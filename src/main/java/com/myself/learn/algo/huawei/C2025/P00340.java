package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.Scanner;

/**
 * P00340.华为od机试—水果摊小买卖
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class P00340 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            // 物品容量
            int[] wets = Arrays.stream(sc.nextLine().trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            // 物品价值
            int[] sells = Arrays.stream(sc.nextLine().trim().split(",")).map(String::trim).mapToInt(Integer::parseInt)
                    .toArray();
            // 物品价值
            int[] values = new int[wets.length];
            for (int i = 0; i < sells.length; i++) {
                values[i] = sells[i] - wets[i];
            }


            // 背包容量
            int weight = sc.nextInt();
            int n = wets.length ;
            int m = weight + 1;

            // 用0/1 背包问题解决
            int[] dp = new int[m];
            for (int i = 1; i < n; i++) {
                for (int j = weight; j > wets[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j-wets[i]] + values[i]);
                }
            }

            System.out.println(dp[weight]);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
