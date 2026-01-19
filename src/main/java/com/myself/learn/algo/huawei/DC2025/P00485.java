package com.myself.learn.algo.huawei.DC2025;

import java.util.Scanner;

/**
 * P00485.华为od机试—结队编程
 * 双机位C卷
 * difficulty:3
 * @author luqi
 */
public class P00485 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            long res = 0;
            for (int i = 0; i < n; i++) {
                int num = sc.nextInt();
                nums[i] = num;
            }
            for (int i = 1; i < nums.length - 1 ; i++) {
                int leftBig = 0,leftSmall = 0;
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        leftSmall++;
                    }else if (nums[j] > nums[i]) {
                        leftBig++;
                    }
                }
                int rightBig = 0,rightSmall = 0;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] < nums[i]) {
                        rightSmall++;
                    }else if (nums[j] > nums[i]) {
                        rightBig++;
                    }
                }
                res += (long) leftBig * rightSmall + (long) leftSmall * rightBig;
            }
            System.out.println(res);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
