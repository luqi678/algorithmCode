package com.myself.learn.algo.huawei.doubleC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 490-约瑟夫问题
 * `m`。
 *
 * 从数列首位置开始计数，计数到 `m` 后，将数列该位置数值替换计数值 `m`，并将数列该位置数值出列，然后从下一位置重新开始计数，直到数列所有数值出列为止。
 *
 * 如果计数到达数列尾段，则返回数列首位置继续计数。
 *
 * 请编程实现上述计数过程，同时输出数值出列的顺序。
 **/
public class DoubleC_490 {
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String inputNList = sc.nextLine();
        List<Integer> items = Arrays.stream(inputNList.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());

        if (!sc.hasNextLine()) {
            return;
        }
        String inputN = sc.nextLine();
        int curIndex = 0;
        int n = Integer.parseInt(inputN.trim());
        int size = items.size();
        List<Integer> res = new ArrayList<>();

        while (res.size() < size) {
            curIndex = (n - 1 + curIndex) % items.size();
            n = items.get(curIndex);
            res.add(n);
            items.remove(curIndex);
        }
        System.out.println(res);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String inputNList = sc.nextLine();

        // 修改点1：兼容 Java 8 写法，使用 collect(Collectors.toList())
        List<Integer> items = Arrays.stream(inputNList.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList()); //转为可变List

        if (!sc.hasNextLine()) {
            return;
        }
        String inputN = sc.nextLine();

        // 这里为了逻辑清晰，变量名建议和题目保持一致，m 代表步长
        int m = Integer.parseInt(inputN.trim());

        int curIndex = 0;
        int size = items.size();
        List<Integer> res = new ArrayList<>();

        while (res.size() < size) {
            // 逻辑完全没问题
            curIndex = (curIndex + m - 1) % items.size();

            int val = items.get(curIndex);
            res.add(val);

            // 更新步长 m
            m = val;

            items.remove(curIndex);
        }

        // 修改点2：按题目要求格式化输出 (2,3,1,4)
        // 方法 A (Java 8 Stream):
        String output = res.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.println(output);

        // 方法 B (传统 StringBuilder，如果觉得 Stream 慢):
        /*
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i));
            if (i < res.size() - 1) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
        */
    }
}