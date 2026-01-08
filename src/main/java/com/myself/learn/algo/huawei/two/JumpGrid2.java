package com.myself.learn.algo.huawei.two;

import java.util.Scanner;

/**
 * 跳格子 2
 * @author 2405051
 */
public class JumpGrid2 {
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String input = sc.nextLine();
        String[] itemStrings = input.split("\\s+");
        Integer[] items = new Integer[itemStrings.length];
        for (int i = 0; i < itemStrings.length; i++) {
            items[i] = Integer.parseInt(itemStrings[i].trim());
        }
        // 1. 特殊情况处理：只有一个格子
        if (items.length == 1) {
            System.out.println(items[0]);
            return;
        }

        // 是环 所以分为两次考虑 1到n-1 ， 0到n-2 两次动态规划
        // 0到n-2
        //   到达该格子
        int t = items[0];
        //   不到达该格子
        int n = 0;
        for (int i = 1; i < items.length - 1; i++) {
            int n_old = n;
            int t_old = t;
            n = Math.max(n_old,t_old);
            t = n_old + items[i];
        }
        int max_one = Math.max(t,n);

        // 1到n-1
        t = items[1];
        n = 0;
        for (int i = 2; i < items.length; i++) {
            int n_old = n;
            int t_old = t;
            n = Math.max(n_old,t_old);
            t = n_old + items[i];
        }
        int max_two = Math.max(t,n);
        System.out.println(Math.max(max_one,max_two));

    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String input = sc.nextLine();
        // 健壮性处理：去除可能的首尾空格
        String[] itemStrings = input.trim().split("\\s+");
        Integer[] items = new Integer[itemStrings.length];
        for (int i = 0; i < itemStrings.length; i++) {
            items[i] = Integer.parseInt(itemStrings[i]);
        }

        // 1. 特殊情况处理：只有一个格子
        if (items.length == 1) {
            System.out.println(items[0]);
            return;
        }

        // 2. 环形处理：分为两种情况取最大值
        // 情况一：考虑范围 [0, n-2] (包含第一个，一定不包含最后一个)
        int max_one = robRange(items, 0, items.length - 2);

        // 情况二：考虑范围 [1, n-1] (不包含第一个，可能包含最后一个)
        int max_two = robRange(items, 1, items.length - 1);

        System.out.println(Math.max(max_one, max_two));
    }

    // 将动态规划逻辑抽取为方法，代码更简洁复用
    private static int robRange(Integer[] nums, int start, int end) {
        if (start > end) return 0;
        if (start == end) return nums[start];

        // t 代表偷当前格子的最大收益 (take)
        int t = nums[start];
        // n 代表不偷当前格子的最大收益 (no take)
        int n = 0;

        for (int i = start + 1; i <= end; i++) {
            int n_old = n;
            int t_old = t;

            // 不偷当前格子 i：前一个格子偷或者不偷都行，取最大值
            n = Math.max(n_old, t_old);

            // 偷当前格子 i：前一个格子必须没偷 (n_old)
            t = n_old + nums[i];
        }
        return Math.max(t, n);
    }
}