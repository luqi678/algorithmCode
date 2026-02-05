package com.myself.learn.algo.huawei.TE1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 23- 分发糖果
 * difficulty:困难
 * frequency:中
 * @author luqi
 */
public class TE23 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int[] rates = Arrays.stream(sc.nextLine().trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            int[] leftNumbers = new int[rates.length];
            int[] rightNumbers = new int[rates.length];
            Arrays.fill(leftNumbers, 1);
            Arrays.fill(rightNumbers, 1);
            int n = 0;
            for (int i = 1; i < rates.length; i++) {
                if (rates[i] > rates[i - 1]) {
                    leftNumbers[i] = leftNumbers[i - 1] + 1;
                }
            }
            for (int i = rates.length - 2; i >= 0; i--) {
                if (rates[i] > rates[i + 1]) {
                    rightNumbers[i] = rightNumbers[i + 1] + 1;
                }
            }
            for (int i = 0; i < rates.length; i++) {
                n += Math.max(leftNumbers[i], rightNumbers[i]);
            }

            System.out.println(n);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
