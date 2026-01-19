package com.myself.learn.algo.huawei.two;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * 两个字符串间的最短路径问题
 * 双机位C卷
 * difficulty:5
 * @author luqi
 */
public class P1023 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};

    public static void main(String[] args) {

        if (sc.hasNext()) {
            String[] split = sc.nextLine().trim().split("\\s+");
            String one = split[0];
            String two = split[1];

            int[][] map = new int[one.length() + 1][two.length() + 1];
            // bfs算法遍历
            int minDistance = bfs(one,two);
            System.out.println(minDistance);

        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static int bfs(String one, String two) {
        // 初始化
        int[][] map = new int[one.length() + 1][two.length() + 1];
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[one.length() + 1][two.length() + 1];
        int distance = 0;
        queue.offer(new int[]{0,0});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                // 跳出条件
                if (poll[0] == one.length() && poll[1] == two.length()) {
                    return distance;
                }

                for (int[] direction : directions) {
                    int nextX = poll[0] + direction[0];
                    int nextY = poll[1] + direction[1];
                    if (nextX < 0 || nextY < 0 || nextX > one.length() || nextY > two.length()) {
                        continue;
                    }
                    // 斜向移动需要判断是否满足条件
                    if(Math.abs(direction[0]) == Math.abs(direction[1])){
                        if ( nextX > 0 && nextY > 0 && (one.charAt(nextX - 1) != two.charAt(nextY - 1))) {
                            continue;
                        }
                    }

                    if (!visited[nextX][nextY]) {
                        queue.offer(new int[]{nextX,nextY});
                        visited[nextX][nextY] = true;
                    }
                }
            }
            distance++;
        }
        return -1;
    }
}
