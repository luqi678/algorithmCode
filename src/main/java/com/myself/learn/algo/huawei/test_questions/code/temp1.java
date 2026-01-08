package com.myself.learn.algo.huawei.test_questions.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 华为OD机试万能模板
 * 包含：Scanner初始化、常用输入模式、常用工具函数
 */
public class temp1 {

    // 全局静态 Scanner，方便在任何函数中使用
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        /* * ============================================================
         * 模式 A: 固定行数/组数输入
         * 场景：第一行是 N，后面紧跟 N 行数据
         * ============================================================
         */
        /*
        if (sc.hasNext()) {
            int n = sc.nextInt();
            sc.nextLine(); // 【重要】吃掉整数后的换行符

            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                // TODO: 处理每一行逻辑
                System.out.println("处理第 " + (i+1) + " 行: " + line);
            }
        }
        */

        /* * ============================================================
         * 模式 B: 多行输入，直到 EOF (End Of File)
         * 场景：题目没说多少行，只说“多组输入”
         * ============================================================
         */
        /*
        while (sc.hasNext()) {
            String line = sc.next(); // 或者 nextInt()
            // TODO: 处理逻辑
            System.out.println("读入: " + line);
        }
        */
        if (!sc.hasNextLine()) {
            return;
        }
        String s = sc.nextLine();
        int k = Integer.parseInt(s);

        /* * ============================================================
         * 模式 C: 一行字符串转数组 (空格分隔) (逗号分隔)
         * 场景：输入 "1 2 3 4 5" "1,2,3,4,5"
         * ============================================================
         */
        if (sc.hasNextLine()) {
            String line = sc.nextLine();
            // 使用下方封装的工具方法快速转换
            String[] split = line.split("-");
            List<String> list = new ArrayList<>();
            list.add(split[0]);
            for (int i = 1; i < split.length; i++) {
                String trim = split[i].trim();
                while (!trim.isEmpty()) {
                    int subLen = Math.min(k, trim.length());
                    String substring = trim.substring(0, subLen);
                    long upNum = substring.chars().filter(e->e>='A' & e <= 'Z').count();
                    long downNum = substring.chars().filter(e->e>='a' & e <= 'z').count();
                    if (upNum > downNum) {
                        substring = substring.toUpperCase();
                    }
                    if (downNum > upNum) {
                        substring = substring.toLowerCase();
                    }
                    list.add(substring);
                    trim = trim.substring(subLen);
                }
            }




            // TODO: 逻辑处理
            System.out.println(Arrays.toString(list.toArray()));
        }

        /* * ============================================================
         * 模式 E: 二维矩阵输入
         * 场景：第一行 m n，后续 m 行数据
         * ============================================================
         */
        /*
        if (sc.hasNext()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int[][] grid = new int[m][n];

            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    grid[i][j] = sc.nextInt();
                }
            }
            // TODO: 逻辑处理
        }
        */

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