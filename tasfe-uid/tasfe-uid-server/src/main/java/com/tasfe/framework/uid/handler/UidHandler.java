package com.tasfe.framework.uid.handler;

import com.tasfe.framework.uid.model.UidRequest;
import com.tasfe.framework.uid.server.UidServer;
import com.tasfe.framework.uid.util.Constant;
import com.tasfe.framework.uid.util.JedisUtil;
import com.tasfe.framework.uid.util.MessageType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请求处理handler
 * Created by lait.zhang on 2017/8/3.
 */
public class UidHandler extends SimpleChannelInboundHandler<UidRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UidHandler.class);

    private JedisUtil jedisUtil;

    private UidServer server;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UidRequest uidRequest) throws Exception {
        if (uidRequest.getType() == MessageType.REQUEST_TYPE_CREATE) {
            long begin = System.currentTimeMillis();
            try {
                server.pushIdsInRedis();
                LOGGER.info("handler run time:" + (System.currentTimeMillis() - begin));
            } finally {
                jedisUtil.del(Constant.REDIS_SETNX_KEY);
                //jedisUtil.returnResource(jedis);
                //zkClient.decrease(ip);
                //ctx.writeAndFlush("").addListener(ChannelFutureListener.CLOSE);
            }
        }
    }

    public UidHandler(UidServer server, JedisUtil jedisUtil) {
        this.server = server;
        this.jedisUtil = jedisUtil;
    }

    public JedisUtil getJedisUtil() {
        return jedisUtil;
    }

    public void setJedisUtil(JedisUtil jedisUtil) {
        this.jedisUtil = jedisUtil;
    }

}
