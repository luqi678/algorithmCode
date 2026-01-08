package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * P00436.华为od机试—最优芯片资源占用_最优资源分配_芯片资源占用
 * 包含：Scanner初始化、常用输入模式、常用工具函数
 * 要求： 稳 < 10^7 < 可能出现问题 < 10^8 < 可能出现问题 < 3.1 * 10^8 < 一定有问题
 */
public class C436 {

    // 全局静态 Scanner，方便在任何函数中使用
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        if (sc.hasNext()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            sc.nextLine();
            String input = sc.nextLine();
            // TODO: 逻辑处理
            int[] nums = new int[n];
            Arrays.fill(nums, m);
            for (char c : input.trim().toCharArray()) {
                int t ;
                if ('A' == c) {
                    // 1
                    t = 1;
                }else if ('B' == c)  {
                    // 2
                    t = 2;
                }else if ('C' == c) {
                    // 8
                    t = 8;
                }else {
                    continue;
                }
                if (t > m) {
                    continue;
                }
                for (int i = 0, numsLength = nums.length; i < numsLength; i++) {
                    if (nums[i] >= t) {
                        nums[i] -= t;
                        break;
                    }
                }
            }
            // 打印结果
            for (int num : nums) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < m-num; i++) {
                    sb.append("1");
                }
                for (int i = 0; i < num; i++) {
                    sb.append("0");
                }
                System.out.println(sb.toString());
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