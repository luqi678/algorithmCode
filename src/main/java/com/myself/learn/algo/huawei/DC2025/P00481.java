package com.myself.learn.algo.huawei.DC2025;

import java.util.Scanner;

/**
 * P00481.华为od机试—最佳升级时间窗
 * 双机位C卷
 * difficulty:4
 * @author luqi
 */
public class P00481 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            // 最佳升级时间窗口的天数
            int maxTimes = 0;
            int firstIndex = -1;
            int n = sc.nextInt();
            int[] arr = new int[168 * 2];
            for (int i = 0; i < 168 * 2; i++) {
                if (i < 168) {
                    arr[i] = sc.nextInt();
                }else {
                    arr[i] = arr[i - 168];
                }
            }
            // 滑动窗口内的访问量
            int sum = 0;
            int left = 0;
            for (int right = 0; right < arr.length; right++) {
                sum += arr[right];
                while (sum > n && left < right) {
                    sum -= arr[left];
                    left++;
                }
                if (right - left > maxTimes) {
                    maxTimes = right - left;
                    firstIndex = left;
                }
            }


            System.out.println(firstIndex % 168 + " " + (firstIndex + maxTimes) % 168);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
