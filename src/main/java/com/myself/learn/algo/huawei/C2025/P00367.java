package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.Scanner;

/**
 * P00367.华为od机试—翻牌求最大分
 * 2025C卷
 * difficulty:4
 * @author luqi
 */
public class P00367 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int[] numbs = Arrays.stream(sc.nextLine().trim().split("\\s+")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            int n = numbs.length;
            long[] sums = new long[n];
            sums[0] = Math.max(numbs[0], 0);
            for (int i = 1; i < numbs.length; i++) {
                sums[i] = sums[i-1] + numbs[i];
                if(i < 3){
                    sums[i] = Math.max(sums[i], 0); // Fixed: Added missing semicolon
                }else{
                    if (sums[i] < sums[i-3]) {
                        sums[i] = sums[i-3];
                    }
                }
            }

            System.out.println(sums[n-1]);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
