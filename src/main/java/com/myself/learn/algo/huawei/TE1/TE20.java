package com.myself.learn.algo.huawei.TE1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

/**
 * 20- 岛屿数量
 * difficulty:中等
 * frequency:高
 * @author luqi
 */
public class TE20 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);

    static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {

        if (sc.hasNext()) {
            // 岛屿数量
            int count = 0;
            // 矩阵
            List<int[]> list = new ArrayList<>();
            while (sc.hasNextLine()) {
                int[] array = Arrays.stream(sc.nextLine().trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
                list.add(array);
            }
            // 遍历
            for (int i = 0; i < list.size(); i++) {
                int[] array = list.get(i);
                for (int j = 0; j < array.length; j++) {
                    int type = array[j];
                    // 水跳过 已经遍历过的岛屿也跳过
                    if (type == 0 || type == 2) {
                        continue;
                    }
                    // 陆地进行计算岛屿
                    Deque<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{i, j});
                    count++;
                    array[j] = 2;
                    while (!queue.isEmpty()) {
                        int[] poll = queue.poll();
                        int x = poll[0];
                        int y = poll[1];
                        // 上
                    }
                }
            }
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
