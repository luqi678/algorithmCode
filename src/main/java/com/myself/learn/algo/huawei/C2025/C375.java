package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * P00375.华为od机试—最大社交距离
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class C375 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static int[] directions = new int[]{-1, 1};


    public static void main(String[] args) {

        if (sc.hasNext()) {
            // 总位子数
            int seatNum = sc.nextInt();
            sc.nextLine();
            // 进出顺序
            int[] seatOrLeave  = Arrays.stream(sc.nextLine().trim().replaceAll("[\\[\\]]", "").split(","))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            // 作为距离情况
            int[] distance = new int[seatNum];
            Arrays.fill(distance, Integer.MAX_VALUE);
            int maxDistanceIndex = 0;
            // 遍历进出顺序
            for (int i = 0; i < seatOrLeave.length; i++) {
                if (seatOrLeave[i] == 1) {
                    // 进站
                    // 访问过的索引
                    boolean[] visited = new boolean[seatNum];
                    // 获取最远距离并更新距离
                    visited[maxDistanceIndex] = true;
                    distance[maxDistanceIndex] = 0;

                    // 打印验证
                    // System.out.println(maxDistanceIndex);

                    maxDistanceIndex = getMaxDistanceIndex(distance, maxDistanceIndex, visited);

                    if (maxDistanceIndex == -1) {
                        System.out.println(maxDistanceIndex);
                        return;
                    }
                    // 打印
                    if (i == seatOrLeave.length - 1) {
                        System.out.print(maxDistanceIndex);
                    }
                } else if (seatOrLeave[i] < 0) {
                    // 出站
                    int indexAbs = Math.abs(seatOrLeave[i]);
                    distance[indexAbs] = Integer.MAX_VALUE;

                    boolean[] visited = new boolean[seatNum];
                    maxDistanceIndex = getMaxDistanceIndex(distance, maxDistanceIndex, visited);
                    // 打印验证
                    // System.out.println(seatOrLeave[i]);
                    // System.out.println(maxDistanceIndex);
                    // System.out.println(Arrays.toString(distance));
                }
            }
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static int getMaxDistanceIndex(int[] distance, int maxDistanceIndex, boolean[] visited) {
        List<Integer> zeroIndexs = new ArrayList<>();
        for (int zeroI = 0; zeroI < distance.length; zeroI++) {
            if (distance[zeroI] == 0) {
                zeroIndexs.add(zeroI);
            }
        }
        Queue<Integer> queue = new ArrayDeque<>(zeroIndexs);
        int step = 1;
        int maxStep = 0;
        boolean found = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Integer poll = queue.poll();
                for (int direction : directions) {
                    int next = poll + direction;
                    // 越界
                    if (next < 0 || next >= distance.length) {
                        continue;
                    }
                    // 访问过
                    if (visited[next] || distance[next] == 0) {
                        continue;
                    }
                    visited[next] = true;
                    queue.offer(next);
                    distance[next] = step;
                    if (step > maxStep) {
                        maxStep = step;
                        maxDistanceIndex = next;
                        found = true;
                    }
                }
            }
            step++;
        }
        return found ? maxDistanceIndex : -1;
    }
}
