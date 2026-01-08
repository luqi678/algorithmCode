package com.myself.learn.temp;

import java.util.LinkedList;
import java.util.Queue;

public class BFSTemplate2 {

    // 1. 方向数组：上、下、左、右
    // 技巧：如果允许对角线移动，数组长度改为8，并添加对应偏移量
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    /**
     * BFS 主函数
     * @param grid   地图矩阵 (0代表空地，1代表障碍物)
     * @param startX 起点行坐标
     * @param startY 起点列坐标
     * @param targetX 终点行坐标
     * @param targetY 终点列坐标
     * @return 最短步数，如果无法到达返回 -1
     */
    public static int bfs(int[][] grid, int startX, int startY, int targetX, int targetY) {
        int m = grid.length;
        int n = grid[0].length;

        // 2. 判空或起点即终点检查
        if (startX == targetX && startY == targetY) return 0;

        // 3. 初始化队列
        // int[] 存储坐标 {x, y}，如果需要记录更多状态（如持有钥匙数量），可增加数组长度或用对象
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});

        // 4. 初始化 visited 数组，防止走回头路 (很重要！)
        boolean[][] visited = new boolean[m][n];
        visited[startX][startY] = true;

        int step = 0; // 记录步数

        // 5. 开始 BFS 循环
        while (!queue.isEmpty()) {
            int size = queue.size(); // 【核心】锁定当前层的节点数量

            // 遍历当前这一层的所有节点
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int x = curr[0];
                int y = curr[1];

                // 如果到达终点，直接返回步数
                if (x == targetX && y == targetY) {
                    return step;
                }

                // 6. 向四个方向扩散
                for (int k = 0; k < 4; k++) {
                    int newX = x + dx[k];
                    int newY = y + dy[k];

                    // 7. 边界检查 & 有效性检查
                    // (1) 不越界
                    // (2) 不是障碍物 (这里假设 1 是障碍)
                    // (3) 没有被访问过
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n
                            && grid[newX][newY] != 1
                            && !visited[newX][newY]) {

                        queue.offer(new int[]{newX, newY});
                        visited[newX][newY] = true; // 入队时立即标记，防止重复入队
                    }
                }
            }
            // 当前层遍历完，步数 +1
            step++;
        }

        // 队列空了还没找到终点
        return -1;
    }

    // 简单的 Main 测试
    public static void main(String[] args) {
        // 示例：0是路，1是墙
        int[][] map = {
                {0, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 0, 0, 0},
                {0, 1, 1, 0}
        };
        // 从 (0,0) 走到 (3,3)
        System.out.println("最短步数: " + bfs(map, 0, 0, 3, 3));
    }
}