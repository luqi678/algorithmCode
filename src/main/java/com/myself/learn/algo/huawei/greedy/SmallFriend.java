package com.myself.learn.algo.huawei.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 小朋友高矮排序
 * @author 2405051
 */
public class SmallFriend {

    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] in = s.split(" ");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < in.length; i++) {
            int number = Integer.parseInt(in[i]);
            list.add(number);
            System.out.println(number);
        }

        // 算法
        for (int i = 0; i < list.size() - 1; i++) {
            if(!list.get(i).equals(list.get(i + 1)) && i%2 == 0 != list.get(i) > list.get(i+1)){
                Integer temp = list.get(i);
                list.set(i, list.get(i+1));
                list.set(i+1, temp);
            }



        }
        System.out.println(list.stream().map(Object::toString).collect(Collectors.joining(" ")));

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 检查是否有输入
        if (!sc.hasNextLine()) return;

        String input = sc.nextLine();

        // 1. 非法参数检查：使用正则表达式判断是否只包含数字和空格
        if (!input.matches("[0-9\\s]+")) {
            System.out.println("[]");
            return;
        }

        // 2. 数据转换：将字符串转为整数数组
        int[] heights;
        try {
            heights = Arrays.stream(input.trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (Exception e) {
            System.out.println("[]");
            return;
        }

        // 3. 贪心交换逻辑
        for (int i = 0; i < heights.length - 1; i++) {
            // heights[i] != heights[i+1] 是为了避免相等时做无谓交换
            // (heights[i] > heights[i+1]) != (i % 2 == 0) 是判断的核心逻辑
            if (heights[i] != heights[i+1] && (heights[i] > heights[i+1]) != (i % 2 == 0)) {
                // 发现不符合规则，直接交换相邻元素
                int temp = heights[i];
                heights[i] = heights[i+1];
                heights[i+1] = temp;
            }
        }

        // 4. 格式化输出：使用 StringJoiner 以空格分隔
        StringJoiner sj = new StringJoiner(" ");
        for (int h : heights) {
            sj.add(String.valueOf(h));
        }
        System.out.println(sj.toString());
    }



}