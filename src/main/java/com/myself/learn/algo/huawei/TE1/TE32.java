package com.myself.learn.algo.huawei.TE1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 32- 两数之和 II - 输入有序数组
 * difficulty:简单
 * frequency:高
 * @author luqi
 */
public class TE32 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            sc.nextLine();
            int[] numbers = Arrays.stream(sc.nextLine().trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            for (int i = 0; i < numbers.length; i++) {
                int number = numbers[i];
                int target = n - number;
                if (target > number) {
                    // 二分查找
                    int index = halfSearch(numbers, i + 1, target);
                    if (index != -1) {
                        System.out.println((i + 1) + " " + (index + 1));
                        return;
                    }
                }
            }
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static int halfSearch(int[] numbers, int i, int target) {
        int left = i,right = numbers.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] == target) {
                return mid;
            } else if (numbers[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
