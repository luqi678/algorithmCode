package com.myself.learn.algo.huawei.TE1;

/**
 * 45- 按摩师
 * difficulty:简单
 * frequency:中
 * @author luqi
 */
public class TE45 {


    public static int maxValue(int[] nums) {
        // dp 表示 i 天内工作获得的最大利益
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = dp[0] + nums[1];
        dp[2] = dp[1] + nums[2];
        // 状态转移方程
        // 遍历阶段
        for (int i = 3; i < nums.length; i++) {
            // 休息
            int rest = dp[i - 1];
            // 工作
            int work = (i >= 4 ? dp[i - 4] : 0) + nums[i] + nums[i - 1] + nums[i - 2] + nums[i - 3] -
                    Math.min(nums[i - 1], Math.min(nums[i - 2], nums[i - 3]));
            dp[i] = Math.max(rest, work);
        }
        return dp[nums.length - 1];
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        System.out.println(maxValue(nums));
    }
}
