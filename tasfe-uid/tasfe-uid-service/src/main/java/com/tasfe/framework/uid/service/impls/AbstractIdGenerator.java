package com.tasfe.framework.uid.service.impls;

import java.util.Calendar;
import java.util.Date;

/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2018-02-08 12:41
 */
public class AbstractIdGenerator {

    /**
     * 获取一年中的第多少天的第多少个小时
     * @param date
     * @return
     * @Description
     */
    protected String getYDHPrefix(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        // 今天是第多少天
        int day = c.get(Calendar.DAY_OF_YEAR);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        // 0补位操作 必须满足三位
        String dayFmt = String.format("%1$03d", day);
        // 0补位操作 必须满足2位
        String hourFmt = String.format("%1$02d", hour);
        StringBuffer prefix = new StringBuffer();
        prefix.append((year - 2000)).append(dayFmt).append(hourFmt);
        return prefix.toString();
    }




}