package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * P00377.华为od机试—单词搜索/找到它
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class C377 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static String point;
    static Character[][] map;
    static boolean[][] visited;
    static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int step;
    static int[] res;
    static boolean found = false;

    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            sc.nextLine();
            // 目标单词
            point = sc.nextLine();
            // 输入地图
            map = new Character[n][m];
            // 首字母列表
            List<int[]> firsts = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j].equals(point.charAt(0))) {
                        firsts.add(new int[]{i, j});
                    }
                }
            }
            // 遍历所有首字母
            for (int[] first : firsts) {
                visited = new boolean[n][m];
                visited[first[0]][first[1]] = true;
                step = 1;
                found = false;
                // 递归 DFS
                dfs(first[0], first[1]);
                if (found) {
                    res = first;
                }
            }
            System.out.println(found ? ( res[0] + 1 ) + " " + ( res[1] + 1 ): "No");
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static boolean dfs(int x, int y) {
        if( step == point.length()){
            found = true;
            return true;
        }
        // 选择
        for (int[] dir : dirs) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            // 越界
            if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length) {
                continue;
            }
            // 访问过
            if (visited[nextX][nextY]) {
                continue;
            }
            if (map[nextX][nextY].equals(point.charAt(step))) {
                System.out.println(nextX + " " + nextY);
                visited[nextX][nextY] = true;
                step++;
                dfs(nextX, nextY);
                visited[nextX][nextY] = false;
                step--;
            }
        }
        return false;
    }
}
