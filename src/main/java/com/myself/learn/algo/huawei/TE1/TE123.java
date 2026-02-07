package com.myself.learn.algo.huawei.TE1;

import java.util.Arrays;

/**
 * 123- 最长公共子序列
 * difficulty:中等
 * frequency:中
 * @author luqi
 */
public class TE123 {

    public static int LCS(String str1, String str2) {
        // dp[i][j] 表示 str1 的 i 位置和 str2 的 j 位置的公共子序列的长度
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < dp.length ; i++) {
            dp[i][0] = 0;
        }
        Arrays.fill(dp[0], 0);
        // 状态转移方程
        // 当str1[i] == str2[j] 时，dp[i][j] = dp[i-1][j-1] + 1
        // 当str1[i] != str2[j] 时，dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])
        for (int i = 1; i < str1.length() + 1; i++) {
            char char1 = str1.charAt(i - 1);
            for (int j = 1; j < str2.length() + 1; j++) {
                char char2 = str2.charAt(j - 1);
                if (char1 == char2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }


    public static void main(String[] args) {
        System.out.println(LCS("1A2C3D4B56", "B1D23CA45B6A"));
        System.out.println(LCS("abcde", "ace"));
        System.out.println(LCS("abc", "def"));
    }
}
