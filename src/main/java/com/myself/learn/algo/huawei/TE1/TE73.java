package com.myself.learn.algo.huawei.TE1;

import java.util.Arrays;

/**
 * 73- 除自身以外数组的乘积
 * difficulty:中等
 * frequency:中
 * @author luqi
 */
public class TE73 {

    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int left = 1;
            for (int j = 0; j < i; j++) {
                left *= nums[j];
            }
            int right = 1;
            for (int j = i + 1; j < nums.length; j++) {
                right *= nums[j];
            }
            res[i] = left * right;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
    }

}
