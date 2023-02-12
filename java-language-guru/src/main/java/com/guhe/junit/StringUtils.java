package com.guhe.junit;

/**
 * @author njl
 * @date 2023/2/7
 */
public class StringUtils {
	public static String capitalize(String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
	}
}
