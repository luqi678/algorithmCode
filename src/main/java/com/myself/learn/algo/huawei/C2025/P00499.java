package com.myself.learn.algo.huawei.C2025;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * P00499.华为od机试—字符串匹配
 * 双机位C卷
 * difficulty:3
 * @author luqi
 */
public class P00499 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {

            String[] split = sc.nextLine().trim().split("\\s+");
            String str1 = split[0].trim();
            String str2 = split[1].trim();

            Map<Character, Integer> charCount = new HashMap<>();
            Map<Character, Integer> str2CharCount = new HashMap<>();
            for (char c : str1.toCharArray()) {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }
            for (char c : str2.toCharArray()) {
                str2CharCount.put(c, str2CharCount.getOrDefault(c, 0) + 1);
            }

            int right = str2.length() - 1;
            for (int left = 0; left < str2.length(); left++) {
                Integer oneNumber = charCount.getOrDefault(str2.charAt(left), 0);
                Integer twoNumber = str2CharCount.getOrDefault(str2.charAt(left), 0);
                if (twoNumber > oneNumber) {
                    left ++;
                    str2CharCount.put(str2.charAt(left), twoNumber - 1);
                }else {
                    oneNumber = charCount.getOrDefault(str2.charAt(right), 0);
                    twoNumber = str2CharCount.getOrDefault(str2.charAt(right), 0);
                    while (twoNumber > oneNumber){
                        right ++;
                        str2CharCount.put(str2.charAt(right), twoNumber - 1);
                        oneNumber = charCount.getOrDefault(str2.charAt(right), 0);
                        twoNumber = str2CharCount.getOrDefault(str2.charAt(right), 0);
                    }
                    System.out.println(right - left + 1 == str1.length() ? left + " " + right : -1);
                    break;
                }
            }

        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
