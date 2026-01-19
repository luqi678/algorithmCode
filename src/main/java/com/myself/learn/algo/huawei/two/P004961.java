package com.myself.learn.algo.huawei.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * P00496.华为od机试—缓存需要最少金币数_静态扫描_静态代码扫描服务
 * 双机位C卷
 * difficulty:4
 * @author luqi
 */
public class P004961 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            // 缓存消费值
            int m = sc.nextInt();
            sc.nextLine();
            // 扫描文件顺序
            int[] fields = Arrays.stream(sc.nextLine().trim().split("\\s+")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            // 扫描文件花销
            int[] costs = Arrays.stream(sc.nextLine().trim().split("\\s+")).map(String::trim).mapToInt(Integer::parseInt).toArray();

            // 已经缓存过的文件集合
            Set<Integer> cachedFiles = new HashSet<>();
            // 出现过的扫描文件-位置映射
            Map<Integer, List<Integer>> fileMap = new HashMap<>();
            // 读取i个文件最少需要的金币数
            int[] dp = new int[fields.length];
            // 初始化
            dp[0] = costs[0];

            // 动态规划遍历
            for (int i = 1; i < fields.length; i++) {
                int field = fields[i];
                int cost = costs[i];
                // 判断之前是否有扫描过该文件
                List<Integer> indexs = fileMap.get(field);
                if (indexs != null && !indexs.isEmpty()) {
                    if (cachedFiles.contains(field)) {
                        dp[i] = dp[i - 1];
                    }else {
                        int one = dp[i - 1] + cost;
                        int two = dp[i - 1] + m - indexs.size() * cost;
                        if (one < two) {
                            dp[i] = one;
                            fileMap.computeIfAbsent(field, k -> new ArrayList<>()).add(i);
                        }else {
                            dp[i] = two;
                            cachedFiles.add(field);
                        }
                    }
                }else {
                    // 之前无该文件
                    // 选择一：直接扫描
                    dp[i] = dp[i-1] + cost;
                    fileMap.computeIfAbsent(field, k -> new ArrayList<>()).add(i);
                }
            }
            System.out.println(dp[fields.length - 1]);

        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
