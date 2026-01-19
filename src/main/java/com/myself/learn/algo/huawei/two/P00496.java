package com.myself.learn.algo.huawei.two;

import java.util.Scanner;

/**
 * P00496.华为od机试—抢7游戏
 * 双机位C卷
 * difficulty:4
 * @author luqi
 */
public class P00496 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();

            // 动态规划实现
            // n为阶段  countA[i] 为A可以喊道i的方案数  countB[i] 为B可以喊道i的方案数
            // 求解 countB[7]的值
            // 状态转移方程：countB[i] = countA[i-1] + countA[i-2] ; countA[i] = countB[i-1] + countB[i-2]
            // 初始条件：countA[0] = 1, countA[1] = 1, countB[0] = 1, countB[1] = 1
            int prePreA = 1, prePreB = 0, preA = 0, preB = 1;
            for (int i = n - 3; i >= 7; i--) {
                int oldPrePreA = prePreA, oldPrePreB = prePreB, oldPreA = preA, oldPreB = preB;
                preA = oldPrePreB + oldPreB;
                preB = oldPrePreA + oldPreA;
                prePreA = oldPreA;
                prePreB = oldPreB;
            }
            System.out.println(preB);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
