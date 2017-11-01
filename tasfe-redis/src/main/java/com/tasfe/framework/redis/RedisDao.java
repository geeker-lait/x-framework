package com.tasfe.framework.redis;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author lait.zhang@gmail.com
 * @Description: redis缓存操作接口
 * @date 2017年4月1日  下午5:16:06
 */
public interface RedisDao {

    /**
     * @param key   要保存的key
     * @param value 要保存的String类型值
     * @Description: 保存指定key和String类型值到redis缓存
     */
    void setString(String key, String value);

    /**
     * @param key     要保存的key
     * @param value   要保存的String类型值
     * @param timeout 过期时间
     * @param unit    过期时间单位
     * @Description: 保存指定key和String类型值到redis缓存，指定过期时间和单位
     */
    void setString(String key, String value, long timeout, TimeUnit unit);

    /**
     * @param key    要保存的key
     * @param value  要保存的String类型值
     * @param expire 过期时间
     * @Description: 保存指定key和String类型值到redis缓存，指定过期时间，单位秒
     */
    void setString(String key, String value, long expire);

    /**
     * @param key   要保存的key
     * @param value 要保存的String类型值
     * @param date  过期日期
     * @Description: 保存指定key和String类型值到redis缓存，指定过期日期
     */
    void setString(String key, String value, Date date);

    /**
     * @param key   要保存的key
     * @param value 要保存的值
     * @Description: 保存指定key到redis缓存
     */
    void setObject(String key, Object value);

    /**
     * @param key     要保存的key
     * @param value   要保存的值
     * @param timeout 过期时间
     * @param unit    过期时间单位
     * @Description: 保存指定key到redis缓存，指定过期时间和单位
     */
    void setObject(String key, Object value, long timeout, TimeUnit unit);

    /**
     * @param key    要保存的key
     * @param value  要保存的值
     * @param expire 过期时间
     * @Description: 保存指定key到redis缓存，指定过期时间，单位秒
     */
    void setObject(String key, Object value, long expire);

    /**
     * @param key   要保存的key
     * @param value 要保存的值
     * @param date  过期日期
     * @Description: 保存指定key到redis缓存，指定过期日期
     */
    void setObject(String key, Object value, Date date);

    /**
     * @param key   要获取锁的名称
     * @param value 锁的值，通常使用某个id以在主动释放时判断是否自己的锁
     * @return 获取成功=true、失败=false
     * @Description: cas锁实现，不设置过期时间（永久锁，必须主动释放）
     */
    boolean setIfAbsent(String key, Object value);

    /**
     * @param key     要获取锁的名称
     * @param value   锁的值，通常使用某个id以在主动释放时判断是否自己的锁
     * @param timeout 过期时间
     * @param unit    过期时间单位
     * @return 获取成功=true、失败=false
     * @Description: cas锁实现，指定过期时间和单位（临时锁，如果不主动释放，到指定时间后自动释放）
     */
    boolean setIfAbsent(String key, Object value, long timeout, TimeUnit unit);

    /**
     * @param key    要获取锁的名称
     * @param value  锁的值，通常使用某个id以在主动释放时判断是否自己的锁
     * @param expire 过期时间
     * @return 获取成功=true、失败=false
     * @Description: cas锁实现，指定过期时间，单位秒（临时锁，如果不主动释放，到指定时间后自动释放）
     */
    boolean setIfAbsent(String key, Object value, long expire);

    /**
     * @param key   要获取锁的名称
     * @param value 锁的值，通常使用某个id以在主动释放时判断是否自己的锁
     * @param date  过期日期
     * @return 获取成功=true、失败=false
     * @Description: cas锁实现，指定过期日期（临时锁，如果不主动释放，到指定时间后自动释放）
     */
    boolean setIfAbsent(String key, Object value, Date date);

    /**
     * @param key 要获取的key
     * @return
     * @Description: 获取指定key的String值
     */
    String getString(String key);

    /**
     * @param key 要获取的key
     * @return
     * @Description: 获取指定key的值
     */
    Object getObject(String key);

    /**
     * @param key 要查询的key
     * @return
     * @Description: 是否包含指定的key
     */
    boolean containsKey(String key);

    /**
     * @param key    要修改的key
     * @param newKey 修改后的key
     * @Description: 修改key的名称
     */
    void rename(String key, String newKey);

    /**
     * @param key 要删除的key
     * @Description: 删除指定key
     */
    void remove(String key);

    /**
     * @param pattern 要获取的pattern
     * @return
     * @Description: 获取指定pattern的所有key
     */
    Set<String> keys(String pattern);

    /**
     * @param key    要设置的key
     * @param expire 过期时间
     * @return
     * @Description: 设置key的过期时间，单位秒
     */
    boolean expire(String key, long expire);

    /**
     * @param key     要设置的key
     * @param timeout 过期时间
     * @param unit    过期时间单位
     * @return
     * @Description: 设置key的过期时间，指定单位
     */
    boolean expire(String key, long timeout, TimeUnit unit);

    /**
     * @param key  要设置的key
     * @param date 过期的日期
     * @return
     * @Description: 设置key的过期时间，指定到date
     */
    boolean expire(String key, Date date);

    /**
     * @param key 要获取的key
     * @return
     * @Description: 获取key的过期时间，单位秒
     */
    Long getExpire(String key);

    /**
     * @param key  要获取的key
     * @param unit 过期时间的单位
     * @return
     * @Description: 获取key的过期时间，指定单位
     */
    Long getExpire(String key, TimeUnit unit);

    /**
     * @param key    集合的key
     * @param values 要添加的String类型元素数组
     * @return
     * @Description: 添加String类型set集合元素
     */
    boolean sAdd(String key, String... values);

    /**
     * @param key    集合的key
     * @param values 要删除的String类型元素数组
     * @return
     * @Description: 删除String类型set集合的元素
     */
    boolean sRem(String key, String... values);

    /**
     * @param key 集合的key
     * @return
     * @Description: 获取String类型set集合的内容
     */
    Set<String> sMembers(String key);

    /**
     * @param key
     * @param field
     * @param value
     * @return
     * @Description: 添加String类型map集合的一个字段
     */
    boolean hSetString(String key, String field, String value);

    /**
     * @param key
     * @param map
     * @return
     * @Description: 添加String类型map集合
     */
    void hMSetString(String key, Map<String, String> map);

    /**
     * @param key
     * @param field
     * @param value
     * @return
     * @Description: 添加Object类型map集合的一个字段
     */
    boolean hSet(String key, Object field, Object value);

    /**
     * @param key
     * @param map
     * @return
     * @Description: 添加Object类型map集合
     */
    void hMSet(String key, Map<Object, Object> map);

    /**
     * @param key
     * @param field
     * @return
     * @Description: 获取String类型map集合的一个字段内容
     */
    String hGetString(String key, String field);

    /**
     * @param key
     * @param fields
     * @return
     * @Description: TODO
     */
    List<String> hMGetString(String key, String... fields);

    /**
     * @param key
     * @return
     * @Description: 获取String类型map集合的所有字段内容
     */
    Map<String, String> hGetAllString(String key);

    /**
     * @param key
     * @param field
     * @return
     * @Description: 获取Object类型map集合的一个字段内容
     */
    Object hGet(String key, Object field);

    /**
     * @param key
     * @param fields
     * @return
     * @Description: 获取Object类型map集合的多个字段内容
     */
    List<Object> hMGet(String key, Object... fields);

    /**
     * @param key
     * @return
     * @Description: 获取Object类型map集合的所有字段内容
     */
    Map<Object, Object> hGetAll(String key);

    /**
     * @param map 批量写的key-value集合
     * @Description: 批量写redis缓存
     */
    void multiSet(Map<String, Object> map);

    /**
     * @param map     批量写的key-value集合
     * @param timeout 过期时间
     * @param unit    过期时间单位
     * @Description: 批量写redis缓存
     */
    void multiSet(Map<String, Object> map, long timeout, TimeUnit unit);

    /**
     * @param list key列表
     * @return
     * @Description: 批量获取redis内容
     */
    List<Object> multiGet(List<String> list);

    /**
     * @param map 批量写的key-value集合
     * @Description: spring-data cluster暂时不支持pipeline方式，自己实现
     */
    void pipelineSet(Map<String, Object> map);

    /**
     * @param map     批量写的key-value集合
     * @param timeout 过期时间
     * @param unit    过期时间单位
     * @Description: spring-data cluster暂时不支持pipeline方式，自己实现
     */
    void pipelineSet(Map<String, Object> map, long timeout, TimeUnit unit);

    /**
     * @param list key列表
     * @return
     * @Description: spring-data cluster暂时不支持pipeline方式，自己实现
     */
    List<Object> pipelineGet(List<String> list);
}
