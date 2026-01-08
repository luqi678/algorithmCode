package com.myself.learn.algo.huawei.doubleC;

import java.util.Scanner;

public class DoubleC_491_copy {
    static int m, n;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            m = sc.nextInt(); // 员工数
            n = sc.nextInt(); // 月饼数

            if (m > n) {
                System.out.println(0);
                return;
            }

            count = 0;
            // 递归入口：
            // 第1个人开始分，剩余n个，preVal传n（对于第1个人来说preVal不生效，但作为初始上界参考）
            distribute(1, n, n);
            System.out.println(count);
        }
    }

    /**
     * 递归分配
     * @param personIdx 当前正在给第几个人分 (1 ~ m)
     * @param remain    剩余月饼数量
     * @param preVal    上一个人分到的月饼数量
     */
    public static void distribute(int personIdx, int remain, int preVal) {
        // Base Case: 已经分到了最后一个人
        if (personIdx == m) {
            // 最后一个人直接拿走剩下的所有月饼
            // 必须满足：
            // 1. 剩下的月饼数 <= 上一个人 (保持降序)
            // 2. 上一个人 - 剩下的月饼数 <= 3 (满足差值约束)
            // 3. 剩下的月饼数 >= 1 (题目要求至少分1个)
            // 注意：如果 m=1，personIdx也是1，此时没有上一个人，前两个条件自动满足或忽略
            if (remain >= 1) {
                if (personIdx == 1) {
                    count++;
                } else if (remain <= preVal && (preVal - remain) <= 3) {
                    count++;
                }
            }
            return;
        }

        // --- 确定当前人能拿的范围 ---

        // 上界 (Max)：
        // 1. 不能超过剩余总数减去后面人最少拿的量（后面至少每人1个）：remain - (m - personIdx)
        // 2. 如果不是第1个人，不能超过上一个人 preVal（保持降序）
        int max = remain - (m - personIdx);
        if (personIdx > 1) {
            max = Math.min(preVal, max);
        }

        // 下界 (Min)：
        // 1. 至少为 1
        // 2. 如果不是第1个人，受差值限制：current >= preVal - 3
        int min = 1;
        if (personIdx > 1) {
            min = Math.max(1, preVal - 3);
        }

        // --- 剪枝与递归 ---
        for (int i = max; i >= min; i--) {
            // 剪枝判断：
            // 如果当前选 i，剩下的人数 (m - personIdx) 即使每个人都拿最大的 i，
            // 总和 (m - personIdx) * i 依然小于剩余月饼 (remain - i)，
            // 说明当前选的 i 太小了，导致剩下的月饼分不完（不满足降序条件），
            // 既然 i 都不行，比 i 更小的数更不行，直接 break。
            if ((remain - i) > (m - personIdx) * i) {
                break;
            }

            distribute(personIdx + 1, remain - i, i);
        }
    }
}