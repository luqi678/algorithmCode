package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class C407 {
    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static class Node implements Comparable<Node> {
        int x, y, times;
        Node(int x, int y, int times) {
            this.x = x;
            this.y = y;
            this.times = times;
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.times, other.times);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;

        // 1. 健壮地读取输入
        String gridStr = sc.nextLine();
        String lightStr = sc.nextLine();

        int[][] grid = parseMatrix(gridStr);
        int[][] lightData = parseMatrix(lightStr);

        if (grid == null || grid.length == 0) {
            System.out.println("-1");
            return;
        }

        int m = grid.length;
        int n = grid[0].length;

        // 2. 将红绿灯数据存入二维数组，比 Map 访问更快
        int[][] lightWait = new int[m][n];
        for (int[] light : lightData) {
            // 注意：lightData[i] 格式为 [x, y, waitTime]
            if (light.length >= 3) {
                lightWait[light[0]][light[1]] = light[2];
            }
        }

        // 3. Dijkstra 算法实现
        System.out.println(dijkstra(grid, lightWait, m, n));
    }

    private static int dijkstra(int[][] grid, int[][] lightWait, int m, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));

        int[][] minTime = new int[m][n];
        for (int[] row : minTime) Arrays.fill(row, Integer.MAX_VALUE);
        minTime[0][0] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            // 如果到达终点，因为是优先队列，此时一定是最小时间
            if (curr.x == m - 1 && curr.y == n - 1) {
                return curr.times;
            }

            // 优化：如果当前弹出的时间已经不是最优，跳过
            if (curr.times > minTime[curr.x][curr.y]) continue;

            for (int[] d : directions) {
                int nx = curr.x + d[0];
                int ny = curr.y + d[1];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] != 1) {
                    // 核心逻辑：移动到下一格基础 1s + 如果下一格是红绿灯则加上等待时间
                    int wait = (grid[nx][ny] == 2) ? lightWait[nx][ny] : 0;
                    int nextTime = curr.times + 1 + wait;

                    if (nextTime < minTime[nx][ny]) {
                        minTime[nx][ny] = nextTime;
                        pq.offer(new Node(nx, ny, nextTime));
                    }
                }
            }
        }
        return -1; // 无法到达
    }

    /**
     * 通用矩阵解析器：处理 [[...],[...]] 格式，自动忽略空格和非数字字符
     */
    private static int[][] parseMatrix(String str) {
        if (str == null || str.isEmpty()) return new int[0][0];

        // 使用正则匹配每一行内容 [...]
        List<int[]> rows = new ArrayList<>();
        // 匹配嵌套在 [] 里的内容
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("\\[([^\\[\\]]+)\\]").matcher(str);

        while (m.find()) {
            String rowContent = m.group(1);
            String[] parts = rowContent.split(",");
            int[] row = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                row[i] = Integer.parseInt(parts[i].trim());
            }
            rows.add(row);
        }
        return rows.toArray(new int[0][]);
    }
}