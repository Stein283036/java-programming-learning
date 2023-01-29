package com.guhe.oop;

/**
 * @author njl
 * @date 2023/1/29
 */
public class OOPApp {
	private String s1 = "str1";

	public static void main(String[] args) {
		oop1();
	}

	public void oop2() {
	}

	public static void oop1() {
		Father father = new Son();
	}

}

class Father {
	static {
		System.out.println("1 - Father static block");
	}

	public static final String FATHER = "2 - father";

	private String father = "5 - Father father";

	{
		System.out.println("6 - Son instance block");
	}

	public Father() {
		System.out.println("7 - Father constructor");
	}
}

class Son extends Father {
	public static final String SON = "3 - son";

	static {
		System.out.println("4 - Son static block");
	}

	{
		System.out.println("8 - Son instance block");
	}

	private String son = "9 - Son son";

	public Son() {
		System.out.println("10 - Son constructor");
	}
}