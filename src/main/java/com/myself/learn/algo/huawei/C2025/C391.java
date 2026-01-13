package com.myself.learn.algo.huawei.C2025;

import java.util.Scanner;

/**
 * P00391.华为od机试—求满足条件的最长子串的长度
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class C391 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            String line = sc.nextLine();
            int left =0;
            int letterNum = 0;
            int maxLen = 0;
            boolean hasDigit = false,hasLetter = false;
            for (int right = 0; right < line.length(); right++) {
                char c = line.charAt(right);
                if (Character.isDigit(c)) {
                    hasDigit = true;
                }else if (Character.isLetter(c)) {
                    hasLetter = true;
                    while (letterNum != 0 && left < right) {
                        char leftC = line.charAt(left);
                        if (Character.isLetter(leftC)) {
                            letterNum--;
                        }
                        left++;
                    }
                    letterNum++;
                }
                maxLen = Math.max(maxLen, right - left + 1);
            }
            System.out.println(hasDigit && hasLetter ? maxLen : -1);
        }

        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
