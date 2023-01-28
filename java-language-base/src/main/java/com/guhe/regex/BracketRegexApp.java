package com.guhe.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author njl
 * @date 2023/1/28
 */
public class BracketRegexApp {
	private static final String REGEX = "(\\d{2})([a-z]{2,3})";

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher("33aa-32sdy-29ssc");
		while (matcher.find()) {
			System.out.println(matcher.group(1)); // 数字分组
			System.out.println(matcher.group(2)); // 字母分组
		}

		// \\2 代表引用前面的第2组匹配的值
		Pattern p = Pattern.compile("(\\d(\\d))\\2");
		Matcher matcher2 = p.matcher("211");
		int groupCount = matcher2.groupCount();
		System.out.println("groupCount = " + groupCount);
//		System.out.println(matcher2.matches());
		while (matcher2.find()) {
			System.out.println(matcher2.group(0));
			System.out.println(matcher2.group(1));
			System.out.println(matcher2.group(2));
		}
	}
}
