package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * 图论模板
 *
 * @author 2405051*/
public class C451 {

    public static class Node{
        public int x;
        public int y;
        public int step;
        public int rest;
        Node(int x, int y,int step, int rest){
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
            int n = sc.nextInt();
            int m = sc.nextInt();
            // 图
            int[][] map = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            // 逻辑 - DFS或者BFS
            System.out.println(bfs(n, m, map));
        }
    }

    private static int bfs(int m, int n, int[][] map) {
        // 初始化
        //      队列
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0,0,1));
        //      访问标记
        boolean[][][] visited = new boolean[m][n][2];
        visited[0][0][1] = true;
        //      访问方向
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        //      最小代价
        int minStep = Integer.MAX_VALUE;
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
                    minStep = Math.min(minStep, current.step);
                }
                // 正常移动
                for (int[] direction : directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];
                    // 越绝判断
                    if (newX > m - 1 || newX < 0 || newY > n - 1 || newY < 0) {
                        // 出界
                        continue;
                    }
                    // 访问记录
                    if (visited[newX][newY][current.rest]) {
                        continue;
                    }
                    queue.add(new Node(newX, newY, current.step + Math.abs(map[newX][newY] - map[x][y]) , current.rest));
                    visited[newX][newY][current.rest] = true;
                }
                // 传送
                if (current.rest > 0) {
                    int value = map[x][y];
                    List<Node> toFly = new ArrayList<>();
                    for (int k = 0; k < map.length; k++) {
                        int[] line = map[k];
                        for (int j = 0; j < line.length; j++) {
                            int one = line[j];
                            if (one == value) {
                                toFly.add(new Node(k, j, current.step, current.rest - 1));
                            }
                        }
                    }
                    for (Node node : toFly) {
                        if (visited[node.x][node.y][node.rest]) {
                            continue;
                        }
                        visited[node.x][node.y][node.rest] = true;
                        queue.add(node);
                    }
                }
            }
        }
        return minStep;
    }


    // 状态类，实现 Comparable 用于优先队列排序
    static class State implements Comparable<State> {
        int x, y;
        int cost;
        int rest; // 剩余传送次数

        public State(int x, int y, int cost, int rest) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.rest = rest;
        }

        // 优先队列按花费从小到大排序
        @Override
        public int compareTo(State other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt(); // 行
            int m = sc.nextInt(); // 列

            int[][] map = new int[n][m];
            // 预处理：记录每个数值出现的所有坐标，用于快速传送查找
            Map<Integer, List<int[]>> valueMap = new HashMap<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = sc.nextInt();
                    valueMap.computeIfAbsent(map[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
                }
            }

            System.out.println(dijkstra(n, m, map, valueMap));
        }
    }

    private static int dijkstra(int n, int m, int[][] map, Map<Integer, List<int[]>> valueMap) {
        // 优先队列
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(0, 0, 0, 1));

        // dist[x][y][rest] 记录到达 (x,y) 且剩余 rest 次传送机会时的最小开销
        int[][][] dist = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }
        dist[0][0][1] = 0;

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            // 如果当前路径花费大于已有记录的最小花费，剪枝
            if (cur.cost > dist[cur.x][cur.y][cur.rest]) {
                continue;
            }

            // 到达终点
            if (cur.x == n - 1 && cur.y == m - 1) {
                return cur.cost;
            }

            // 1. 普通移动 (上下左右)
            for (int[] dir : directions) {
                int nx = cur.x + dir[0];
                int ny = cur.y + dir[1];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    int newCost = cur.cost + Math.abs(map[nx][ny] - map[cur.x][cur.y]);
                    // 如果发现了更短的路径，更新并入队
                    if (newCost < dist[nx][ny][cur.rest]) {
                        dist[nx][ny][cur.rest] = newCost;
                        pq.offer(new State(nx, ny, newCost, cur.rest));
                    }
                }
            }

            // 2. 使用传送 (仅当还有次数时)
            if (cur.rest > 0) {
                int currentVal = map[cur.x][cur.y];
                List<int[]> targets = valueMap.get(currentVal);

                if (targets != null) {
                    for (int[] point : targets) {
                        int tx = point[0];
                        int ty = point[1];

                        // 不传送回自己，虽然逻辑上不影响结果但浪费性能
                        if (tx == cur.x && ty == cur.y) continue;

                        // 传送本身通常没有额外花费(或花费为0)，状态变为 rest=0
                        int newCost = cur.cost;
                        if (newCost < dist[tx][ty][0]) {
                            dist[tx][ty][0] = newCost;
                            pq.offer(new State(tx, ty, newCost, 0));
                        }
                    }
                }
            }
        }
        return -1; // 理论上不会执行到这
    }
}