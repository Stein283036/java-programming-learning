package com.stein.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * @author njl
 * @date 2023/1/29
 */
public class BigDecimalApp {
    public static void main(String[] args) {
		bigdecimal1();
		bigdecimal3();
		bigdecimal4();
        bigdecimal5();
    }

    /**
     * 对BigDecimal做除法的同时求余数
     */
    public static void bigdecimal5() {
        BigDecimal b1 = new BigDecimal("12.345");
        BigDecimal b2 = new BigDecimal("0.12");
        BigDecimal[] divideAndRemainder = b1.divideAndRemainder(b2);
        System.out.println("divideAndRemainder = " + Arrays.toString(divideAndRemainder));
    }

    /**
     * 对BigDecimal做加、减、乘时，精度不会丢失，但是做除法时，存在无法除尽的情况，这时，就必须指定精度以及如何进行截断
     */
    public static void bigdecimal4() {
        BigDecimal d1 = new BigDecimal("123.456");
        BigDecimal d2 = new BigDecimal("23.456789");
//		System.out.println(d1.divide(d2)); // 除不尽，报错
        System.out.println(d1.divide(d2, 10, RoundingMode.HALF_UP)); // 结果保留10位小数，四舍五入
    }

    /**
     * 通过BigDecimal的stripTrailingZeros()方法，可以将一个BigDecimal格式化为一个相等的，但去掉了末尾0的BigDecimal
     */
    public static void bigdecimal3() {
        BigDecimal d1 = new BigDecimal("123.4500");
        BigDecimal d2 = d1.stripTrailingZeros();
        System.out.println(d1.scale()); // 4
        System.out.println(d2.scale()); // 2,因为去掉了00

        BigDecimal d3 = new BigDecimal("1234500");
        BigDecimal d4 = d3.stripTrailingZeros();
        System.out.println(d3.scale());
        System.out.println(d4.scale());
    }

    /**
     * BigDecimal用scale()表示小数位数
     */
    public static void bigdecimal2() {
        BigDecimal d1 = new BigDecimal("123.45");
        BigDecimal d2 = new BigDecimal("123.4500");
        BigDecimal d3 = new BigDecimal("1234500");
        System.out.println(d1.scale()); // 2,两位小数
        System.out.println(d2.scale()); // 4
        System.out.println(d3.scale()); // 0
    }

    public static void bigdecimal1() {
        BigDecimal bd = new BigDecimal("123.4567");
        System.out.println(bd.multiply(bd)); // 15241.55677489
    }
}
