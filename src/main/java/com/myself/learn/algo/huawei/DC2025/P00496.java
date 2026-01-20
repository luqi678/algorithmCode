package com.myself.learn.algo.huawei.DC2025;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * P00496.华为od机试—自动泊车
 * 双机位C卷
 * difficulty:4
 * @author luqi
 */
public class P00496 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static int m,n;
    static int[] target;
    static int[][] grid;

    static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) {

        if (sc.hasNext()) {
            target = new int[]{sc.nextInt(), sc.nextInt()};
            m = sc.nextInt();
            n = sc.nextInt();
            grid = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

            int min = bfs();
            System.out.println(min);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static int bfs() {
        // 初始化
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        int step = 0;

        // 遍历
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                // 判断是否达到目标
                if (poll[0] == target[0] && poll[1] == target[1]) {
                    return step;
                }
                // 遍历四个方向
                for (int[] direction : directions) {
                    int newX = poll[0] + direction[0];
                    int newY = poll[1] + direction[1];
                    // 越界
                    if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                        continue;
                    }
                    // 障碍
                    if (grid[newX][newY] == 1) {
                        continue;
                    }
                    // 已经访问过
                    if (visited[newX][newY]) {
                        continue;
                    }
                    queue.offer(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }
            step ++;
        }
        return -1;
    }
}
