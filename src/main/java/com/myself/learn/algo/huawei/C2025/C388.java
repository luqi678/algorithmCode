package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * P00388.华为od机试—计算疫情扩散时间
 * 2025C卷
 * difficulty:5
 * @author luqi
 */
public class C388 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static int day = 0;
    static int n ;
    static int[][] map;
    static int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {

        if (sc.hasNext()) {
            int[] numbers = Arrays.stream(sc.nextLine().trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            n = (int) Math.sqrt(numbers.length);
            map = new int[n][n];
            for (int i = 0; i < numbers.length; i++) {
                map[i / n][i % n] = numbers[i];
            }
            // 逻辑处理
            day = bfs();
            System.out.println(day == 0 ? -1 : day);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static int bfs() {
        // 初始化
        Queue<int[]> queue = new ArrayDeque<>();
        int visitCount = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    visitCount++;
                }
            }
        }
        // 全0
        if (queue.isEmpty()) {
            return 0;
        }
        while (!queue.isEmpty()) {
            // 跳出条件
            if (visitCount == n * n) {
                return day;
            }
            // 开始按天蔓延
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (cur ==null) {
                    continue;
                }
                for (int k = 0; k < dirs.length; k++) {
                    int x = cur[0] + dirs[k][0];
                    int y = cur[1] + dirs[k][1];
                    // 越界
                    if (x < 0 || x >= n || y < 0 || y >= n) {
                        continue;
                    }
                    // 访问过
                    if (map[x][y] == 1){
                        continue;
                    }
                    // 未访问
                    map[x][y] = 1;
                    queue.offer(new int[]{x, y});
                    visitCount++;
                }
            }
            day++;
        }
        return day;
    }
}
