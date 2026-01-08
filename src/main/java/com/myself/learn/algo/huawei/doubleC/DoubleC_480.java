package com.myself.learn.algo.huawei.doubleC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 华为OD机试万能模板
 * 包含：Scanner初始化、常用输入模式、常用工具函数
 */
public class DoubleC_480 {

    // 全局静态 Scanner，方便在任何函数中使用
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        /* * ============================================================
         * 模式 A: 固定行数/组数输入
         * 场景：第一行是 N，后面紧跟 N 行数据
         * ============================================================
         */
        if (sc.hasNext()) {
            int n = sc.nextInt();
            sc.nextLine(); // 【重要】吃掉整数后的换行符

            int[][] arr = new int[201][201];

            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                // TODO: 处理每一行逻辑
                // System.out.println("处理第 " + (i+1) + " 行: " + line);
                String[] split = line.split(" ");
                String operator = split[0];
                int x1 = Integer.parseInt(split[1]) + 100;
                int y1 = Integer.parseInt(split[2]) + 100;
                int x2 = Integer.parseInt(split[3]) + 100;
                int y2 = Integer.parseInt(split[4]) + 100;
                if ("d".equalsIgnoreCase(operator)) {
                    for (int j = y2; j < y1; j++) {
                        for (int k = x1; k < x2; k++) {
                            arr[j][k] = 1;
                        }
                    }
                }else if ("e".equalsIgnoreCase(operator)) {
                    for (int j = y2; j < y1; j++) {
                        for (int k = x1; k < x2; k++) {
                            arr[j][k] = 0;
                        }
                    }
                }else {
                    System.out.println("0");
                    return;
                }
            }

            // 计算最后面积
            int sum = 0;
            for (int j = 0; j < 200; j++) {
                for (int k = 0; k < 200; k++) {
                    sum += arr[j][k];
                }
            }
            System.out.println(sum);
        }
        sc.close();
    }

    /**
     * 工具方法：将包含分隔符的字符串转换为 int 数组
     * @param line 输入的字符串 (如 "1 2 3" 或 "1,2,3")
     * @param splitter 分隔符 (如 " " 或 ",")
     * @return int[] 数组
     */
    public static int[] toIntArray(String line, String splitter) {
        if (line == null || line.length() == 0) return new int[0];

        String[] parts = line.trim().split(splitter);
        int[] res = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            // 防止有连续空格导致空字符串解析报错
            if (!parts[i].isEmpty()) {
                res[i] = Integer.parseInt(parts[i].trim());
            }
        }
        return res;
    }

    /**
     * 工具方法：将包含分隔符的字符串转换为 List<Integer>
     */
    public static List<Integer> toIntList(String line, String splitter) {
        List<Integer> list = new ArrayList<>();
        if (line == null || line.length() == 0) return list;

        String[] parts = line.trim().split(splitter);
        for (String part : parts) {
            if (!part.isEmpty()) {
                list.add(Integer.parseInt(part.trim()));
            }
        }
        return list;
    }

    /**
     * 工具方法：按指定格式输出数组 (例如题目要求输出 "1 2 3"，行尾无空格)
     */
    public static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}