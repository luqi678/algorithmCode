package com.myself.learn.algo.huawei.TE1;

import java.util.Arrays;

/**
 * 117- 最长递增子序列
 * difficulty:中等
 * frequency:中
 * @author luqi
 */
public class TE117 {

    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i] 表示以 nums[i] 结尾的最长递增子序列的长度
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int maxDp = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxDp = Math.max(maxDp, dp[j]);
                }
            }
            dp[i] = maxDp + 1;
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 3, 4, 5, 2, 3, 7, 101, 18};
//        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }

}
