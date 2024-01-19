package com.stein.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author njl
 * @date 2023/2/1
 */
public class StringApp2 {
    public static void main(String[] args) {
//		str1();
        str2();
        System.out.println(int.class);
        System.out.println(Integer.class);
    }

    public static void str2() {
        //new String("ab") 执行完以后，字符串常量池中并没有"ab"
        String s = new String("a") + new String("b");

        //jdk6中：在串池中创建一个字符串"ab"
        //jdk8中：字符串池中没有创建字符串"ab",而是创建一个引用，指向new String("ab")，将此引用返回
        String s2 = s.intern(); // 如果说 intern 是在常量池中创建字符串对象并返回池中的引用的话，那么 s2 不可能和 s 相等。

        System.out.println(s2 == "ab");//jdk6:true  jdk8:true
        System.out.println(s == "ab");//jdk6:false  jdk8:true
        System.out.println(s == s2);//jdk8:true
    }

    /**
     * 字符串存储在 Java 的方法去中（逻辑概念），JDK 6以前，方法区用永久代实现，JDK 7 方法区移到堆内存中，JDK 8 彻底
     * 移除永久代，用元空间替代。
     */
    public static void str1() {
        //使用Set保持着常量池引用，避免full gc回收常量池行为
        Set<String> set = new HashSet<String>();
        //取值的范围内足以让6MB的PermSize或heap产生OOM了。
        long i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
