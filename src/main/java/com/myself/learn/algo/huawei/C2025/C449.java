package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class C449 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            return;
        }
        int D = sc.nextInt();
        if (!sc.hasNextInt()) {
            System.out.println(D / 100);
            return;
        }
        int N = sc.nextInt();

        // 存储站点信息：[距离, 排队时间]
        int[][] stations = new int[N + 1][2];
        stations[0][0] = 0; // 起点
        stations[0][1] = 0;
        for (int i = 1; i <= N; i++) {
            stations[i][0] = sc.nextInt();
            stations[i][1] = sc.nextInt();
        }

        // dp[i] 表示在第 i 个休息站充电完后的最小总额外时间
        long[] dp = new long[N + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        // 单调队列，存储索引，对应 dp 值单调递增
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(Integer.valueOf(0));

        for (int i = 1; i <= N; i++) {
            // 1. 移除超出续航范围（1000km）的站点
            while (!deque.isEmpty() && stations[i][0] - stations[deque.peekFirst()][0] > 1000) {
                deque.pollFirst();
            }

            // 2. 如果队列为空，说明无法到达当前站点
            if (deque.isEmpty()) {
                dp[i] = -1;
            } else {
                // 3. 转移：当前站点的额外时间 = 最小前置dp + 排队时间 + 1
                int bestJ = deque.peekFirst();
                dp[i] = dp[bestJ] + stations[i][1] + 1;

                // 4. 将当前 dp[i] 加入单调队列
                while (!deque.isEmpty() && dp[deque.peekLast()] >= dp[i]) {
                    deque.pollLast();
                }
                deque.offerLast(Integer.valueOf(i));
            }
        }

        // 5. 计算到终点的最小耗时
        long minExtra = Long.MAX_VALUE;
        boolean reachable = false;

        // 检查直接从哪些站点（包括起点）可以到达终点
        for (int i = 0; i <= N; i++) {
            if (dp[i] != -1 && D - stations[i][0] <= 1000) {
                minExtra = Math.min(minExtra, dp[i]);
                reachable = true;
            }
        }

        if (!reachable) {
            System.out.println("-1");
        } else {
            System.out.println(D / 100 + minExtra);
        }
    }
}