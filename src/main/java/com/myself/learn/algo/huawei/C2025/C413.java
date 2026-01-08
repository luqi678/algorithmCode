package com.myself.learn.algo.huawei.C2025;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// OD机考请将类名改为 Main，并去掉 package
public class C413 {

    static int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int[][] grid = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

            // 标记数组，防止重复计算
            boolean[][] visited = new boolean[m][n];
            int maxArea = 0;

            // 核心修改：遍历每一个格子作为潜在的起点
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 如果该点属于一个新的连通块（未被访问过）
                    if (!visited[i][j]) {
                        // 计算当前连通块的大小
                        int currentArea = bfs(grid, visited, i, j);
                        // 更新最大值
                        maxArea = Math.max(maxArea, currentArea);
                    }
                }
            }
            System.out.println(maxArea);
        }
    }

    // 使用 BFS 以避免递归过深导致的 StackOverflow
    private static int bfs(int[][] grid, boolean[][] visited, int startX, int startY) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
        count++;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];

            for (int[] dir : dirs) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                // 越界检查
                if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                    continue;
                }
                // 已访问检查
                if (visited[newX][newY]) {
                    continue;
                }
                // 题目条件：绝对值差 <= 1
                if (Math.abs(grid[newX][newY] - grid[x][y]) <= 1) {
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY});
                    count++;
                }
            }
        }
        return count;
    }
}