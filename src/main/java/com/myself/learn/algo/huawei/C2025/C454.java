package com.myself.learn.algo.huawei.C2025;

import java.util.Scanner;

public class C454 {
    // 定义每道题的分数
    static int[] scores = new int[25];
    static int targetScore;
    static int resultCount = 0;

    public static void main(String[] args) {
        // 初始化题目分数
        // 0-9: 2分, 10-19: 4分, 20-24: 8分
        for (int i = 0; i < 10; i++) scores[i] = 2;
        for (int i = 10; i < 20; i++) scores[i] = 4;
        for (int i = 20; i < 25; i++) scores[i] = 8;

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            targetScore = scanner.nextInt();
            // 开启DFS
            dfs(0, 0, 0);
            System.out.println(resultCount);
        }
    }

    /**
     * DFS 搜索
     * @param index 当前题号 (0-24)
     * @param currentScore 当前得分
     * @param wrongCount 当前错题数
     */
    public static void dfs(int index, int currentScore, int wrongCount) {
        // 剪枝：如果当前分数已经超过目标，直接返回
        if (currentScore > targetScore) return;

        // Base Case: 25道题全部答完
        if (index == 25) {
            if (currentScore == targetScore) {
                resultCount++;
            }
            return;
        }

        // 情况1：当前题答对
        dfs(index + 1, currentScore + scores[index], wrongCount);

        // 情况2：当前题答错
        if (wrongCount + 1 == 3) {
            // 如果这是第3个错误，考试直接结束
            // 此时分数不再增加，直接判断当前分数是否达标
            if (currentScore == targetScore) {
                resultCount++;
            }
            // 注意：这里return了，不再进行下一题
        } else {
            // 如果错误未达3次，继续下一题
            dfs(index + 1, currentScore, wrongCount + 1);
        }
    }
}