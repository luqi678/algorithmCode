package com.myself.learn.algo.huawei.doubleC;

import java.util.Arrays;
import java.util.Scanner;

/**
 * P00482. 华为od机试—贪吃的猴子
 **/
public class DoubleC_482 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String inputN = sc.nextLine();
        int len = Integer.parseInt(inputN.trim());

        if (!sc.hasNextLine()) {
            return;
        }
        String line = sc.nextLine();
        int[] numbers = Arrays.stream(line.split("\\s+"))
                .map(String::trim).mapToInt(Integer::parseInt).toArray();

        if (!sc.hasNextLine()) {
            return;
        }
        String times = sc.nextLine();
        int n = Integer.parseInt(times.trim());

        // 求一个连续窗口的最小值
        int allSum = 0;
        int minSum = 0;
        int minIndex = 0;
        int sum = 0;
        if (numbers.length <= n) {
            for (int number : numbers) {
                allSum += number;
            }
            System.out.println(allSum);
            return;
        }

        int tapLen = numbers.length - n;
        for (int j = 0; j < tapLen; j++) {
            sum += numbers[j];
        }
        for (int i = 0; i < numbers.length - tapLen + 1; i++) {
            int number = numbers[i];
            if (i==0) {
                minSum = sum;
                minIndex = i;
                allSum = sum;
            }else {
                sum = sum - numbers[i - 1];
                sum = sum + numbers[(i + tapLen - 1)];
                allSum += numbers[(i + tapLen - 1)];
                if (sum < minSum) {
                    minSum = sum;
                    minIndex = i;
                }
            }
        }
        System.out.println(allSum - minSum);
    }
}