package com.myself.learn.algo.huawei.DC2025;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * P00494.华为od机试—字符串计数匹配
 * 2025B卷,双机位C卷
 * difficulty:3
 * @author luqi
 */
public class P00494 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            String str = sc.nextLine().trim();
            int k = sc.nextInt();

            int count = 0;
            int left = 0;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(-1, 0);
            for (int i = 0; i < 10; i++) {
                map.put(i, 0);
            }
            // 滑动窗口长度
            int length = 10 + k;
            if (str.length() < length) {
                System.out.println("0");
                return;
            }

            for (int right = 0; right < str.length(); right++) {
                char currentChar = str.charAt(right);
                if (Character.isDigit(currentChar)) {
                    Integer currentInteger = currentChar - '0';
                    map.put(currentInteger, map.get(currentInteger) + 1);
                } else if (Character.isLetter(currentChar)) {
                    map.put(-1, map.get(-1) + 1);
                }
                if (right < length) {
                    if (right == length -1) {
                        boolean ok = checkMap(map, k);
                        if (ok) {
                            count++;
                        }
                    }
                }else {
                    char leftChar = str.charAt(left);
                    if (Character.isDigit(leftChar)) {
                        Integer leftInteger = leftChar - '0';
                        map.put(leftInteger, map.get(leftInteger) - 1);
                    } else if (Character.isLetter(leftChar)) {
                        map.put(-1, map.get(-1) - 1);
                    }
                    left ++;
                    boolean ok = checkMap(map, k);
                    if (ok) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static boolean checkMap(Map<Integer, Integer> map, int k) {

        boolean ok = true;
        if (map.get(-1) != k) {
            ok = false;
        }
        for (int i = 0; i < 10; i++) {
            if (map.get(i) != 1) {
                ok = false;
                break;
            }
        }
        return ok;

    }

}
