package com.stein.java8.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author njl
 * @date 2023/2/13
 */
public class Time1App {
	public static void main(String[] args) {
//		t1();
//		t2();
//		t3();
//		t4();
		t5();
	}

	public static void t5() { // LocalDateTime
		// A date-time without a time-zone in the ISO-8601 calendar system, such as 2007-12-03T10:15:30.
		LocalDateTime now = LocalDateTime.of(2023, Month.FEBRUARY, 13, 17, 5, 56);
		// 获取 LocalDateTime 的相关字段
		int year = now.getYear();
		Month month = now.getMonth();
		int dayOfMonth = now.getDayOfMonth(); // 这个月的第几天
		DayOfWeek dayOfWeek = now.getDayOfWeek(); // 这周的第几天 周一 ~ 周日
		int dayOfYear = now.getDayOfYear(); // 这一年的第几天
		long minuteOfDay = now.getLong(ChronoField.MINUTE_OF_DAY); // 到现在为止今天过了多少分钟
		LocalDate localDate = now.toLocalDate(); // 转换为 LocalDate
		LocalTime localTime = now.toLocalTime(); // 转换为 LocalTime
		LocalDateTime now2 = LocalDateTime.of(localDate, localTime); // 通过 LocalDate 和 LocalTime 构造 LocalDateTime

		System.out.println("year = " + year);
		System.out.println("month = " + month);
		System.out.println("dayOfMonth = " + dayOfMonth);
		System.out.println("dayOfWeek = " + dayOfWeek);
		System.out.println("dayOfYear = " + dayOfYear);
		System.out.println("minuteOfDay = " + minuteOfDay);
	}

	public static void t4() { // DateTimeFormatter
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthday = LocalDate.parse("2000-05-28", dtf);
		System.out.println("birthday = " + birthday);
	}

	public static void t3() { // LocalDate
		// A date without a time-zone in the ISO-8601 calendar system, such as 2007-12-03.
		LocalDate now = LocalDate.now();
		LocalDate tomorrow = now.plus(1, ChronoUnit.DAYS); // 每次对 LocalDate 实例的修改都返回新的实例
		LocalDate yesterday = tomorrow.minusDays(2);
		System.out.println("yesterday = " + yesterday);
		LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
		DayOfWeek dayOfWeek = independenceDay.getDayOfWeek(); // 这一天是周几
		System.out.println("dayOfWeek = " + dayOfWeek);
	}

	public static void t2() { // Timezones && LocalTime
		// 每一个 TimeZone 时区由 对应的 ZoneId 表示
		String[] availableIDs = TimeZone.getAvailableIDs(); // 获得所有可用的 ZoneId
		System.out.println("availableIDs = " + Arrays.toString(availableIDs));
		TimeZone timeZone = TimeZone.getTimeZone("America/Los_angles"); // 根据 ZoneId 获取指定的时区

		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		ZoneId zone3 = ZoneId.of("Asia/Shanghai");
		// A time without a time-zone in the ISO-8601 calendar system, such as 10:15:30.
		LocalTime lt1 = LocalTime.now(zone1); // 获得 Europe/Berlin 时区的当前时间
		LocalTime lt2 = LocalTime.now(zone2); // 获得 Brazil/East 时区的当前时间
		LocalTime lt3 = LocalTime.now(zone3); // 获得 Asia/Shanghai 时区的当前时间
		System.out.println("lt1 = " + lt1);
		System.out.println("lt2 = " + lt2);
		System.out.println("lt3 = " + lt3);
		System.out.println(lt3.isBefore(lt1));
		System.out.println(lt2.isBefore(lt1));
		// 计算两个 LocalTime 之间的小时分钟差
		long hours = ChronoUnit.HOURS.between(lt1, lt3);
		long minutes = ChronoUnit.MINUTES.between(lt1, lt3);
		System.out.println("hours = " + hours);
		System.out.println("minutes = " + minutes);
	}

	public static void t1() { // Clock
		// Clock provides access to the current date and time. Clocks are aware of a timezone and may
		// be used instead of System.currentTimeMillis() to retrieve the current time in milliseconds since Unix EPOCH.
		// Such an instantaneous point on the time-line is also represented by the class Instant. Instants can be used
		// to create legacy java.util.Date objects.
		// A clock providing access to the current instant, date and time using a time-zone.
		Clock clock = Clock.systemDefaultZone();
		System.out.println("clock = " + clock);
		Instant instant = clock.instant();
		Date date = Date.from(instant);
		System.out.println("date = " + date);
	}
}
