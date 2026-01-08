package com.myself.learn.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 标准
 * 模板二：回溯 DFS（全排列/组合）
 * 适用于：暴力搜索所有可能结果，如“数字排列”、“凑硬币”、“选课方案”。 特点：做选择 -> 递归 -> 撤销选择。
 */
public class DFSTemple2 {
    // 收集所有结果
    static List<List<Integer>> res = new ArrayList<>();
    // 收集当前路径
    static LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0; i<n; i++) nums[i] = sc.nextInt();

        // 排序通常有助于剪枝（去重）
        Arrays.sort(nums);

        // used数组用于全排列去重
        boolean[] used = new boolean[n];

        dfs(nums, used, 0); // 0 代表 startIndex，组合问题常用

        System.out.println(res);
    }

    /**
     * 回溯模版
     * @param nums 原始数组
     * @param used 标记数组（排列问题需要）
     * @param startIndex 起始位置（组合问题需要，防止回头选）
     */
    public static void dfs(int[] nums, boolean[] used, int startIndex) {
        // 1. 终止条件：收集结果
        if (path.size() == nums.length) { // 举例：全排列长度够了
            res.add(new ArrayList<>(path)); // 注意：必须 new 一个新的 List
            return;
        }

        // 2. 遍历选择列表
        for (int i = 0; i < nums.length; i++) { // 排列问题从 0 开始，组合问题从 startIndex 开始

            // 剪枝逻辑（非常重要，根据题目写）
            if (used[i]) continue;

            // 做选择
            path.add(nums[i]);
            used[i] = true;

            // 进入下一层
            dfs(nums, used, i + 1); // 组合问题传入 i+1，全排列不用

            // 3. 撤销选择（回溯的核心）
            used[i] = false;
            path.removeLast();
        }
    }
}