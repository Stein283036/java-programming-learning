package com.guhe.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * ZonedDateTime
 *
 * @author njl
 * @date 2023/2/7
 */
public class Date3App {
	public static void main(String[] args) {
//		d1();
		d2();
	}

	/**
	 * 时区转换
	 */
	public static void d2() {
		ZonedDateTime z1 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
		System.out.println("z1 = " + z1); // z1 = 2023-02-07T10:53:57.921+08:00[Asia/Shanghai]
		// 转换为美国纽约时区
		ZonedDateTime z2 = z1.withZoneSameInstant(ZoneId.of("America/New_York"));
		System.out.println("z2 = " + z2); // z2 = 2023-02-06T21:53:57.921-05:00[America/New_York]
		// 要特别注意，时区转换的时候，由于夏令时的存在，不同时候的日期转换的结果很可能是不同的
		LocalDateTime l1 = LocalDateTime.of(2022, 9, 7, 10, 52, 31);
		ZonedDateTime z3 = ZonedDateTime.of(l1, ZoneId.of("America/New_York"));
		System.out.println("z3 = " + z3); // z3 = 2022-09-07T10:52:31-04:00[America/New_York]
	}

	public static void d1() {
		// LocalXXX 表示本地日期或时间 要表示带时区的日期或时间 需要使用 ZonedDateTime
		ZonedDateTime z1 = ZonedDateTime.now(); // 获取本地时区的当前日期时间
		System.out.println("z1 = " + z1);
		ZonedDateTime z2 = ZonedDateTime.now(ZoneId.of("America/New_York")); // 获取美国纽约时区的当前日期时间
		System.out.println("z2 = " + z2);
		// 通过 LocalXXX的 atZone() 获取指定时区的统一时刻表示
		LocalDateTime l1 = LocalDateTime.now();
		ZonedDateTime z3 = l1.atZone(ZoneId.of("Asia/Chongqing"));
		System.out.println("z3 = " + z3);
	}
}
