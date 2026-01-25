package com.myself.learn.exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 描述：
 * This is a three.
 *
 * @author lujian
 * @since 2026/1/21.
 */
public class three {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static String res = "";

    public static void main(String[] args) {

        if (sc.hasNext()) {
            // 定义接受两个变量数组
            List<int[]> smfList = new ArrayList<>();
            List<int[]> upfList = new ArrayList<>();
            // 接受第一个变量数组smf
            int n = Integer.parseInt(sc.nextLine().trim());
            for (int i = n - 1; i >= 0; i--) {
                int[] array = Arrays.stream(sc.nextLine().trim().split("\\s+")).map(String::trim).mapToInt(Integer::parseInt).toArray();
                smfList.add(array);
            }
            // 接受第二个变量数组upf
            int m = Integer.parseInt(sc.nextLine().trim());
            for (int i = m - 1; i >= 0; i--) {
                int[] array = Arrays.stream(sc.nextLine().trim().split("\\s+")).map(String::trim).mapToInt(Integer::parseInt).toArray();
                upfList.add(array);
            }
            int[][] distance = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    // 计算全部的距离
                    distance[i][j] = Math.max(Math.abs(smfList.get(i)[0] - upfList.get(j)[0])
                            , Math.abs(smfList.get(i)[1] - upfList.get(j)[1]));
                }
            }
            boolean[] smfVisited = new boolean[n];
            boolean[] upfVisited = new boolean[m];

            // 定义返回总距离
            int totalDistance = 0;
            // 每个smf都需要匹配到
            for (int k = 0; k < n; k++) {
                // 获取distance最小值
                int minDistance = Integer.MAX_VALUE;
                int[] minIndex = new int[]{-1, -1};
                for (int i = 0; i < n; i++) {
                    // 减枝
                    if (smfVisited[i]) {
                        continue;
                    }
                    for (int j = 0; j < m; j++) {
                        // 减枝
                        if (upfVisited[j]) {
                            continue;
                        }
                        if (distance[i][j] < minDistance) {
                            minDistance = distance[i][j];
                            minIndex = new int[]{i, j};
                        }
                    }
                }
                totalDistance += minDistance;
                smfVisited[minIndex[0]] = true;
                upfVisited[minIndex[1]] = true;
                //System.out.println("Selected SMF: " + minIndex[0] + ", Selected UPLink: " + minIndex[1] + ", Distance: " + minDistance);
            }

            System.out.println(totalDistance);
        }

        // 关闭输入
        sc.close();
    }

}
