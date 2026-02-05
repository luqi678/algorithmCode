package com.myself.learn.algo.huawei.TE1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 13- 单词拆分
 * difficulty:中等
 * frequency:中
 * @author luqi
 */
public class TE13 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            String s = sc.nextLine().trim();
            String[] words = sc.nextLine().trim().split("\\s+");
            // 使用hashSet存储words
            Set<String> wordSet = new HashSet<>(Arrays.asList(words));
            //dp[i]表示i个字符前是否能全部拆分为 words中的单词
            // 状态转移方程：dp[i] =dp[j]&&wordSet.contains(s.substring(j,i))
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    dp[i] = dp[j] && wordSet.contains(s.substring(j, i));
                    if (dp[i]) break;
                }
            }
            System.out.println(dp[s.length()]);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
