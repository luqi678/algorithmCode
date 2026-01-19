package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.Scanner;

/**
 * P00307.华为od机试—分披萨
 * 2025C卷
 * difficulty:4
 * @author luqi
 */
public class P00307 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static int[] nums;
    static long[][] cache;

    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            // 披萨块大小
            nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            // 定义一个缓存减少计算时间
            cache = new long[n][n];
            for (int i = 0; i < cache.length; i++) {
                Arrays.fill(cache[i], -1);
            }
            // 披萨总和
            long maxSum = 0;
            // 任选一块作为开始
            for (int i = 0; i < n; i++) {
                maxSum = Math.max(maxSum, nums[i] + getMax((i + 1 + nums.length) % nums.length, (i - 1 + nums.length) % nums.length));
            }

            System.out.println(maxSum);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }


    /**
     * 在left ~ right 中，找到能获取到的最大的披萨总量
     * @param left 索引
     * @param right 索引
     * @return 披萨总量
     */
    static long getMax(int left, int right) {
        long sum = 0;

        // B先吃一个边界值最大的，然后在剩余中找最大的
        if (nums[left] > nums[right]) {
            left = (left + 1) % nums.length;
        }else {
            right = (right - 1 + nums.length) % nums.length;
        }

        // 只剩下一个直接返回
        if (left == right) {
            return nums[left];
        }
        if (cache[left][right] != -1) {
            return cache[left][right];
        }

        // 选左边或者选右边
        sum = Math.max(nums[right] + getMax(left, (right - 1 + nums.length) % nums.length)
                , nums[left] +  getMax((left + 1) % nums.length, right));
        cache[left][right] = sum;

        return sum;
    }
}
