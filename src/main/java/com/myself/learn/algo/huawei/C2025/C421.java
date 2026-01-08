package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * P00421.华为od机试—快速开租建站
 * 2025C卷
 * difficulty:4
 * @author luqi
 */
public class C421 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int taskNum = sc.nextInt();
            int relationsNum = sc.nextInt();
            // 度 列表
            int[] degree = new int[taskNum];
            int[][] relations = new int[relationsNum][2];
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < relationsNum; i++) {
                int pre = sc.nextInt();
                int next = sc.nextInt();
                relations[i][0] = pre;
                relations[i][1] = next;
                map.computeIfAbsent(pre, k -> new ArrayList<>()).add(next);
                degree[next]++;
            }

            Queue<Integer> queue = new ArrayDeque<>();
            // 查找没有依赖的先入栈
            boolean[] visited = new boolean[taskNum];
            for (int i = 0; i < relations.length; i++) {
                visited[relations[i][1]] = true;
            }
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    queue.offer(i);
                }
            }
            // BFS
            int step = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Integer current = queue.poll();
                    map.getOrDefault(current, new ArrayList<>()).forEach(next -> {
                        degree[next]--;
                        if (degree[next] == 0) {
                            queue.offer(next);
                        }
                    });
                }
                step++;
            }
            System.out.println(step);

        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
