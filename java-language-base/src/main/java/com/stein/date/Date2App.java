package com.stein.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * LocalDate 和 LocalTime 和 LocalDateTime 这些都表示本地日期时间 没有时区 ZonedDateTime 有时区
 * java8的 java.util 包下日期时间的新特性
 * 本地日期和时间：LocalDateTime，LocalDate，LocalTime
 * 带时区的日期和时间：ZonedDateTime
 * 时刻：Instant
 * 时区：ZoneId，ZoneOffset
 * 时间间隔：Duration
 * 新的日期时间格式化类 DateTimeFormatter
 * 和旧的API相比，新API严格区分了时刻、本地日期、本地时间和带时区的日期时间，并且，对日期和时间进行运算更加方便。
 * 新的 API 修正了旧 API 的不合理常量设计
 * Month 的范围改为 1-12 表示 1月~12月
 * Week 的范围改为 1-7 表示 周一~周日
 *
 * @author njl
 * @date 2023/2/7
 */
public class Date2App {
    public static void main(String[] args) {
//		d1();
//		d2();
//		d3();
        d4();
    }

    /**
     * Duration 和 Period
     */
    public static void d4() {
        // Duration 用来表示两个时间(不是日期)之间的间隔 内部使用 seconds 和 nanos 表示
        LocalDateTime l1 = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        LocalDateTime l2 = LocalDateTime.of(2020, 1, 9, 19, 25, 30);
        Duration d1 = Duration.between(l1, l2);
        System.out.println("d1 = " + d1); // d1 = PT1235H10M30S 1235小时10分支30秒
        // Period 表示两个日期之间间隔的天数
        LocalDate l3 = LocalDate.of(2000, 5, 28);
        LocalDate l4 = LocalDate.of(2023, 2, 7);
        Period p1 = Period.between(l3, l4);
        System.out.println("p1 = " + p1); // p1 = P22Y8M10D 22年8月10天
        // Duration和Period的表示方法也符合ISO 8601的格式，它以P...T...的形式表示，P...T之间表示日期间隔，T后面表示时间间隔。
        // 如果是PT...的格式表示仅有时间间隔。利用ofXxx()或者parse()方法也可以直接创建Duration
        Duration d2 = Duration.ofHours(10); // 10 hours
        Duration d3 = Duration.parse("P1DT2H3M"); // 1 day, 2 hours, 3 minutes
    }

    /**
     * 比较日期时间
     */
    public static void d3() {
        LocalDate l1 = LocalDate.now();
        LocalDate l2 = l1.minusDays(1);
        System.out.println(l1.isBefore(l2));
        System.out.println(l1.isAfter(l2));
        System.out.println(l1.isLeapYear());
    }

    /**
     * 新的日期时间类的运算
     */
    public static void d2() {
        // 日期时间的加减
        LocalDate birthday = LocalDate.of(2000, 5, 28);
        // 新的 日期时间类几乎都是不可变的 因此不会再原对象上进行变化 会返回新的日期时间对象
        LocalDate l1 = birthday.plusYears(22); // 加 22 年
        LocalDate localDate = l1.plusMonths(8).plusDays(9);
        System.out.println("localDate = " + localDate);
        // 日期时间的变化
        LocalDate l2 = localDate.withDayOfMonth(7);
        System.out.println("ld2 = " + l2);
        // 使用 LocalXXX 通用的 with 做复杂的运算
        // 本月第一天开始时刻
        LocalDateTime l3 = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
        System.out.println("l3 = " + l3);
        // 本月最后一天
        LocalDate l4 = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("l4 = " + l4);
        // 下月第一天
        LocalDate l5 = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("l5 = " + l5);
        // 本月第一个周一
        LocalDate l6 = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println("l6 = " + l6);
    }

    /**
     * 新的日期时间类的简单使用
     */
    public static void d1() {
        // LocalDate LocalTime LocalDateTime 构造器都是私有的 因此只能通过类的静态工厂方法获取类的实例 如下
        LocalDate now = LocalDate.now(); // 获取当前默认时区的日期对象
        LocalTime time = LocalTime.of(9, 59, 26); // 指定具体的时分秒获取当前系统的时间
        LocalDateTime dateTime = LocalDateTime.of(now, time); // 通过 LocalDate LocalTime 获取指定日期时间的本地日期时间对象
        // 本地日期和时间通过now()获取到的总是以当前默认时区返回的，和旧API不同，
        // LocalDateTime、LocalDate和LocalTime默认严格按照ISO 8601规定的日期和时间格式进行打印。
        System.out.println("now = " + now);
        System.out.println("time = " + time);
        System.out.println("dateTime = " + dateTime);
        // LocalDateTime 转换为 日期和时间对象
        LocalDate localDate = dateTime.toLocalDate();
        LocalTime localTime = dateTime.toLocalTime();
        // 严格按照 ISO 8601 规定传入合法的日期时间表示来创建 LocalDateTime
        // ISO 8601规定的日期和时间分隔符是T
        LocalDateTime birthday = LocalDateTime.parse("2000-05-28T12:12:12");
        System.out.println("birthday = " + birthday); // birthday = 2000-05-28T12:12:12
        // 获取在调用 parse 的时候传入指定的 DateTimeFormatter 对象用来格式化日期时间字符串表示
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse("2023-02-07 10:08:31", dtf);
        System.out.println("parse = " + parse);
    }
}
