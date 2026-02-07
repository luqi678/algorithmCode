package com.myself.learn.algo.huawei.TE1;

import java.util.Arrays;

/**
 * 121- K 和数对的最大数目
 * difficulty:中等
 * frequency:中
 * @author luqi
 */
public class TE121 {

    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while (left < right) {
            if (nums[left] + nums[right] < k) {
                left++;
            } else if (nums[left] + nums[right] > k) {
                right--;
            } else {
                left++;
                right--;
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {

        TE121 t = new TE121();
        System.out.println(t.maxOperations(new int[]{1,2,2,3,3,4}, 5));
    }
}
