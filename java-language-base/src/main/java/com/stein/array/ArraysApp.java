package com.stein.array;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author njl
 * @date 2023/1/28
 */
public class ArraysApp {
    public static void main(String[] args) {
        arrays1();
        arrays2();
        array3();
    }

    public static void array3() {
        Double[] doubles = new Double[10];
        doubles[0] = 1.2;
        doubles[1] = 2.2;
        doubles[2] = 8.2;
        doubles[3] = 4.2;
        doubles[4] = 7.2;
        doubles[5] = 6.2;
        doubles[6] = 5.2;
        doubles[7] = 4.2;
        doubles[8] = 9.2;
        doubles[9] = 10.2;

        Arrays.sort(doubles);
        System.out.println("升序:");
        for (double emp : doubles) {
            System.out.println(emp);
        }

        Arrays.sort(doubles, Collections.reverseOrder());
        System.out.println("降序:");
        for (double emp : doubles) {
            System.out.println(emp);
        }
    }

    public static void arrays2() {
        int[] a = {10, 20, 30, 40, 50};
        a = Arrays.copyOf(a, a.length + 1); // 底层调用的是 System.arraycopy 方法
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void arrays1() {
        int[] array = new int[5];

        // 填充数组
        Arrays.fill(array, 5);
        System.out.println("填充数组：Arrays.fill(array, 5)：");
        System.out.println("array = " + Arrays.toString(array));

        // 将数组的第2和第3个元素赋值为8
        Arrays.fill(array, 2, 4, 8);
        System.out.println("将数组的第2和第3个元素赋值为8：Arrays.fill(array, 2, 4, 8)：");
        System.out.println("array = " + Arrays.toString(array));

        int[] array1 = {7, 8, 3, 2, 12, 6, 3, 5, 4};
        // 对数组的第2个到第6个进行排序进行排序
        // java.util.Arrays.sort 只可以用于按升序排列数组，若按降序排序可以使用 Arrays.sort(数组,Collections.reverseOrder())
        Arrays.sort(array1, 2, 7);
        System.out.println("对数组的第2个到第6个元素进行排序进行排序：Arrays.sort(array,2,7)：");
        System.out.println("array1 = " + Arrays.toString(array1));

        // 对整个数组进行排序
        Arrays.sort(array1);
        System.out.println("对整个数组进行排序：Arrays.sort(array1)：");
        System.out.println("array1 = " + Arrays.toString(array1));
        // 比较数组元素是否相等
        System.out.println("比较数组元素是否相等:Arrays.equals(array, array1):" + "\n" + Arrays.equals(array, array1));
        int[] array2 = array1.clone();
        System.out.println("克隆后数组元素是否相等:Arrays.equals(array1, array2):" + "\n" + Arrays.equals(array1, array2));
        // 使用二分搜索算法查找指定元素所在的下标（必须是排序好的，否则结果不正确）
        Arrays.sort(array1);
        System.out.println("元素3在array1中的位置：Arrays.binarySearch(array1, 3)：" + "\n" + Arrays.binarySearch(array1, 3));
        // 如果不存在就返回负数
        System.out.println("元素9在array1中的位置：Arrays.binarySearch(array1, 9)：" + "\n" + Arrays.binarySearch(array1, 9));
    }
}
