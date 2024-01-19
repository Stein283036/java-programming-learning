package com.stein.date;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Instant
 *
 * @author njl
 * @date 2023/2/7
 */
public class Date4App {
    public static void main(String[] args) {
        d1();
    }

    public static void d1() {
        // 计算机存储的当前时间本质上是从 1970年1月1日0时0分0秒 表示的到现在为止经过的秒数 即 Epoch Time
        // 获取当前系统的时间戳
        long c1 = System.currentTimeMillis();
        Instant now = Instant.now();
        System.out.println(now);
        System.out.println(now.getEpochSecond()); // the seconds from the epoch of 1970-01-01T00:00:00Z
        System.out.println(now.toEpochMilli()); // the number of milliseconds since the epoch of 1970-01-01T00:00:00Z
        Instant instant = Instant.ofEpochSecond(1675738766);
        // 有了时间戳 加上时区就能创建 ZonedDateTime 对象
        ZonedDateTime z1 = instant.atZone(ZoneId.systemDefault());
        System.out.println("z1 = " + z1);
    }
}
