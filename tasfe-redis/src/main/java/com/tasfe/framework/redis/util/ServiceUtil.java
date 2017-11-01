package com.tasfe.framework.redis.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lait.zhang@gmail.com
 * @Description: TODO
 * @date 2017年2月26日  上午12:03:10
 */
public class ServiceUtil {

    private static final Logger logger = LoggerFactory.getLogger(ServiceUtil.class);

    //解析json字符串到Map中
    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseJsonString(final String jsonStr) {

        Map<String, Object> map = new HashMap<String, Object>();

        if (jsonStr == null || "".equals(jsonStr)) {
            return map;
        }

        try {
            map = (Map<String, Object>) JSON.parse(jsonStr);
        } catch (Exception ex) {
            logger.error("解析jsonStr：{}时出现异常，异常信息如下：\n{}", jsonStr, ex);
        }
        return map;
    }

    //转换map为json字符串
    public static String toJsonString(final Map<String, Object> map) {
        return (map == null || map.isEmpty()) ? "" : JSON.toJSONString(map);
    }

    //获取Map属性值，忽略属性名称的大小写
    @SuppressWarnings("unchecked")
    public static <E> E getPropertyByKeyInsensitive(final Map<String, Object> infoMap, final String propertyName, final Class<E> requiredType) {

        if (infoMap == null || propertyName == null || infoMap.size() == 0 || "".equals(propertyName)) {
            return null;
        }

        for (String key : infoMap.keySet()) {
            if (key == null) {
                continue;
            }

            if (key.toUpperCase().equalsIgnoreCase(propertyName)) {
                return (E) infoMap.get(key);
            }
        }
        return null;
    }

    //睡眠等待timeout毫秒（ms）
    public static void waitFor(long timeout) {

        if (timeout <= 0) {
            return;
        }

        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (Exception e) {
        }
    }

    //睡眠等待timeout毫秒（ms）
    public static void waitFor(long timeout, TimeUnit unit) {

        if (timeout <= 0 || null == unit) {
            return;
        }

        try {
            TimeUnit.MILLISECONDS.sleep(unit.convert(timeout, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
        }
    }
}
