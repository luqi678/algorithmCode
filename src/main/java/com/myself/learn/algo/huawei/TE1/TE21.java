package com.myself.learn.algo.huawei.TE1;

import java.util.Scanner;

/**
 * 21- 验证回文串
 * difficulty:简单
 * frequency:高
 * @author luqi
 */
public class TE21 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            String line = sc.nextLine().trim();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (Character.isLetterOrDigit(c) || Character.isDigit(c)) {
                    sb.append(Character.toLowerCase(c));
                }
            }
            System.out.println(sb.toString());
            int left = 0, right = sb.length() - 1;
            while (left < right) {
                if (sb.charAt(left) != sb.charAt(right)) {
                    System.out.println("false");
                    return;
                }
                left++;
                right--;
            }
            System.out.println("true");
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
