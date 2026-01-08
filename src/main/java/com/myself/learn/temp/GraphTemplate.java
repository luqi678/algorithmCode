package com.myself.learn.temp;

import java.util.Scanner;

/**
 * 图论模板
 *
 * @author 2405051*/
public class GraphTemplate {


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

    private static boolean bfs(int m, int n, int t, int[][] map) {
        return false;
    }

}