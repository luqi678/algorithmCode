package com.myself.learn.algo.huawei.doubleC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 华为OD机试万能模板
 * 包含：Scanner初始化、常用输入模式、常用工具函数
 */
public class DoubleC_475 {

    // 全局静态 Scanner，方便在任何函数中使用
    static Scanner sc = new Scanner(System.in);

    static class Node {
        public int x;
        public int y;
        public int p;
        public Node(int x, int y, int p) {
            this.x = x;
            this.y = y;
            this.p = p;
        }
    }

    public static void main(String[] args) {

        /* * ============================================================
         * 模式 A: 固定行数/组数输入
         * 场景：第一行是 N，后面紧跟 N 行数据
         * ============================================================
         */

        if (sc.hasNext()) {
            int n = sc.nextInt();
            int d = sc.nextInt();
            sc.nextLine(); // 【重要】吃掉整数后的换行符

            int minX = 0, maxX = 0, minY = 0, maxY = 0;
            List<Node> nodes = new ArrayList<Node>();
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                // TODO: 处理每一行逻辑
                System.out.println("\n处理第 " + (i+1) + " 行: " + line);

                String[] split = line.split("\\s+");
                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[1]);
                nodes.add(new Node(x, y, Integer.parseInt(split[2])));
                minX = Math.min(minX, x - d);
                maxX = Math.max(maxX, x + d);
                minY = Math.min(minY, y - d);
                maxY = Math.max(maxY, y + d);
            }
            List<Node> result = new ArrayList<Node>();
            for (int i = minX; i <= maxX; i++) {
                for (int j = minY; j <= maxY; j++) {
                    nodes.forEach(node -> {

                    });
                }
            }





        }


        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
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