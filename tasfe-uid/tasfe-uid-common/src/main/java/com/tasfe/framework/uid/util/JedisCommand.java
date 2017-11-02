package com.tasfe.framework.uid.util;

import redis.clients.jedis.ShardedJedis;

/**
 * jedis命令接口
 * Created by lait.zhang on 2017/8/9.
 */
public interface JedisCommand<T> {

    T command(ShardedJedis jedis);
}
