package com.myself.learn.algo.huawei.TE1;

import java.util.Scanner;

/**
 * 50- 不同路径
 * difficulty:中等
 * frequency:中
 * @author luqi
 */
public class TE50 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);

    static int[][] dirs = new int[][]{{0, 1}, {1, 0}};

    static int count = 0;


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            dfs(m, n, 0, 0);
            System.out.println(count);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    /**
     * 递归函数
     * @param m 行数
     * @param n 列数
     * @param i 当前 行
     * @param j 当前 列
     */
    private static void dfs(int m, int n, int i, int j) {
        // 跳出条件
        if (i == m - 1 && j == n - 1) {
            count++;
            return;
        }
        // 遍历
        for (int k = 0; k < dirs.length; k++) {
            int x = i + dirs[k][0];
            int y = j + dirs[k][1];
            // 越界
            if (x < 0 || x >= m || y < 0 || y >= n) {
                continue;
            }
            dfs(m, n, x, y);
        }
    }
}
