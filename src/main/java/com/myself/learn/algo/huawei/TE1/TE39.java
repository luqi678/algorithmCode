package com.myself.learn.algo.huawei.TE1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 39- 打家劫舍
 * difficulty:中等
 * frequency:高
 * @author luqi
 */
public class TE39 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int[] numbers = Arrays.stream(sc.nextLine().trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            // dp[]表示在i在偷窃时，偷窃到的最大金额
            int[] dp = new int[numbers.length];
            dp[0] = numbers[0];
            dp[1] = Math.max(numbers[0], numbers[1]);
            // 遍历数组
            for (int i = 2; i < numbers.length; i++) {
                int number = numbers[i];
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + number);
            }
            System.out.println(dp[numbers.length - 1]);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
