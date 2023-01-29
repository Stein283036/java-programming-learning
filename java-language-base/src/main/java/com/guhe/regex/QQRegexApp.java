package com.guhe.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author njl
 * @date 2023/1/28
 */
public class QQRegexApp {
	private static final String QQREGEX = "[1-9]\\d{4,11}";

	public static void main(String[] args) {
		String qqNumber = args[0];

		Pattern pattern = Pattern.compile(QQREGEX);
		Matcher matcher = pattern.matcher(qqNumber);
		if (matcher.find()) {
			System.out.println("valid qq number");
		}
	}
}
