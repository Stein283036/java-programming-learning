package com.stein.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author njl
 * @date 2023/1/28
 */
public class RegexApp {
    public static void main(String[] args) {
//		regex1();
//		regex2();
//		regex3();
//		regex4();
//		regex5();
        regex6();
    }

    /**
     * appendReplacement 和 appendTail 方法
     * Matcher 类也提供了appendReplacement 和 appendTail 方法用于文本替换
     */
    public static void regex6() {
        String REGEX = "a*b";
        String INPUT = "aabfooaabfooabfoobkkk";
        String REPLACE = "-";

        Pattern p = Pattern.compile(REGEX);
        // 获取 matcher 对象
        Matcher m = p.matcher(INPUT);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, REPLACE);
        }
        m.appendTail(sb);
        System.out.println(sb.toString());

    }

    /**
     * replaceFirst 和 replaceAll 方法
     * replaceFirst 和 replaceAll 方法用来替换匹配正则表达式的文本。不同的是，replaceFirst 替换首次匹配，replaceAll 替换所有匹配。
     */
    public static void regex5() {
        final String REGEX = "dog";
        final String INPUT = "The dog says meow. " +
                "All dogs say meow.";
        final String REPLACE = "cat";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(INPUT);
//		String inputAfterReplaceAll = matcher.replaceAll(REPLACE); // replace all 'dog' to 'cat'
        String inputAfterReplaceFirst = matcher.replaceFirst(REPLACE); // replace the first matched 'dog' to 'cat'
//		System.out.println("inputAfterReplaceAll = " + inputAfterReplaceAll);
        System.out.println("inputAfterReplaceFirst = " + inputAfterReplaceFirst);
    }

    /**
     * matches 和 lookingAt 方法
     * matches 和 lookingAt 方法都用来尝试匹配一个输入序列模式。它们的不同是 matches 要求整个序列都匹配，而lookingAt 不要求。
     * <p>
     * lookingAt 方法虽然不需要整句都匹配，但是需要从第一个字符开始匹配。
     * <p>
     * 这两个方法经常在输入字符串的开始使用。
     */
    public static void regex4() {
        final String REGEX = "foo";
        final String INPUT = "fooooooooooooooooo";
        final String INPUT2 = "oooooooofoooooooo";

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(INPUT);
        Matcher matcher2 = pattern.matcher(INPUT2);

        System.out.println("Current REGEX is: " + REGEX);
        System.out.println("Current INPUT is: " + INPUT);
        System.out.println("Current INPUT2 is: " + INPUT2);

        System.out.println("matcher lookingAt: " + matcher.lookingAt()); // true
        System.out.println("matcher matches: " + matcher.matches()); // false
        System.out.println("matcher2 lookingAt: " + matcher2.lookingAt()); // false
    }

    /**
     * start 和 end 方法
     * 对单词 "cat" 出现在输入字符串中出现次数进行计数的例子
     */
    public static void regex3() {
        final String REGEX = "\\bcat\\b";
        final String INPUT = "cat cat cat cattie cat";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(INPUT);
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("Matcher count : " + count);
            System.out.println("Matcher find start: " + matcher.start());
            System.out.println("Matcher find end : " + matcher.end());
        }
		/*
		可以看到这个例子是使用单词边界，以确保字母 "c" "a" "t" 并非仅是一个较长的词的子串。它也提供了一些关于输入字符串中匹配发生位置的有用信息。
		start 方法返回在以前的匹配操作期间，由给定组所捕获的子序列的初始索引，end 方法最后一个匹配字符的索引加 1。
		 */
    }

    /**
     * 正则匹配分组
     */
    public static void regex2() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("total group count: " + m.groupCount());
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
    }

    public static void regex1() {
        String toBeTestedStr = "I want to be tested.";
        String regex = ".*want to.*";
        boolean match = Pattern.matches(regex, toBeTestedStr);
        System.out.println("match = " + match);
    }
}
