package com.myself.learn.algo.huawei.TE1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 15- 零钱兑换
 * difficulty:中等
 * frequency:高
 * @author luqi
 */
public class TE15 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int amount = sc.nextInt();
            sc.nextLine();
            String line = sc.nextLine().trim();
            int[] coins = Arrays.stream(line.split(",")).map(String::trim).mapToInt(Integer::parseInt).sorted().toArray();
            // dp[] 表示 i 金额下最少的硬币数
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            // 遍历物品
            for (int i = 0; i < coins.length; i++) {
                // 遍历背包容量
                for (int j = coins[i]; j <= amount; j++) {
                    // 状态转移方程
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
            if (dp[amount] == Integer.MAX_VALUE) {
                System.out.println(-1);
            }else {
                System.out.println(dp[amount]);
            }
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
