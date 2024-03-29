package com.stein.io;

import java.io.*;
import java.util.Scanner;

/**
 * @author njl
 * @date 2023/1/28
 */
public class IO1App {
    public static void main(String[] args) {
        io1();
//		io2();
//		io3();
//		io4();
//        io5();
    }

    /**
     * 基于字符流读取纯文本文件（字符文件）
     */
    public static void io5() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\java-learning\\01-JavaBasic\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\stein\\io\\Java 语言基础 Copy.md"));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\java-learning\\01-JavaBasic\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\stein\\io\\Java 语言基础 Copy2.md"))) {
            int readCount;
            char[] buf = new char[8096];
            while ((readCount = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, readCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 基于字节流读取二进制文件（音频、视频、图片等）
     */
    public static void io4() {
        try (FileInputStream fis = new FileInputStream("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\Queue Hierarchy.png");
             FileOutputStream fos = new FileOutputStream("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\img Copy.png")) {
            byte[] buffer = new byte[2048];
            int readCount;
            while ((readCount = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, readCount);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取控制台输入 - 方式三
     */
    public static void io3() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        System.out.println("line = " + line);
    }

    /**
     * 读取控制台输入 - 方式二
     */
    public static void io2() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        do {
            try {
                line = br.readLine();
                System.out.println("line = " + line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (!line.equals("EOF"));
    }

    /**
     * 读取控制台输入 - 方式一
     */
    public static void io1() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符, 按下 'q' 键退出。");
        char c;
        do {
            try {
                c = (char) bufferedReader.read();
                System.out.print(c);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (c != 'q');
    }
}
