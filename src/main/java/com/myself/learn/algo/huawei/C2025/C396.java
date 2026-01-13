package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.Scanner;

/**
 * P00396.华为od机试—数字涂色
 * 2025C卷
 * difficulty:4
 * @author luqi
 */
public class C396 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            int[] matrix = new int[n];
            boolean[] isReady = new boolean[n];
            for (int i = 0; i < n; i++) {
                int number = sc.nextInt();
                matrix[i] = number;
            }
            Arrays.sort(matrix);
            int colorNumber = 0;
            int readyNumber = 0;

            for (int i = 0; i < matrix.length; i++) {
                if (isReady[i]) {
                    continue;
                }
                colorNumber++;
                for (int j = i; j < matrix.length; j++) {
                    if (!isReady[j] && matrix[j] % matrix[i] == 0) {
                        readyNumber++;
                        isReady[j] = true;
                    }
                }
                if (readyNumber >= n) {
                    System.out.println(colorNumber);
                    break;
                }
            }
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
