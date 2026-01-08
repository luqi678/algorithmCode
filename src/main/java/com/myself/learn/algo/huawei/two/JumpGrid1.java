package com.myself.learn.algo.huawei.two;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 跳格子 1
 * @author 2405051
 */
public class JumpGrid1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String inputItems = sc.nextLine();
        List<Integer> items = Arrays.stream(inputItems.replaceAll("\\[", "")
                        .replaceAll("]", "").split(","))
                .map(e->Integer.valueOf(e.trim())).collect(Collectors.toList());
        if (!sc.hasNextLine()) {
            return;
        }
        String inputDistance = sc.nextLine();
        Integer distance = Integer.valueOf(inputDistance.trim());

        int minIndex = Integer.MAX_VALUE;
        int bestOne = 0,bestTwo = 0;

        for (int i = 0; i < items.size() - 1; i++) {
            Integer one = items.get(i);
            for (int j = i + 1; j < items.size(); j++) {
                Integer two = items.get(j);
                if (one + two == distance) {
                    int index = i + j;
                    if (index < minIndex) {
                        minIndex = index;
                        bestOne = one;
                        bestTwo = two;
                    }
                }
            }
        }
        List<Integer> res = Arrays.asList(bestOne, bestTwo);
        System.out.println(res);

    }
}