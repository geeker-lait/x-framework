package com.tasfe.framework.uid.id;

import com.tasfe.framework.uid.handler.EncoderHandler;
import com.tasfe.framework.uid.model.UidRequest;
import com.tasfe.framework.uid.util.Constant;
import com.tasfe.framework.uid.util.IpUtil;
import com.tasfe.framework.uid.util.JedisUtil;
import com.tasfe.framework.uid.util.MessageType;
import com.tasfe.framework.uid.zk.ZkClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import redis.clients.jedis.ShardedJedis;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 客户端ID生成类
 * Created by lait.zhang on 2017/8/3.
 */
public class TasfeUid {

    private static final Logger logger = Logger.getLogger(TasfeUid.class);

    /**
     * 连接池，维护与服务端的长连接
     */
    private Map<String, ChannelFuture> channelPool = new ConcurrentHashMap<String, ChannelFuture>();

    /**
     * 服务端开始生成新的ID的开关
     */
    private volatile boolean flag = false;

    private ZkClient zkClient;

    private JedisUtil jedisUtil;

    private LinkedBlockingQueue<UidRequest> sendQueue = new LinkedBlockingQueue<UidRequest>(8);

    /**
     *ZooKeeper服务地址
     */
    private String zkAddress;

    /**
     *rediss服务地址
     */
    private String redisAddress;

    public TasfeUid(String zkAddress, String redisAddress) {
        this.zkAddress = zkAddress;
        this.redisAddress = redisAddress;
        try {
            //初始化ZkClient
            this.zkClient = new ZkClient(zkAddress);
            this.jedisUtil = JedisUtil.newInstance(redisAddress);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                public void run() {
                    zkClient.close();
                    jedisUtil.close();
                }
            }));
            SendThread sendThread = new SendThread();
            sendThread.start();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (KeeperException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    /**
     * 获取id
     * @return
     */
    public long nextId() throws InterruptedException {
        ShardedJedis jedis = jedisUtil.getJedis();
        try {
            int list_min_size = zkClient.getRedisListSize() * 300;
            return getId(jedis, list_min_size);
        } finally {
            jedisUtil.returnResource(jedis);
        }
    }

    private long getId(ShardedJedis jedis, int list_min_size) throws InterruptedException {
        long len = jedis.llen(Constant.REDIS_LIST_NAME);
        if ((int) len < list_min_size) {
            getRedisLock(jedis);
        }
        String id = jedis.lpop(Constant.REDIS_LIST_NAME);
        if (len == 0l || null == id || "".equals(id)) {
            Thread.sleep(100l);
            return getId(jedis, list_min_size);
        }
        return Long.valueOf(id);
    }

    /**
     * 获取redis锁；若获得，则发送消息到服务端
     * @throws KeeperException
     * @throws InterruptedException
     */
    private void getRedisLock(ShardedJedis jedis) {
        //redis加锁
        if (jedis.setnx(Constant.REDIS_SETNX_KEY, "1") == 1l) {
            //设置有效时间
            jedis.expire(Constant.REDIS_SETNX_KEY, 3);
            UidRequest uidRequest = new UidRequest(MessageType.REQUEST_TYPE_CREATE);
            //向发送队列中推入请求
            sendQueue.offer(uidRequest);
        }
    }

    /**
     * 向服务端发送指令的线程
     */
    private class SendThread extends Thread {

        private EventLoopGroup group;

        private Bootstrap bootstrap;

        public SendThread() {
            group = new NioEventLoopGroup();
            bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline()
                                    .addLast(new EncoderHandler());
                        }
                    }).option(ChannelOption.SO_KEEPALIVE, true);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                public void run() {
                    group.shutdownGracefully();
                }
            }));
        }

        @Override
        public void run() {
            while (true) {
                //从发送队列中去出请求
                UidRequest uidRequest = sendQueue.poll();
                if (uidRequest != null) {
                    try {
                        //通过zookeeper的负载均衡算法，获取服务端ip地址
                        String ip = zkClient.balance();
                        String host = IpUtil.getHost(ip);
                        ChannelFuture channelFuture = connectChannel(host, Constant.TASFE_UID_SERVER_PORT);
                        //发送
                        send(channelFuture, uidRequest);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                        logger.error(e.getMessage());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        logger.error(e.getMessage());
                    }
                }
            }
        }

        public ChannelFuture connectChannel(String host, int port) {
            //从连接池中获取连接
            ChannelFuture channelFuture = channelPool.get(host);
            //若池中没有，则建立连接，并放入连接池
            if (channelFuture == null) {
                channelFuture = connect(host, port);
                channelPool.put(host, channelFuture);
            }
            return channelFuture;
        }

        public ChannelFuture connect(String host, int port) {
            ChannelFuture future = null;
            try {
                // 链接服务器
                future = bootstrap.connect(host, port).sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
            return future;
        }

        public void send(ChannelFuture future, UidRequest uidRequest) {
            try {
                // 将request对象写入outbundle处理后发出
                future.channel().writeAndFlush(uidRequest).sync();
                // 服务器同步连接断开时,这句代码才会往下执行
                //future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public ZkClient getZkClient() {
        return zkClient;
    }

    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    public String getZkAddress() {
        return zkAddress;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    public String getRedisAddress() {
        return redisAddress;
    }

    public void setRedisAddress(String redisAddress) {
        this.redisAddress = redisAddress;
    }

}
