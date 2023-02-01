package com.guhe.enumeration;

/**
 * @author njl
 * @date 2023/1/29
 */
public class EnumApp {
	public static void main(String[] args) {
//		enum1();
//		enum2();
	}

	public static void enum2() {
		AbstractEnum[] enums = AbstractEnum.values();
		for (AbstractEnum anEnum : enums) {
			anEnum.bar();
		}
	}

	public static void enum1() {
		Color[] colors = Color.values();
		for (Color color : colors) {
			System.out.println("color = " + color);
			System.out.println(color.ordinal());
			color.colorInfo();
		}
	}
}

