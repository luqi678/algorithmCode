package com.myself.learn.algo.huawei.two;

import java.util.Scanner;

/**
 * P00496.华为od机试—运输时间
 * 双机位C卷
 * difficulty:4
 * @author luqi
 */
public class P004962 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            // n辆车
            int n = sc.nextInt();
            // m距离
            int m = sc.nextInt();
            // 上辆车到达地点的时间
            double time = 0;
            for (int i = 0; i < n; i++) {
                int speed = sc.nextInt();
                time = Math.max(time, i + (double) m / speed);
            }

            System.out.println(time - n -1);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
