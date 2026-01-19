package com.myself.learn.algo.huawei.DC2025;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * P00498.华为od机试—统计员工影响力分数
 * 双机位C卷
 * difficulty:3
 * @author luqi
 */
public class P00498 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            List<Integer>[] directRelations = new ArrayList[n];
            List<Integer>[] relations = new ArrayList[n];
            Arrays.fill(relations, new ArrayList<>());
            int[] relationScores = new int[n];
            boolean[] readed = new boolean[n];
            sc.nextLine();
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine().trim();
                if ("*".equals(line)) {
                    directRelations[i] = new ArrayList<>();
                }else {
                    directRelations[i] = Arrays.stream(line.split("\\s+"))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                }
            }
            // 计算关系树
            for (int i = 0; i < n; i++) {
                relationScores[i] = bfs(i,directRelations,n);
            }

            String result = Arrays.stream(relationScores).mapToObj(String::valueOf).collect(Collectors.joining(","));

            System.out.println(result);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static int bfs(int i, List<Integer>[] directRelations, int n) {
        // 初始化
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        int score = 0;

        queue.offer(i);
        visited[i] = true;
        // 选择
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            for (Integer item : directRelations[current]) {
                if (!visited[item]) {
                    queue.offer(item);
                    visited[item] = true;
                    score++;
                }
            }
        }
        return score;
    }
}
