package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * P00315.华为od机试—寻找最优的路测线路
 * 2025C卷
 * difficulty:4
 * @author luqi
 */
public class P00315 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static boolean[][] visited ;
    static int bestScore = 0;

    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] grid = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            ArrayList<Integer> list = new ArrayList<>();
            list.add(grid[0][0]);
            visited = new boolean[n][m];
            visited[0][0] = true;
            dfs(grid,new int[]{0,0},list);
            System.out.println(bestScore);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    /**
     * 递归
     * @param grid 地图
     * @param ints 当前位置
     * @param path 记录走过的路径
     */
    private static void dfs(int[][] grid, int[] ints, ArrayList<Integer> path) {
        // 结束条件
        if (ints[0] == grid.length - 1 && ints[1] == grid[0].length - 1) {
            // 更新最佳分数
            bestScore = Math.max(bestScore, path.stream().mapToInt(Integer::intValue).min().orElse(0));
            return;
        }

        // 遍历
        for (int[] dir : dirs) {
            int newX = ints[0] + dir[0];
            int newY = ints[1] + dir[1];
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && !visited[newX][newY]) {
                visited[newX][newY] = true;
                // 添加路径
                path.add(grid[newX][newY]);
                // 递归
                dfs(grid, new int[]{newX, newY}, path);
                visited[newX][newY] = false;
                // 删除路径
                path.remove(path.size() - 1);
            }
        }

    }
}
