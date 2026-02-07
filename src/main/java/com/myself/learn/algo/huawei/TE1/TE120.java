package com.myself.learn.algo.huawei.TE1;

import java.util.Map;
import java.util.TreeMap;

/**
 * 120- 多数元素
 * difficulty:中等
 * frequency:中
 * @author luqi
 */
public class TE120 {

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer orDefault = map.getOrDefault(num, 0);
            map.put(num, orDefault + 1);
        }
        int maxCount = 0;
        int maxKey = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value > maxCount) {
                maxCount = value;
                maxKey = key;
            }
        }
        return maxKey;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(new TE120().majorityElement(nums));
    }

}
