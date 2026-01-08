package com.myself.learn.algo.huawei.doubleC;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 分月饼
 * 中秋节，公司分月饼，m个员工，买了n个月饼，m<=n，每个员工至少分1个月饼，但可以分多个，单人份到最多月饼的个数为Max1，单人分到第二多月饼的个数是Max2，Max1-Max2<=3,。同理，单人分到第n-1多月饼的个数是Max(n-1)，单人分到第n多月饼的个数是Max(n)，Max(n-1)-Max(n)<=3。请问有多少种分月饼的方法？
 *
 * 输入描述：
 *
 * 第一行输入m n，表示m个员工，n个月饼，m<=n
 *
 * 输出描述：
 *
 * 输出有多少种月饼分法
 *
 * 示例1：
 *
 * 输入：
 *
 * 2 4
 *
 * 输出：
 *
 * 2
 *
 * 说明：
 *
 * 4 = 1+3 （和3+1算一种分法）
 *
 * 4 = 2+2
 * 共两种方案
 **/
public class DoubleC_491 {

    // 员工数
    static int m;
    // 月饼数
    static int n ;
    // 结果
    static int res = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String input = sc.nextLine();
        int[] ints = Arrays.stream(input.split("\\s+")).mapToInt(Integer::parseInt).toArray();
        if (ints.length != 2) {
            System.out.println("Error");
            return;
        }
        // 员工数
        m = ints[0];
        // 月饼数
        n = ints[1];
        // 初始调用
        backtrack(1, n, n);
        System.out.println(res);
    }

    /**
     * DFS
     * @param i 第i个人分月饼
     * @param remain 剩余月饼数
     * @param pre 上个人分的月饼数
     */
    private static void backtrack(int i, int remain, int pre) {
        // 结束条件
        if (i==m) {
            if (remain >= 1) {
                if (i == 1) {
                    res++;
                } else if (remain <= pre && (pre - remain) <= 3) {
                    res++;
                }
            }
            return;
        }

        // 剪枝 求本次能够分月饼max ， min 值

        int max = Math.min(remain - (m - i), pre);
        int min = Math.max(1, pre - 3);
        if (i==1) {
            max = remain - (m - i);
            min = 1;
        }else {
            max = Math.min(remain - (m - i), pre);
            min = Math.max(1, pre - 3);
        }

        // 递归
        for (int num = min; num <= max; num++) {
            if (remain < m -i) {
                continue;
            }
            if (num * (m - i) < remain - num) {
                continue;
            }
            backtrack(i+1, remain-num, num);
        }

    }
}