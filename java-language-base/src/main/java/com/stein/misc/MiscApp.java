package com.stein.misc;

/**
 * @author njl
 * @date 2023/1/29
 */
public class MiscApp {
    public static void main(String[] args) {
//		misc3();
//		misc4();
//		misc5();
//		misc6();
//		misc7();
        misc8();
    }

    /**
     * 包装类的缓存机制
     */
    public static void misc8() {
        Integer i1 = 100;
        Integer i2 = 100;
        System.out.println(i1 == i2);

        Integer i3 = 200;
        Integer i4 = 200;
        System.out.println(i3 == i4);
    }

    public static void misc7() {
        int abs = Math.abs(-127);
        assert abs >= 0 : "abs must >= 0";
        System.out.println("abs = " + abs);

        int x = -1;
        assert x > 0;
        System.out.println("x = " + x);
    }

    public static void misc6() {
        System.out.println(Integer.toString(100)); // "100",表示为10进制
        System.out.println(Integer.toString(100, 36)); // "2s",表示为36进制
        System.out.println(Integer.toHexString(100)); // "64",表示为16进制
        System.out.println(Integer.toOctalString(100)); // "144",表示为8进制
        System.out.println(Integer.toBinaryString(100)); // "1100100",表示为2进制

        // boolean只有两个值true/false，其包装类型只需要引用Boolean提供的静态字段:
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
        // int可表示的最大/最小值:
        int max = Integer.MAX_VALUE; // 2147483647
        int min = Integer.MIN_VALUE; // -2147483648
        // long类型占用的bit和byte数量:
        int sizeOfLong = Long.SIZE; // 64 (bits)
        int bytesOfLong = Long.BYTES; // 8 (bytes)
        // 因为byte的-1的二进制表示是11111111，以无符号整型转换后的int就是255。
        System.out.println(Byte.toUnsignedInt((byte) -1));
        // 类似的，可以把一个short按unsigned转换为int，把一个int按unsigned转换为long。
        // Short.toUnsignedInt()
        // Integer.toUnsignedLong()
    }

    public static void misc5() {
        double d = 12900000;
        System.out.println(d); // 1.29E7
    }

    public static void misc4() {
        int a = 72;
        int b = 105;
        int c = 65281;

        String s = "" + (char) a + (char) b + (char) c;
        System.out.println("s = " + s);
    }

    public static void misc3() {
        String s = "abc\"xyz"; // 包含7个字符: a, b, c, ", x, y, z
        System.out.println(s.length());
        // 因为\是转义字符，所以，两个\\表示一个\字符
        String s2 = "abc\\xyz"; // 包含7个字符: a, b, c, \, x, y, z
        System.out.println(s2.length());
    }

    public static void misc2() {
        // 要显示一个字符的Unicode编码，只需将char类型直接赋值给int类型即可
        int n1 = 'A'; // 字母“A”的Unicode编码是65
        int n2 = '中'; // 汉字“中”的Unicode编码是20013
        // 可以直接用转义字符\ u+Unicode编码来表示一个字符
        char c3 = '\u0041'; // 'A'，因为十六进制0041 = 十进制65
        char c4 = '\u4e2d'; // '中'，因为十六进制4e2d = 十进制20013
    }

    public static void misc1() {
        double d1 = 0.0 / 0; // NaN
        System.out.println("d1 = " + d1);
        double d2 = 1.0 / 0; // Infinity
        System.out.println("d2 = " + d2);
        double d3 = -1.0 / 0; // -Infinity
        System.out.println("d3 = " + d3);
    }
}
