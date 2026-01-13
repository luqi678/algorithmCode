package com.myself.learn.algo.huawei.C2025;

import java.util.Scanner;

/**
 * P00405.华为od机试—5键键盘
 * 2025C卷
 * difficulty:4
 * @author luqi
 */
public class C405 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        int chooseCount = 0;
        int copyCount = 0;
        int res = 0;
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    // a
                    res = res - chooseCount + 1;
                    chooseCount = 0;
                    break;
                case 2:
                    // ctrl + c
                    if (chooseCount > 0) {
                        copyCount = chooseCount;
                    }
                    break;
                case 3:
                    // ctrl + x
                    if (chooseCount > 0) {
                        copyCount = chooseCount;
                        res -= chooseCount;
                        chooseCount = 0;
                    }
                    break;
                case 4:
                    // ctrl + v
                    res = res - chooseCount + copyCount;
                    chooseCount = 0;
                    break;
                case 5:
                    // ctrl + a
                    chooseCount = res;
                    break;
                default:
                    break;
            }
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();

        System.out.println(res);
    }
}
