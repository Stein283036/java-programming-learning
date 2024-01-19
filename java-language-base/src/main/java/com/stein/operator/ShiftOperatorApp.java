package com.stein.operator;

/**
 * @author njl
 * @date 2023/1/29
 */
public class ShiftOperatorApp {
    public static void main(String[] args) {
//		bit1();
//		bit2();
//		bit3();
        bit4();
    }

    /**
     * 无符号的右移运算，使用>>>，它的特点是不管符号位，右移后高位总是补0，
     * 因此，对一个负数进行>>>右移，它会变成正数，原因是最高位的1变成了0
     */
    public static void bit4() {
        int n = -536870912;
        int a = n >>> 1;  // 01110000 00000000 00000000 00000000 = 1879048192
        int b = n >>> 2;  // 00111000 00000000 00000000 00000000 = 939524096
        int c = n >>> 29; // 00000000 00000000 00000000 00000111 = 7
        int d = n >>> 31; // 00000000 00000000 00000000 00000001 = 1
    }

    /**
     * 对一个负数进行右移，最高位的1不动，结果仍然是一个负数
     */
    public static void bit3() {
        int n = -536870912;
        int a = n >> 1;  // 11110000 00000000 00000000 00000000 = -268435456
        int b = n >> 2;  // 11111000 00000000 00000000 00000000 = -134217728
        int c = n >> 28; // 11111111 11111111 11111111 11111110 = -2
        int d = n >> 29; // 11111111 11111111 11111111 11111111 = -1
    }

    public static void bit2() {
        int n = 7;       // 00000000 00000000 00000000 00000111 = 7
        int a = n >> 1;  // 00000000 00000000 00000000 00000011 = 3
        int b = n >> 2;  // 00000000 00000000 00000000 00000001 = 1
        int c = n >> 3;  // 00000000 00000000 00000000 00000000 = 0
    }

    public static void bit1() {
        // in Java, numbers are stored in memory in its complement form
        // 一个数左移 一位 相当于 × 2
        // 一个数右移 一位 相当于 / 2
        int n = 7;       // 00000000 00000000 00000000 00000111 = 7
        int a = n << 1;  // 00000000 00000000 00000000 00001110 = 14
        int b = n << 2;  // 00000000 00000000 00000000 00011100 = 28
        int c = n << 28; // 01110000 00000000 00000000 00000000 = 1879048192
        int d = n << 29; // 11100000 00000000 00000000 00000000 = -536870912
    }
}