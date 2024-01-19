package com.stein.io;

import java.io.File;
import java.io.IOException;

/**
 * @author njl
 * @date 2023/1/29
 */
public class FileApp {
    public static void main(String[] args) throws IOException {
//		deleteFolder(new File("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\a"));
        fileNameFilter();
    }

    /**
     * 只查看以 .java 结尾的文件名
     */
    public static void fileNameFilter() {
        File file = new File("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io");
        String[] allFiles = file.list();
        assert allFiles != null;
        for (String allFile : allFiles) {
            System.out.println("allFile = " + allFile);
        }
        String[] javaFiles = file.list(((dir, name) -> name.endsWith(".java")));
        assert javaFiles != null;
        for (String javaFile : javaFiles) {
            System.out.println("javaFile = " + javaFile);
        }
    }

    /**
     * 递归删除非空目录
     */
    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
    }

    /**
     * File 类的常用方法
     */
    public static void file1() throws IOException {
        File file1 = new File("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\Java 语言基础 Copy.md");
        System.out.println(file1.getName() + "文件或目录存在么？ " + file1.exists());
        System.out.println(file1.getName() + "是文件么？ " + file1.isFile());
        System.out.println(file1.getName() + "是目录么？ " + file1.isDirectory());
        System.out.println(file1.getName() + "是可读的么？ " + file1.canRead());
        System.out.println(file1.getName() + "是可写的么？ " + file1.canWrite());
        System.out.println(file1.getName() + "是可执行的么？ " + file1.canExecute());
        boolean deleteFlag = file1.delete(); // 删除文件，如果是目录的话，必须是空目录才能删除
        System.out.println("deleteFlag = " + deleteFlag);
        boolean newFile = file1.createNewFile(); // 文件不存在时创建新的文件
        System.out.println("newFile = " + newFile);
    }
}
