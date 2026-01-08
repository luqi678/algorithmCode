package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * P00463. 华为od机试—田忌赛马
 * 包含：Scanner初始化、常用输入模式、常用工具函数
 */
public class C463 {

    // 全局静态 Scanner，方便在任何函数中使用
    static Scanner sc = new Scanner(System.in);

    static int[] a;
    static int[] b;
    static int[] res;
    static int max;
    static int maxNum;

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

        /* * ============================================================
         * 模式 C: 一行字符串转数组 (空格分隔) (逗号分隔)
         * 场景：输入 "1 2 3 4 5" "1,2,3,4,5"
         * ============================================================
         */
        if (sc.hasNextLine()) {
            String line = sc.nextLine();
            // 使用下方封装的工具方法快速转换
            a = toIntArray(line, "\\s+");
        }

        if (sc.hasNextLine()) {
            String line = sc.nextLine();
            // 使用下方封装的工具方法快速转换
            b = toIntArray(line, "\\s+");
        }
        // 用dfs 实现深度优先算法
        int n = a.length;
        res = new int[n + 1];
        int[] temp = new int[n];
        int[] visit = new int[n];

        trackBack(a,visit,temp,0);
        System.out.println(maxNum);


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

    private static void trackBack(int[] a, int[] visit, int[] temp, int index) {
        if (index == a.length) {
            // 判断临时和b大小个数
            int num = 0;
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] > b[i]) {
                    num ++;
                }
            }
            if(num > max){
                max = num;
                maxNum = 1;
            }else if(num == max){
                maxNum++;
            }


            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (visit[i] == 0) {
                temp[index] = a[i];
                visit[i] = 1;
                trackBack(a, visit, temp, index + 1);
                visit[i] = 0;
            }
        }


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