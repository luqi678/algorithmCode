package com.myself.learn.temp;

import java.util.Scanner;

/**
 * 模板一：网格 DFS（最常用）
 * 适用于：矩阵/地图问题，如“机器人活动范围”、“岛屿数量”。 特点：需要处理边界、visited 数组、方向数组。
 */
public class DFSTemple1 {

    // 1. 定义全局变量，减少递归传参
    static int m, n;
    static int[][] grid;
    static boolean[][] visited;
    // 方向数组：上、下、左、右
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int maxArea = 0; // 记录结果（如最大面积）

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) { // 处理多组输入（OD常见）
            m = sc.nextInt();
            n = sc.nextInt();
            grid = new int[m][n];
            visited = new boolean[m][n];

            // 读取网格
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

            // 2. 遍历每一个点作为起点（如果是求连通块类问题）
            // 如果题目指定了固定起点（如只从 0,0 出发），则不需要双层循环
            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 只有满足特定条件且未访问过的才进入 DFS
                    if (!visited[i][j] && grid[i][j] == 1) {
                        // count 接收本次 DFS 搜索到的数量
                        int count = dfs(i, j);
                        ans = Math.max(ans, count);
                    }
                }
            }
            System.out.println(ans);
        }
    }

    /**
     * 标准 DFS 函数
     * @return 返回本次搜索覆盖的点数（根据题目要求修改返回值）
     */
    public static int dfs(int x, int y) {
        // 标记已访问
        visited[x][y] = true;

        int count = 1; // 包含当前点

        // 向四个方向扩散
        for (int[] dir : dirs) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];

            // 3. 核心判断：越界检查 + 访问检查 + 题目逻辑检查
            if (inArea(nextX, nextY)
                    && !visited[nextX][nextY]
                    && grid[nextX][nextY] == 1) { // 这里 grid[nextX][nextY] == 1 是题目特定条件

                count += dfs(nextX, nextY);
            }
        }
        return count;
    }

    // 边界检查工具函数
    private static boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}