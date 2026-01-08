package com.myself.learn.temp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 图论模板
 *
 * @author 2405051*/
public class BFSTemplate {

    public static class Node{
        public int x;
        public int y;
        public int step;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        Node(int x, int y, int step){
            this.x = x;
            this.y = y;
            this.step = step;
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

    private static int bfs(int m, int n, int t, int[][] map) {
        // 初始化
        //      队列
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0,0));
        //      访问标记
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        //      访问方向
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        //      轮询访问
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                int x = current.x;
                int y = current.y;
                // 当前是否为目标
                if (x == m - 1 && y == n - 1) {
                    // 达到目标
                    return current.step;
                }
                for (int[] direction : directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];
                    // 越绝判断
                    if (newX > m - 1 || newX < 0 || newY > n - 1 || newY < 0) {
                        // 出界
                        continue;
                    }
                    // 访问记录
                    if (visited[newX][newY]) {
                        continue;
                    }
                    queue.add(new Node(newX, newY,current.step + 1));
                    visited[newX][newY] = true;
                }
            }
        }
        return -1;
    }

}