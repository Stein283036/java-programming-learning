package com.stein.date;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * 新旧日期时间 API 的最佳实践
 *
 * @author njl
 * @date 2023/2/7
 */
public class Date5App {
    public static void main(String[] args) {

    }

    /**
     * 旧 API -> 新 API
     */
    public static void d1() {
        Instant i1 = new Date().toInstant();
        ZonedDateTime z1 = i1.atZone(ZoneId.systemDefault());
        LocalDate l1 = z1.toLocalDate();
        LocalTime l2 = z1.toLocalTime();
        LocalDateTime l3 = z1.toLocalDateTime();

        Instant i2 = Calendar.getInstance().toInstant();
        // 转换同上
    }
}
