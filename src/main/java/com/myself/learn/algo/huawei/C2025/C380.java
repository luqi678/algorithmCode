package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * P00380.华为od机试—火车进站
 * 核心思路：使用递归（DFS）模拟栈的两种操作（入栈、出栈）
 */
public class C380 {

    // 用于存储所有合法的出站序列
    static List<String> results = new ArrayList<>();
    static int[] nums; // 存储火车的入站顺序
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNext()) {
            n = sc.nextInt();
            nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }

            // 开始递归模拟
            // 参数1：waitIdx，当前等待进站的火车在nums中的下标
            // 参数2：stack，当前的车站（栈）
            // 参数3：outPath，当前已经出站的火车序列字符串
            dfs(0, new LinkedList<>(), "");

            // 题目要求输出通常需要按字典序排序
            Collections.sort(results);

            for (String res : results) {
                System.out.println(res);
            }
        }
        sc.close();
    }

    /**
     * DFS 模拟进出栈
     * @param waitIdx 等待进站的火车的索引 (代表 input 数组处理到了第几个)
     * @param stack   当前车站停靠的火车 (用 LinkedList 模拟栈)
     * @param outPath 已经离开车站的火车序列
     */
    private static void dfs(int waitIdx, Deque<Integer> stack, String outPath) {
        // 结束条件：所有火车都处理完了（既没有待进站的，栈里也空了）
        if (waitIdx == n && stack.isEmpty()) {
            results.add(outPath.trim());
            return;
        }

        // 选择1：如果栈里有车，可以选择“出栈”
        if (!stack.isEmpty()) {
            // 为了不破坏当前递归层的 stack 状态，通常有两种写法：
            // 1. 回溯法（手动 push/pop 还原）
            // 2. 克隆法（传一个新的 stack 进去） -> 这里为了逻辑清晰，使用克隆法，虽然稍微耗内存但在算法题数据量下通常没问题

            int top = stack.pop(); // 拿出栈顶
            dfs(waitIdx, stack, outPath + top + " ");
            stack.push(top); // 【回溯】恢复现场，把刚才拿出去的放回来，以便后面尝试另一种选择
        }

        // 选择2：如果还有火车在等待，可以选择“进站”
        if (waitIdx < n) {
            stack.push(nums[waitIdx]); // 进站
            dfs(waitIdx + 1, stack, outPath);
            stack.pop(); // 【回溯】恢复现场，把刚才放进去的拿出来
        }
    }
}