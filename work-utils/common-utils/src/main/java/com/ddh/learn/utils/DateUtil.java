package com.ddh.learn.utils;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/21 16:29
 * @description:
 */
public final class DateUtil {
    /**
     * 将日期转换成指定格式字符串  yyyy-MM-dd yyyy/MM/dd
     *
     * @param date   日期对象
     * @param format 格式
     * @return 返回指定格式字符串
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 将字符串日期转换成指定格式的日期对象
     *
     * @param date   日期字符串
     * @param format 格式
     * @return 日期对象
     */
    public static Date parseDate(String date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(date.trim());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer getHourByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static Integer getWeekByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    @Test
    public void test() {
        Date date = new Date();
        System.out.println(getHourByDate(date));
        System.out.println(getWeekByDate(date));
    }
}
