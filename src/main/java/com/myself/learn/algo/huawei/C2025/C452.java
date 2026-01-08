package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * 图论模板
 *
 * @author 2405051*/
public class C452 {


    public static class Node {
        public int x;
        public int y;
        public int step;
        public int rest;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Node(int x, int y, int step, int rest) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.rest = rest;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入图
        if (sc.hasNextInt()) {
            // 图论前有几个入参
            int m = sc.nextInt();
            int n = sc.nextInt();
            int t = sc.nextInt();
            // 图
            int[][] map = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            // 逻辑 - DFS或者BFS
            System.out.println(bfs(m, n, t, map));
        }
    }

    private static Integer bfs(int m, int n, int t, int[][] map) {
        // 初始化
        Queue<Node> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[m][n][4];
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // int step = 0;
        int rest = 3;
        q.add(new Node(0, 0, 0, rest));
        visited[0][0][rest] = true;
        // 寻找
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node current = q.poll();
                if (current == null) {
                    continue;
                }
                int x = current.x;
                int y = current.y;
                // 是否结束
                if (x == m-1 && y ==n-1) {
                    return current.step;
                }
                // 继续寻找
                for (int[] direction : directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];
                    // 越界
                    if (newX > m-1 || newX <0 || newY > n-1 || newY < 0) {
                        continue;
                    }
                    // 访问
//                    if (visited[newX][newY][current.rest]) {
//                        continue;
//                    }
                    // 是否需要爆发
                    if (Math.abs(map[newX][newY] - map[x][y]) <= t) {
                        if (!visited[newX][newY][current.rest]) {
                            visited[newX][newY][current.rest] = true;
                            q.add(new Node(newX, newY, current.step + 1, current.rest));
                        }
                    }else {
                        if (current.rest > 0 && !visited[newX][newY][current.rest - 1]) {
                            visited[newX][newY][current.rest - 1] = true;
                            q.add(new Node(newX, newY, current.step + 1, current.rest - 1));
                        }
                    }
                }
            }
        }
        return -1;
    }

}