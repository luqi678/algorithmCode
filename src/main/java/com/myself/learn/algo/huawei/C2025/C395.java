package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * P00395.华为od机试—污染水域
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class C395 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int[][] matrix;
    static int days = 0;
    static int allNumbers;

    public static void main(String[] args) {

        if (sc.hasNext()) {
            String line = sc.nextLine();
            int[] number = Arrays.stream(line.trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            // 计算开方
            int n = (int) Math.sqrt(number.length);
            allNumbers = n * n;
            matrix = new int[n][n];
            // 判断是否全0全1
            boolean isAllZero = true;
            boolean isAllOne = true;
            for (int i = 0; i < number.length; i++) {
                matrix[i / n][i % n] = number[i];
                if (number[i] == 0) {
                    isAllOne = false;
                } else {
                    isAllZero = false;
                }
            }
            if (isAllZero || isAllOne) {
                System.out.println("-1");
                return;
            }
            int day = bfs();
            System.out.println(day);


        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static int bfs() {
        // 初始化
        //   剩余未污染数量
        int remain = allNumbers;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int current = matrix[i][j];
                if (current == 1) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    remain--;
                }
            }
        }
        // 搜索
        while (!queue.isEmpty()) {
            // 跳出条件
            if(remain == 0){
                return days;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                // 方向蔓延
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    // 非越界 + 未访问
                    if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && !visited[x][y]) {
                        queue.offer(new int[]{x, y});
                        visited[x][y] = true;
                        remain--;
                    }
                }
            }
            days ++;
        }
        return -1;
    }
}
