package com.myself.learn.algo.huawei.doubleC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * **版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。**
 **/
public class DoubleC_479 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String inputN = sc.nextLine();
        String[] split = inputN.split("\\s+");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        if (!sc.hasNextLine()) {
            return;
        }
        String line = sc.nextLine();
        int[] nums = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
        if (nums.length != n) {
            System.out.println("0");
            return;
        }
        // 前缀和
        long[] subSum = new long[n];
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp += nums[i];
            subSum[i] = temp;
        }
        // 同余定理
        HashMap<Long, List<Integer>> map = new HashMap<>(m);
        map.computeIfAbsent(0L, k -> new ArrayList<>()).add(0);
        for (int i = 0; i < subSum.length; i++) {
            long l = subSum[i];
            long remainder = l % m;
            map.computeIfAbsent(remainder,k -> new ArrayList<>()).add(i);
        }
        boolean isHas = false;
        for (Map.Entry<Long, List<Integer>> entry : map.entrySet()) {
            Long k = entry.getKey();
            List<Integer> v = entry.getValue();
            if (v != null && v.size() > 1) {
                isHas = true;
                break;
            }
        }
        if (!isHas) {
            System.out.println("0");
        }else {
            System.out.println("1");
        }
    }



    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 读取 n 和 m
        if (!sc.hasNext()) return;
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 优化：抽屉原理
        // 如果卡牌数量 n >= m，根据同余定理，必然存在两个前缀和同余，或者某个前缀和余数为0
        // 直接输出 1 即可节省计算
        if (n >= m) {
            System.out.println("1");
            return;
        }

        // 2. 模拟前缀和 + 同余
        // 使用 Set 存储出现过的余数
        Set<Integer> seenRemainders = new HashSet<>();

        // 【关键点】预先加入 0
        // 代表 "前缀和为 0" (虚拟的初始状态)。
        // 如果 calculateSum % m == 0，它会在 set 中找到 0，从而返回 true。
        seenRemainders.add(0);

        long currentSum = 0;
        boolean found = false;

        // 循环读取 n 个数字
        for (int i = 0; i < n; i++) {
            if (sc.hasNextInt()) {
                int num = sc.nextInt();
                currentSum += num;

                // 计算余数
                int remainder = (int) (currentSum % m);

                // 如果余数已存在，说明中间有一段的和是 m 的倍数
                if (seenRemainders.contains(remainder)) {
                    found = true;
                    break; // 找到了就可以直接退出了
                } else {
                    seenRemainders.add(remainder);
                }
            }
        }

        if (found) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }
}