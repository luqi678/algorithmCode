package com.myself.learn.algo.huawei.TE1;

import java.util.Scanner;

/**
 * 12- 二进制求和
 * difficulty:简单
 * frequency:中
 * @author luqi
 */
public class TE12 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            String a = sc.nextLine().trim();
            String b = sc.nextLine().trim();
            if ("0".equals(a) || "0".equals(b)) {
                System.out.println("0".equals(a) ? b : a);
            }else {
                StringBuilder sb = new StringBuilder();
                a = new StringBuilder(a).reverse().toString();
                b = new StringBuilder(b).reverse().toString();
                int len = Math.max(a.length(), b.length());
                // 进位
                int add = 0;
                for (int i = 0; i < len + 1; i++) {
                    int numA = i < a.length() ? a.charAt(i) - '0' : 0;
                    int numB = i < b.length() ? b.charAt(i) - '0' : 0;
                    // 当前位
                    int current = numA + numB + add;
                    add = current / 2;
                    sb.append(current % 2);
                }
                System.out.println(String.valueOf(sb.reverse()));
            }



        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
