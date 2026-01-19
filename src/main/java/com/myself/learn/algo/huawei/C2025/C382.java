package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * P00382.华为od机试—数字排列
 * 2025C卷
 * difficulty:4
 *
 * @author luqi
 */
public class C382 {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Integer> resultList = new ArrayList<>();
    // 标记输入原本的3个数字位置是否被使用，确保每个位置的数字在一轮排列中只用一次
    static boolean[] usedIndex;
    static int[] inputs;

    public static void main(String[] args) {
        if (sc.hasNextLine()) {
            String line = sc.nextLine();

            // --- 1. 严格的输入校验 ---
            String[] parts = line.split(",");
            if (parts.length != 3) {
                System.out.println(-1);
                return;
            }

            inputs = new int[3];
            Set<Integer> uniqueCheck = new HashSet<>();
            int maxN = -1; // 记录原始输入的最大值

            try {
                for (int i = 0; i < 3; i++) {
                    int val = Integer.parseInt(parts[i].trim());
                    // 校验：1-9之间，且不重复
                    if (val < 1 || val > 9 || !uniqueCheck.add(val)) {
                        System.out.println(-1);
                        return;
                    }
                    inputs[i] = val;
                    maxN = Math.max(maxN, val);
                }
            } catch (Exception e) {
                // 捕获非数字格式异常
                System.out.println(-1);
                return;
            }

            // --- 2. 分层生成全排列 ---
            // 题目隐含要求：先列出所有1位数的，再列出2位数的，最后3位数的
            // 因此我们需要运行3次DFS，长度分别为 1, 2, 3

            // 每次生成不同长度前，不需要重置 resultList，因为我们是追加
            for (int len = 1; len <= 3; len++) {
                usedIndex = new boolean[3]; // 每次换长度，重置使用状态
                dfs("", len);
            }

            // --- 3. 排序与输出 ---
            // 虽然DFS生成的顺序大概率是有序的，但为了保险（尤其是2/5映射后大小错乱），必须最后排一次序
            Collections.sort(resultList);

            // 题目要求：输出第 N 个（索引 N-1）
            // 如果不到这么多个数字则给出最后一个
            int targetIndex = maxN - 1;
            if (targetIndex >= resultList.size()) {
                targetIndex = resultList.size() - 1;
            }

            System.out.println(resultList.get(targetIndex));
        }
        sc.close();
    }

    /**
     * @param currentStr 当前拼接的字符串
     * @param targetLen  目标位数 (1, 2, 或 3)
     */
    private static void dfs(String currentStr, int targetLen) {
        // 结束条件：长度达标
        if (currentStr.length() == targetLen) {
            resultList.add(Integer.parseInt(currentStr));
            return;
        }

        // 遍历输入的3个“卡槽”
        for (int i = 0; i < 3; i++) {
            if (!usedIndex[i]) {
                usedIndex[i] = true; // 标记第 i 个卡槽已用

                int originalNum = inputs[i];
                // 获取这个卡槽能代表的所有数字（包括变体）
                List<Integer> possibilities = getPossibilities(originalNum);

                for (int num : possibilities) {
                    dfs(currentStr + num, targetLen);
                }

                usedIndex[i] = false; // 回溯
            }
        }
    }

    // 处理 2<->5 和 6<->9 的映射
    private static List<Integer> getPossibilities(int num) {
        List<Integer> list = new ArrayList<>();
        list.add(num);
        if (num == 2) list.add(5);
        else if (num == 5) list.add(2);
        else if (num == 6) list.add(9);
        else if (num == 9) list.add(6);
        return list;
    }
}