package com.guhe.operator;

/**
 * 运算符练习
 *
 * @author njl
 * @date 2023/1/26
 */
public class OperatorApp {
	public static void main(String[] args) {
		bitOperator();
	}

	public static void bitOperator() {
		// 假设 a = 60，b = 13
		// A = 0011 1100
		// B = 0000 1101
		int a = 60;
		int b = 13;

		// & - 如果相对应位都是 1，则结果为 1，否则为 0
		System.out.println(a & b); // 0000 1100 - 12
		// | - 如果相对应位都是 0，则结果为 0，否则为 1
		System.out.println(a | b); // 0011 1101 - 61
		// ^ 如果相对应位值相同，则结果为 0，否则为 1
		System.out.println(a ^ b); // 0011 0001 - 49
		// ~ 按位取反运算符翻转操作数的每一位，即 0 变成 1，1 变成 0
		System.out.println(Integer.toBinaryString(~a));
		System.out.println(~a); // (111111111111111111111111)1100 0011 - -61
		// 负数以补码的形式在计算机中存储，因此先将补码转换成原码（负数的绝对值），再加上符号就能求出真值了
		// << - 按位左移运算符。左操作数按位左移右操作数指定的位数
		System.out.println(a << 2); // 1111 0000 - 240
		// >> - 按位右移运算符。左操作数按位右移右操作数指定的位数
		System.out.println(Integer.toBinaryString(a >> 2)); // 1111
		System.out.println(a >> 2); // 1111 - 15
		// >>> - 按位右移补零操作符。左操作数的值按右操作数指定的位数右移，移动得到的空位以零填充
		// >>操作对左边空位补符号位，>>>对左边空位补0
		System.out.println(Integer.toBinaryString(a >>> 2));
		System.out.println(a >>> 2); // 0000 1111 - 15
	}
}
