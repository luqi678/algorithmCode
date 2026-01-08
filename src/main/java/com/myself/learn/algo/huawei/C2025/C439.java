package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * P00439.华为od机试—统一限载最小值
 * 包含：Scanner初始化、常用输入模式、常用工具函数
 * 要求： 稳 < 10^7 < 可能出现问题 < 10^8 < 可能出现问题 < 3.1 * 10^8 < 一定有问题
 */
public class C439 {

    // 全局静态 Scanner，方便在任何函数中使用
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        /* * ============================================================
         * 模式 A: 固定行数/组数输入
         * 场景：第一行是 N，后面紧跟 N 行数据
         * ============================================================
         */
        if (sc.hasNext()) {
            int length = sc.nextInt();
            sc.nextLine(); // 【重要】吃掉整数后的换行符
            String line = sc.nextLine();
            int[] goods = toIntArray(line, "\\s+");
            String line2 = sc.nextLine();
            int[] types = toIntArray(line2, "\\s+");
            int k = sc.nextInt();

            int dryMin = Integer.MAX_VALUE;
            int wetMin = Integer.MAX_VALUE;

            int dryTotal = 0;
            int wetTotal = 0;
            List<Integer> dryGoods = new ArrayList<>();
            List<Integer> wetGoods = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                if (types[i] == 0) {
                    dryGoods.add(goods[i]);
                    dryTotal += goods[i];
                }else {
                    wetGoods.add(goods[i]);
                    wetTotal += goods[i];
                }
            }
            // 二分法判断干的
            int left = 0,right = dryTotal;
            while (left <= right) {
                int mid = left + (right - left)/2;
                if(check(dryGoods,k,mid)){
                    // 尝试更小的限重
                    dryMin = Math.min(dryMin,mid);
                    right = mid - 1;
                }else {
                    // 尝试更大的限重
                    left = mid + 1;
                }
            }
            // 二分法判断湿的
            left = 0;right = wetTotal;
            while (left <= right) {
                int mid = left + (right - left)/2;
                if(check(wetGoods,k,mid)){
                    // 尝试更小的限重
                    wetMin = Math.min(wetMin,mid);
                    right = mid - 1;
                }else {
                    // 尝试更大的限重
                    left = mid + 1;
                }
            }

            System.out.println(Math.max(dryMin,wetMin));
        }
        sc.close();
    }

    private static boolean check(List<Integer> dryGoods, int k, int weight) {
        int w = weight;
        for (int i = 0; i < dryGoods.size(); ) {
            if (k <= 0) {
                return false;
            }
            w -= dryGoods.get(i);
            if (w < 0) {
                k --;
                w = weight;
            }else {
                i ++;
            }
        }
        return true;
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