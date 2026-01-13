package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * P00387.华为od机试—图像物体的边界
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class C387 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);

    static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    static int[][] boundaryMap;
    static int count = 0;
    static int n;
    static int m;

    public static void main(String[] args) {

        if (sc.hasNext()) {
            n = sc.nextInt();
            m = sc.nextInt();
            boolean has1 = false;
            List<int[]> list = new ArrayList<>();
            int[][] map = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int number = sc.nextInt();
                    if (number == 5) {
                        list.add(new int[]{i, j});
                    }else {
                        if (number == 1) {
                            has1 = true;
                        }
                    }
                    map[i][j] = number;
                }
            }
            if (list.isEmpty()) {
                System.out.println(0);
                return;
            }
            if (!has1) {
                System.out.println(0);
                return;
            }
            // 边界值集合 0-非边界值 1-是边界值 2-边界值已经被遍历
            boundaryMap = new int[n][m];
            for (int i = 0; i < list.size(); i++) {
                for (int k = 0; k < dirs.length; k++) {
                    int x = list.get(i)[0] + dirs[k][0];
                    int y = list.get(i)[1] + dirs[k][1];
                    // 越界
                    if (x < 0 || x >= n || y < 0 || y >= m) {
                        continue;
                    }
                    if (map[x][y] == 1) {
                        boundaryMap[x][y] = 1;
                    }

                }
            }
            // 对边界值进行BFS遍历
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (boundaryMap[i][j] == 1) {
                        bfs(i, j);
                    }
                }
            }
            System.out.println(count);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static void bfs(int firstX,int firstY) {
        Queue<int[]> queue = new ArrayDeque<>();
        boundaryMap[firstX][firstY] = 2;
        queue.offer(new int[]{firstX, firstY});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int k = 0; k < dirs.length; k++) {
                int x = poll[0] + dirs[k][0];
                int y = poll[1] + dirs[k][1];
                if (x < 0 || x >= n || y < 0 || y >= m) {
                    continue;
                }
                if (boundaryMap[x][y] == 1) {
                    boundaryMap[x][y] = 2;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        count++;
    }
}
