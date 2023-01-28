package com.guhe.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author njl
 * @date 2023/1/28
 */
@SuppressWarnings("ALL")
public class DateApp {
	public static void main(String[] args) {
//		dateTime1();
//		dateTime2();
		dateTime3();
	}

	public static void dateTime3() {
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			// 按照日期
			Date now = sdf.parse("2023-01-28 15:06:45");
			System.out.println(sdf.format(now));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		System.out.printf("当前日期时间：%s", sdf.format(new Date()));
	}

	public static void dateTime2() {
		// 日期比较
		Date date1 = new Date();
		Date date2 = new Date(12500000000L);
		boolean cmp1 = date1.equals(date2); // false
		boolean cmp2 = date1.before(date2); // false
		boolean cmp3 = date1.after(date2); // true
		int cmp4 = date1.compareTo(date2); // 1
		System.out.println("cmp1 = " + cmp1);
		System.out.println("cmp2 = " + cmp2);
		System.out.println("cmp3 = " + cmp3);
		System.out.println("cmp4 = " + cmp4);
	}

	public static void dateTime1() {
		// 查看当前日期时间
		Date date1 = new Date();
		System.out.println("date = " + date1);
	}
}
