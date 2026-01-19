package com.myself.learn.algo.huawei.two;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 特殊的加密算法
 * 包含：Scanner初始化、常用输入模式、常用工具函数
 * 要求： 稳 < 10^7 < 可能出现问题 < 10^8 < 可能出现问题 < 3.1 * 10^8 < 一定有问题
 */
public class Special {

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
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            int m = sc.nextInt();
            Map<Integer, int[]> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    int key = sc.nextInt();
                    map.putIfAbsent(key, new int[]{i, j});
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                int[] ints = map.get(nums[i]);
                if (ints == null) {
                    System.out.println("error");
                    return;
                }else {
                    sb.append(ints[0]).append(" ").append(ints[1]).append(" ");
                }
            }
            System.out.println(sb.toString().trim());


        }

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
        /*
        if (sc.hasNextLine()) {
            String line = sc.nextLine();
            // 使用下方封装的工具方法快速转换
            int[] nums = toIntArray(line, " ");
            int[] nums = toIntArray(line, "\\s+");

            // TODO: 逻辑处理
            System.out.println(Arrays.toString(nums));
        }
        */

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
    /**
     * 通用矩阵解析器：处理 [[...],[...]] 格式，自动忽略空格和非数字字符
     */
    private static int[][] parseMatrix(String str) {
        if (str == null || str.isEmpty()) return new int[0][0];

        // 使用正则匹配每一行内容 [...]
        List<int[]> rows = new ArrayList<>();
        // 匹配嵌套在 [] 里的内容
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("\\[([^\\[\\]]+)\\]").matcher(str);

        while (m.find()) {
            String rowContent = m.group(1);
            String[] parts = rowContent.split(",");
            int[] row = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                row[i] = Integer.parseInt(parts[i].trim());
            }
            rows.add(row);
        }
        return rows.toArray(new int[0][]);
    }
}