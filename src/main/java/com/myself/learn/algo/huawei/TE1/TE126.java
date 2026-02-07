package com.myself.learn.algo.huawei.TE1;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 126- 最小路径和
 * difficulty:中等
 * frequency:中
 * @author luqi
 */
public class TE126 {

    static int[][] dirs = new int[][]{{1,0},{0,1}};

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSum(grid));

    }
    public static int minPathSum(int[][] grid) {
        int[][] visited = new int[grid.length][grid[0].length];
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        // 修正为 Dijkstra 逻辑
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // [x, y, current_sum]
        pq.offer(new int[]{0, 0, grid[0][0]});
        visited[0][0] = grid[0][0];

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0], c = curr[1], sum = curr[2];

            if (sum > visited[r][c]) continue; // 剪枝
            if (r == grid.length - 1 && c == grid[0].length - 1) return sum;

            for (int[] dir : dirs) {
                int nx = r + dir[0], ny = c + dir[1];
                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length) {
                    if (sum + grid[nx][ny] < visited[nx][ny]) {
                        visited[nx][ny] = sum + grid[nx][ny];
                        pq.offer(new int[]{nx, ny, visited[nx][ny]});
                    }
                }
            }
        }
        return -1;
    }
}
