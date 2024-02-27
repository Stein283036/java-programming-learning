package com.stein.utils;

import java.math.BigInteger;

/**
 * @author njl
 * @date 2023/1/29
 */
public class BigIntegerApp {
    public static void main(String[] args) {
//		biginteger1();
        biginteger2();
    }

    public static void biginteger2() {
        BigInteger bi = new BigInteger("2867971860299718107233761438093672048294900000");
        // 如果BigInteger表示的范围超过了基本类型的范围，转换时将丢失高位信息，即结果不一定是准确的
        long l1 = bi.longValue();
        System.out.println("l1 = " + l1);
        // 如果需要准确地转换成基本类型，可以使用intValueExact()、longValueExact()等方法，在转换时如果超出范围，将直接抛出ArithmeticException异常。
        long l2 = bi.longValueExact();
        System.out.println("l2 = " + l2);
    }

    public static void biginteger1() {
        BigInteger bi = new BigInteger("2867971860299718107233761438093672048294900000");
        System.out.println(bi.pow(5));
    }
}
