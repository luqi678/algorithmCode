package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.Scanner;

/**
 * P00411.华为od机试—新学校选址
 * 2025C卷
 * difficulty:2
 * @author luqi
 */
public class C411 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {


        if (sc.hasNext()) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                int num = sc.nextInt();
                nums[i] = num;
            }
            int res;
            // 中位数
            Arrays.sort(nums);
            if (n % 2 == 0) {
                res = nums[n / 2 - 1];
            }else {
                res = nums[n / 2];
            }
            System.out.println(res);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
