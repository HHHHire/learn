package com.ddh.learn.poi;

import org.junit.Test;
import sun.util.resources.ar.CalendarData_ar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/21 13:41
 * @description:
 */
public class TestOther {

    private SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void test() throws Exception {
        String date = parseStr("2020/1/1");
        System.out.println(date);
    }

    private String parseStr(String str) throws Exception {
        String date = null;
        if (str.contains("/")) {
            Date parse = simpleDateFormat1.parse(str);
            date = simpleDateFormat1.format(parse);
        } else if (str.contains("-")) {
            Date parse = simpleDateFormat2.parse(str);
            date = simpleDateFormat2.format(parse);
        }
        return date;
    }

    @Test
    public void test2() throws Exception {
        Date date = new Date();
        System.out.println(date);
        //Fri May 21 14:46:24 CST 2021
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);
        //java.util.GregorianCalendar[time=1621579584780,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,useDaylight=false,transitions=29,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2021,MONTH=4,WEEK_OF_YEAR=21,WEEK_OF_MONTH=4,DAY_OF_MONTH=21,DAY_OF_YEAR=141,DAY_OF_WEEK=6,DAY_OF_WEEK_IN_MONTH=3,AM_PM=1,HOUR=2,HOUR_OF_DAY=14,MINUTE=46,SECOND=24,MILLISECOND=780,ZONE_OFFSET=28800000,DST_OFFSET=0]
    }

    @Test
    public void test3() {
        String[] availableIDs = TimeZone.getAvailableIDs();

        TimeZone aDefault = TimeZone.getDefault();
        System.out.println();
    }

    /**
     * 日期加减
     */
    @Test
    public void test4() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        System.out.println(date);
        // 设置当前日期
        calendar.setTime(date);
        // 加一个月
//        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 2);
        // 获取date对象
        Date time = calendar.getTime();
        System.out.println(time);
    }
}
