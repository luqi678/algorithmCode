package com.myself.learn.temp;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * 单调栈模板
 * @author 2405051
 */
public class MonotonicStackTemp {
    public static void main(String[] args) {
        // 标准输入处理
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNext()) return;

        // 1. 读取数据（兼容 OD 常见的单行空格分隔输入）
        // 也可以根据题目换成 int n = sc.nextInt(); int[] nums = ...
        String[] parts = sc.nextLine().trim().split("\\s+");
        int[] nums = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();

        // 2. 调用核心逻辑
        int[] result = nextGreaterElement(nums);

        // 3. 输出结果
        StringBuilder sb = new StringBuilder();
        for (int val : result) {
            sb.append(val).append(" ");
        }
        System.out.println(sb.toString().trim());

        sc.close();
    }

    /**
     * 单调栈核心逻辑
     * 功能：寻找每个元素右边第一个比它大的元素的【下标】(如果需要存数值，把 result[idx] = i 改为 nums[i])
     */
    public static int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        // 初始化为 -1 或 0，根据题目要求。若找不到则保持此默认值
        Arrays.fill(result, -1);

        // 栈中只存【下标】，不存数值！
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // 【核心口诀】：
            // 找"更大" -> 只要当前 nums[i] > 栈顶，说明栈顶找到了右边的大哥，弹出栈顶
            // 找"更小" -> 只要当前 nums[i] < 栈顶，说明栈顶找到了右边的小弟，弹出栈顶
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                int idx = stack.pop();
                // idx 对应的右边第一个比它大的就是当前的 i
                result[idx] = i; // 这里存的是下标，如果题目要距离，存 i - idx
            }
            stack.push(i);
        }
        return result;
    }
}