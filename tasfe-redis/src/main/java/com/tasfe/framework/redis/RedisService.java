package com.tasfe.framework.redis;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lait.zhang@gmail.com
 * @Description: redis服务层接口
 * @date 2017年3月19日  上午9:28:12
 */
public interface RedisService {

    /**
     * @param key
     * @param value
     * @return
     * @Description: 保存字符串值
     */
    boolean setString(String key, String value);

    /**
     * @param key
     * @param value
     * @param expire 过期时间，单位秒
     * @return
     * @Description: 保存字符串值，设置过期时间
     */
    boolean setString(String key, String value, long expire);

    /**
     * @param key
     * @param value
     * @param syn     true=同步保存、false=异步保存
     * @param retry   获取不到redis连接时，重试次数
     * @param service 异步使用的线程池，syn=false时才有效，syn=true时该参数可为null
     * @return
     * @Description: 保存字符串值，设置同步/异步方式、重试次数
     */
    boolean setString(String key, String value, boolean syn, int retry, ExecutorService service);

    /**
     * @param key
     * @param value
     * @param expire  过期时间，单位秒
     * @param syn     true=同步保存、false=异步保存
     * @param retry   获取不到redis连接时，重试次数
     * @param service 异步使用的线程池，syn=false时才有效，syn=true时该参数可为null
     * @return
     * @Description: 保存字符串值，设置过期时间、同步/异步方式、重试次数
     */
    boolean setString(String key, String value, long expire, boolean syn, int retry, ExecutorService service);

    /**
     * @param key
     * @param value
     * @return
     * @Description: 保存对象值
     */
    boolean setObject(String key, Object value);

    /**
     * @param key
     * @param value
     * @param expire 过期时间，单位秒
     * @return
     * @Description: 保存对象值，设置过期时间
     */
    boolean setObject(String key, Object value, long expire);

    /**
     * @param key
     * @param value
     * @param syn     true=同步保存、false=异步保存
     * @param retry   获取不到redis连接时，重试次数
     * @param service 异步使用的线程池，syn=false时才有效，syn=true时该参数可为null
     * @return
     * @Description: 保存对象值，设置同步/异步方式、重试次数
     */
    boolean setObject(String key, Object value, boolean syn, int retry, ExecutorService service);

    /**
     * @param key
     * @param value
     * @param expire  过期时间，单位秒
     * @param syn     true=同步保存、false=异步保存
     * @param retry   获取不到redis连接时，重试次数
     * @param service 异步使用的线程池，syn=false时才有效，syn=true时该参数可为null
     * @return
     * @Description: 保存对象值，设置过期时间、同步/异步方式、重试次数
     */
    boolean setObject(String key, Object value, long expire, boolean syn, int retry, ExecutorService service);

    /**
     * @param key
     * @return
     * @Description: 对象值获取方法
     */
    Object getObject(String key);

    /**
     * @param key
     * @param retry
     * @return
     * @Description: 对象值获取方法，设置重试次数
     */
    Object getObject(String key, int retry);

    /**
     * @param key
     * @return
     * @Description: 字符串值获取方法
     */
    String getString(String key);

    /**
     * @param key
     * @param retry
     * @return
     * @Description: 字符串值获取方法，设置重试次数
     */
    String getString(String key, int retry);

    /**
     * @param key
     * @param retry
     * @return
     * @Description: 删除方法
     */
    boolean remove(String key, int retry);

    /**
     * @param key
     * @param syn     true=同步删除、false=异步删除
     * @param retry   获取不到redis连接时，重试次数
     * @param service 异步使用的线程池，syn=false时才有效，syn=true时该参数可为null
     * @return
     * @Description: 删除方法，设置同步/异步方式、重试次数，异步方式时不保证一定能删除，返回true表示已开启线程进行删除。
     */
    boolean remove(String key, boolean syn, int retry, ExecutorService service);

    /**
     * @param key 锁名称
     * @return
     * @Description: 获取锁，成功返回锁id（用于释放锁），失败返回null
     */
    String acquireLock(String key);

    /**
     * @param key    锁名称
     * @param expire 锁的过期时间（占用时间，超过自动释放），单位秒
     * @return 成功时返回锁id，失败时返回null
     * @Description: 获取锁，成功返回锁id（用于释放锁），失败返回null
     */
    String acquireLock(String key, long expire);

    /**
     * @param key     锁名称
     * @param timeout 获取锁的超时时间
     * @param unit    超时时间单位
     * @return 成功时返回锁id，失败时返回null
     * @Description: 获取锁，一直获取直到超出timeout指定的时间，成功返回锁id（用于释放锁），失败返回null
     */
    String acquireLock(String key, long timeout, TimeUnit unit);

    /**
     * @param key     锁名称
     * @param expire  锁的过期时间（占用时间，超过自动释放），单位秒
     * @param timeout 获取锁的超时时间
     * @param unit    超时时间单位
     * @return 成功时返回锁id，失败时返回null
     * @Description: 获取锁，一直获取直到超出timeout指定的时间，成功返回锁id（用于释放锁），失败返回null
     */
    String acquireLock(String key, long expire, long timeout, TimeUnit unit);

    /**
     * @param key 锁名称
     * @return 释放成功返回true，失败返回false
     * @Description: 强制释放锁，该方法有风险（如果锁已过期，又被其他用户获取时，该方法会释放锁，可能会造成并发bug），不提供异步方式
     */
    boolean releaseLock(String key);

    /**
     * @param key    锁名称
     * @param lockId 锁id（为空时表示强制释放，不为空时必须与当前锁的值一样才会释放）
     * @return 释放成功返回true，失败返回false
     * @Description: 释放锁，只释放指定id的锁
     */
    boolean releaseLock(String key, String lockId);

    /**
     * @param key     锁名称
     * @param lockId  锁id（为空时表示强制释放，此时异步失效，不为空时必须与当前锁的值一样才会释放）
     * @param syn     true=同步，false=异步
     * @param service 只在syn=false时有效，指定线程池
     * @return
     * @Description: 释放锁，只释放指定id的锁，提供同步/异步方式，通常异步方式用于有过期时间的锁（没有设置过期时间的锁不建议用异步方式）
     */
    boolean releaseLock(String key, String lockId, boolean syn, ExecutorService service);

    /**
     * @param map 批量写的key-value集合
     * @return
     * @Description: 通过pipeline方式批量写
     */
    boolean pipelineWrite(Map<String, Object> map);

    /**
     * @param map    批量写的key-value集合
     * @param expire 过期时间
     * @return
     * @Description: 通过pipeline方式批量写，指定过期时间，单位秒
     */
    boolean pipelineWrite(Map<String, Object> map, long expire);

    /**
     * @param map     批量写的key-value集合
     * @param syn     true=同步，false=异步
     * @param service 只在syn=false时有效，指定线程池
     * @return
     * @Description: 通过pipeline方式批量写，指定同步/异步方式写
     */
    boolean pipelineWrite(Map<String, Object> map, boolean syn, ExecutorService service);

    /**
     * @param map     批量写的key-value集合
     * @param expire  过期时间，单位秒
     * @param syn     true=同步，false=异步
     * @param service 只在syn=false时有效，指定线程池
     * @return
     * @Description: 通过pipeline方式批量写，指定过期时间（单位秒），同步/异步方式写
     */
    boolean pipelineWrite(Map<String, Object> map, long expire, boolean syn, ExecutorService service);

    /**
     * @param list 要批量读取的key列表
     * @return
     * @Description: 通过pipeline方式批量读，适合超大key列表，spring-data还没实现集群的pipeline
     */
    List<Object> pipelineRead(List<String> list);

    boolean multiSet(Map<String, Object> map);

    boolean multiSet(Map<String, Object> map, long expire);

    boolean multiSet(Map<String, Object> map, boolean syn, ExecutorService service);

    boolean multiSet(Map<String, Object> map, long expire, boolean syn, ExecutorService service);

    List<Object> multiGet(List<String> list);
}
