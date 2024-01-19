package com.stein.datetime;

import java.util.*;

/**
 * @author njl
 * @date 2023/1/28
 */
public class CalendarApp {
    public static void main(String[] args) {
//		calendar1();
//		calendar2();
//		calendar3();
        calendar4();
    }

    /**
     * GregorianCalendar 练习
     */
    public static void calendar4() {
        String[] months = {
                "Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug",
                "Sep", "Oct", "Nov", "Dec"};

        int year;
        // 初始化 Gregorian 日历
        // 使用当前时间和日期
        // 默认为本地时间和时区
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        // 显示当前时间和日期的信息
        System.out.print("Date: ");
        System.out.print(months[gregorianCalendar.get(Calendar.MONTH)]);
        System.out.print(" " + gregorianCalendar.get(Calendar.DAY_OF_MONTH) + " ");
        System.out.println(year = gregorianCalendar.get(Calendar.YEAR));
        System.out.print("Time: ");
        System.out.print(gregorianCalendar.get(Calendar.HOUR_OF_DAY) + ":"); // 24 小时制
//		System.out.print(gregorianCalendar.get(Calendar.HOUR) + ":"); // 12 小时制
        System.out.print(gregorianCalendar.get(Calendar.MINUTE) + ":");
        System.out.println(gregorianCalendar.get(Calendar.SECOND));

        // 测试当前年份是否为闰年
        if (gregorianCalendar.isLeapYear(year)) {
            System.out.println("当前年份是闰年");
        } else {
            System.out.println("当前年份不是闰年");
        }
    }

    /**
     * 分日期时间字段修改日期时间
     */
    public static void calendar3() {
        // 如果只设定某个字段，例如日期的值，则可以使用如下set方法
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2022);
        instance.set(Calendar.MONTH, 11);
        // 每月的第一天从1开始表示
        instance.set(Calendar.DAY_OF_MONTH, 20);
        // 通过 add 修改日期时间
        instance.add(Calendar.YEAR, 1);
        instance.add(Calendar.MONTH, -1); // 1 月 -1 变为 11 月，这里注意MONTH的值是0~11来表示12个月，0表示JANUARY
        instance.add(Calendar.DAY_OF_MONTH, -10);
        // 通过 get 获得日期时间
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        System.out.println("year = " + year);
        System.out.println("month = " + month);
        System.out.println("day = " + day);
        Date date = instance.getTime();
        System.out.println("date = " + date);
    }

    /**
     * 整体修改日期时间
     */
    public static void calendar2() {
        Calendar instance = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        // 设置日期时间
        instance.set(2000, 4, 28, 12, 30, 55);
        Date birthday = instance.getTime();
        System.out.println("time = " + birthday);
    }

    /**
     * 获取当前日期
     */
    public static void calendar1() {
        Calendar now = Calendar.getInstance();
        System.out.println("now = " + now);
    }
}
