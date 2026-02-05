package com.myself.learn.algo.huawei.TE1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 35- 你可以获得的最大硬币数目
 * difficulty:中等
 * frequency:高
 * @author luqi
 */
public class TE35 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int amount = sc.nextInt();
            sc.nextLine();
            int[] coins = Arrays.stream(sc.nextLine().trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            // 多背包问题
            int[] dp = new int[amount + 1];
            // 遍历物品
            for (int i = 0; i < coins.length; i++) {
                int coin = coins[i];
                // 遍历背包容量
                for (int j = coin; j < amount + 1; j++) {
                    dp[j] = Math.max(dp[j], dp[j - coin] + 1);
                }
            }
            System.out.println(dp[amount]);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
