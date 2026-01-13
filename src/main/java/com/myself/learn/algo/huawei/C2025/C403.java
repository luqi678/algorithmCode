package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.Scanner;

/**
 * P00403.华为od机试—停车场车辆统计 / 最少停车数
 * 2025C卷
 * difficulty:4
 * @author luqi
 */
public class C403 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNextLine()) {
            String line = sc.nextLine();
            int[] cars = Arrays.stream(line.trim().split(",")).mapToInt(Integer::parseInt).toArray();
            int minNum = 0;
            // 贪心
            for (int i = 0; i < cars.length; i++) {
                if (cars[i] == 1) {
                    int j = 2;
                    while (i + 1 < cars.length && cars[i + 1] == 1 && j > 0){
                        i++;
                        j--;
                    }
                    minNum++;
                }
            }
            System.out.println(minNum);


        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
