package com.stein.date;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Date 和 Calendar
 *
 * @author njl
 * @date 2023/2/7
 */
public class Date1App {
    public static void main(String[] args) {
//		d1();
//		d2();
//		d3();
        d4();
    }

    /**
     * TimeZone
     */
    public static void d4() {
        // TimeZone 提供了时区的功能，在获取日期时间或转换时可以指定具体的时区
        TimeZone tzDefault = TimeZone.getDefault(); // 当前时区
        TimeZone tzGMT9 = TimeZone.getTimeZone("GMT+09:00"); // GMT+9:00时区
        TimeZone tzNY = TimeZone.getTimeZone("America/New_York"); // 纽约时区
        // 每一个时区都与一个 TimeZone 内部的 ID 进行绑定
        System.out.println(tzDefault.getID()); // Asia/Shanghai
        System.out.println(tzGMT9.getID()); // GMT+09:00
        System.out.println(tzNY.getID()); // America/New_York
        // 查看系统支持的所有时区
        String[] availableIDs = TimeZone.getAvailableIDs();
        System.out.println("availableIDs = " + Arrays.toString(availableIDs));
        // 有了时区 就可以对某一日期时间对象进行转换 转换成同一时刻另一时区的日期时间
        // 将 2000-05-28 12:12:12 上海时间 转换为纽约时间
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"), Locale.CHINA);
        instance.clear();
        instance.set(Calendar.YEAR, 2000);
        instance.set(Calendar.MONTH, 6);
        instance.set(Calendar.DAY_OF_MONTH, 28);
        instance.set(Calendar.HOUR_OF_DAY, 12);
        instance.set(Calendar.MINUTE, 12);
        instance.set(Calendar.SECOND, 12);
        instance.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        String format = sdf.format(instance.getTime());
        System.out.println("format = " + format);
    }

    /**
     * Calendar 类的使用
     */
    public static void d3() {
        // Calendar 类除了可以获取当前的日期时间以外 还可以精确地获取年月日时分秒等信息 此外还可以对日期时间进行运算
        // Calendar 是一个抽象类 通过 getInstance API 获取表示当前时刻的日历对象 可以通过构造器参数 TimeZone 和 Locale 设置本地化
        // 默认两个参数都是的系统默认的时区和本地化设置
        Locale aDefault = Locale.getDefault();
        TimeZone aDefault1 = TimeZone.getDefault();
        // aDefault = zh_CN
        System.out.println("aDefault = " + aDefault);
        // aDefault1 = sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,useDaylight=false,transitions=29,lastRule=null]
        System.out.println("aDefault1 = " + aDefault1);
        Calendar instance = Calendar.getInstance();
        // 通过 Calendar API 获取年月日时分秒等信息
        int year = instance.get(Calendar.YEAR);
        System.out.println("year = " + year);
        int month = instance.get(Calendar.MONTH) + 1;
        System.out.println("month = " + month);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        System.out.println("day = " + day);
        int hour = instance.get(Calendar.HOUR_OF_DAY);
        System.out.println("hour = " + hour);
        int minute = instance.get(Calendar.MINUTE);
        System.out.println("minute = " + minute);
        int second = instance.get(Calendar.SECOND);
        System.out.println("second = " + second);
        // 毫秒 本月的第几天 等等
        // 返回的星期要特别注意，1~7分别表示周日，周一，……，周六。
        // 通过 Calendar 的 setXXX API 修改日历
        instance.set(Calendar.YEAR, 2000);
        instance.set(Calendar.MONTH, 5);
        instance.set(Calendar.DAY_OF_MONTH, 28);
        // 通过 getTime() API 将日历对象转换为 Date
        Date time = instance.getTime();
        System.out.println("time = " + time);
    }

    /**
     * SimpleDateFormat 对 Date 进行自定义的格式化输出
     */
    public static void d2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format = sdf.format(date);
        System.out.println("format = " + format);
    }

    /**
     * Date 类基本介绍
     */
    public static void d1() {
        // Epoch Time是计算从1970年1月1日零点（格林威治时区／GMT+00:00）到现在所经历的秒数
        // 获取当前时间戳
        long currentTimeMillis = System.currentTimeMillis();
        // 旧的日期时间 API java.util 包中 Date Calendar 等
        // 新的日期时间 API java.time 包中 LocalDate LocalTime LocalDateTime 等
        // 一些场景涉及到新旧日期对象的类型转换，因此这两种日期时间 API 都需要会使用
        // Date 是用于表示日期和时间的对象 内部存储了 private transient long fastTime; 用来表示 Epoch Time 时间戳
        Date date = new Date();
        System.out.println(date.getYear() + 1900); // 必须加上1900
        System.out.println(date.getMonth() + 1); // 0~11，必须加上1
        System.out.println(date.getDate()); // 1~31，不能加1
        // 转换为String:
        System.out.println(date.toString());
        // 转换为GMT时区:
        System.out.println(date.toGMTString());
        // 转换为本地时区:
        System.out.println(date.toLocaleString());
        // 上面可以看到很多 Date 的 API 都已经是 Deprecated 不建议使用 而且可以发现获得的具体年月日等表示还需要硬编码自己转换 十分麻烦
    }
}
