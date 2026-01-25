package com.myself.learn.exam;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

/**
 * 描述：
 * This is a two.
 *
 * @author lujian
 * @since 2026/1/21.
 */
public class two {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static int[][] grid;
    static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {

        if (sc.hasNext()) {
            int m = Integer.parseInt(sc.nextLine().trim()) + 1;
            int n = Integer.parseInt(sc.nextLine().trim()) + 1;
            // 初始化网格 初始全为正常点-0
            grid = new int[m][n];
            for (int i = 0; i < grid.length; i++) {
                Arrays.fill(grid[i], 0);
            }
            // 定义结果输出
            List<Integer> counts = new ArrayList<>();
            // 故障点输入
            while (sc.hasNextLine()) {
//            for (int z = 0; z < 5; z++) {
                int[] ints = Arrays.stream(sc.nextLine().trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
                int x = ints[0];
                int y = ints[1];
                grid[x][y] = 1;
                // 进行判断当前故障块数
                int number = getNumberOfFaultBlocks(grid);
                counts.add(number);
            }
//            System.out.println(counts.stream().map(String::valueOf).collect(Collectors.joining(",")));
            System.out.println(counts.toString());
        }

        // 关闭输入
        sc.close();
    }

    /**
     * 网格内故障块
     *
     * @param grid
     * @return
     */
    private static int getNumberOfFaultBlocks(int[][] grid) {
        int[][] visited = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                visited[i][j] = grid[i][j];
            }
        }
        // 队列
        Deque<int[]> queue = new ArrayDeque<>();
        // 块数
        int count = 0;
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                int key = visited[i][j];
                if (key == 0 || key == 2) {
                    continue;
                }
                int[] faultPoint = new int[]{i, j};
                queue.offer(faultPoint);
                count++;
                while (!queue.isEmpty()) {
                    int[] pop = queue.pop();
                    // 遍历方向
                    for (int[] direction : directions) {
                        int newX = pop[0] + direction[0];
                        int newY = pop[1] + direction[1];
                        // 越界
                        if (newX < 0 || newX >= visited.length || newY < 0 || newY >= visited[0].length) {
                            continue;
                        }
                        // 正常点也跳过 已经遍历过的点也跳过
                        if (visited[newX][newY] == 0 || visited[newX][newY] == 2) {
                            continue;
                        }
                        // 遇到故障点 加入队列
                        if (visited[newX][newY] == 1) {
                            queue.offer(new int[]{newX, newY});
                            visited[newX][newY] = 2;
                        }
                    }
                }
            }
        }

        return count;
    }

}
