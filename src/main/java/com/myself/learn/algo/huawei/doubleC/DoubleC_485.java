package com.myself.learn.algo.huawei.doubleC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * P00485. 华为od机试—结队编程
 **/
public class DoubleC_485 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String inputN = sc.nextLine();
        int n = Integer.parseInt(inputN.trim());

        if (!sc.hasNextLine()) {
            return;
        }
        String line = sc.nextLine();
        int[] level = Arrays.stream(line.split("\\s+"))
                .map(String::trim).mapToInt(Integer::parseInt).toArray();

        long sum = 0;

        for (int i = 1; i < level.length - 1; i++) {
            int center = level[i];
            List<Integer> leftLows = new ArrayList<>();
            List<Integer> leftHighs = new ArrayList<>();
            List<Integer> rightLows = new ArrayList<>();
            List<Integer> rightHighs = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (level[j] < center) {
                    leftLows.add(level[j]);
                }else {
                    leftHighs.add(level[j]);
                }
            }
            for (int k = i + 1; k < level.length; k++) {
                if (level[k] < center) {
                    rightLows.add(level[k]);
                }else {
                    rightHighs.add(level[k]);
                }
            }
            sum = sum + (long) leftLows.size() * rightHighs.size() + (long) leftHighs.size() * rightLows.size();

        }

        System.out.println(sum);

    }
}