package com.myself.learn.algo.huawei.C2025;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * P00204.华为od机试— 小华地图寻宝
 * 2025A卷,2025C卷
 * difficulty:4
 * @author luqi
 */
public class P00204 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int count = 0;
    static boolean[][] visited ;

    public static void main(String[] args) {

        if (sc.hasNext()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int k = sc.nextInt();
            visited = new boolean[m][n];

            bfs(m, n, k);

            System.out.println(count);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static void bfs(int m, int n, int k) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        count++;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < dirs.length; i++) {
                int[] dir = dirs[i];
                int newX = poll[0] + dir[0];
                int newY = poll[1] + dir[1];

                int sum = dealDigits(newX) + dealDigits(newY);

                if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY] && sum <= k) {
                    queue.offer(new int[]{newX, newY});
                    visited[newX][newY] = true;
                    count++;
                }
            }
        }

    }

    private static int dealDigits(int nums) {
        int res = 0;
        while (nums > 0) {
            res += nums % 10;
            nums /= 10;
        }
        return res;
    }
}
