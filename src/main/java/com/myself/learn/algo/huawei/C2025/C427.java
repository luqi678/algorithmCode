package com.myself.learn.algo.huawei.C2025;

import java.util.Scanner;

/**
 * P00427.华为od机试—人数最多的站点 / 小火车人数最多所在站点
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class C427 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            int maxEnd = 0;
            int[][] arr = new int[n][2];
            for (int i = 0; i < n; i++) {
                int begin = sc.nextInt();
                int end = sc.nextInt();
                arr[i][0] = begin;
                arr[i][1] = end;
                maxEnd = Math.max(maxEnd, end);
            }
            int[] times = new int[maxEnd + 1];
            for (int i = 0; i < n; i++) {
                for (int j = arr[i][0]; j <= arr[i][1]; j++) {
                    times[j]++;
                }
            }
            int max = 0;
            int maxIndex = 0;
            for (int i = 0; i < times.length; i++) {
                if (times[i] > max) {
                    max = times[i];
                    maxIndex = i;
                }
            }
            System.out.println(maxIndex);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    public static void main1(String[] args) {
        if (sc.hasNext()) {
            int n = sc.nextInt();
            // 预读取数据以确定最大站点，或者直接根据题目约束开一个足够大的数组
            // 假设题目未给出最大站点限制，我们动态获取
            int[][] trips = new int[n][2];
            int maxStation = 0;

            for (int i = 0; i < n; i++) {
                trips[i][0] = sc.nextInt();
                trips[i][1] = sc.nextInt();
                maxStation = Math.max(maxStation, trips[i][1]);
            }

            // 差分数组
            // 大小需要 maxStation + 2，因为我们要操作 end + 1
            int[] diff = new int[maxStation + 2];

            for (int i = 0; i < n; i++) {
                int start = trips[i][0];
                int end = trips[i][1];

                // 核心：区间 [start, end] 人数+1
                // 等价于 diff[start] +1, diff[end + 1] -1
                diff[start]++;
                diff[end + 1]--;
            }

            int maxPeople = 0;
            int maxStationIndex = 0;
            int currentPeople = 0;

            // 遍历并计算前缀和（还原每站人数）
            for (int i = 1; i <= maxStation; i++) {
                currentPeople += diff[i]; // 前缀和即为当前站点人数

                if (currentPeople > maxPeople) {
                    maxPeople = currentPeople;
                    maxStationIndex = i;
                }
            }

            System.out.println(maxStationIndex);
        }
        sc.close();
    }
}
