package com.myself.learn.algo.huawei.TE1;

import java.util.Scanner;

/**
 * 70- 最小覆盖子串
 * difficulty:困难
 * frequency:低
 * @author luqi
 */
public class TE70 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            String s = sc.nextLine().trim();
            String t = sc.nextLine().trim();
            // 最短子串
            String ans = s;
            if (t.length() > s.length()) {
                System.out.println("");
            }else {
                int[] need = new int[52];
                for (int i = 0; i < t.length(); i++) {
                    char c = t.charAt(i);
                    if (Character.isLetter(c)) {
                        need[c- 'A']++;
                    }
                }
                // 满足字符个数
                int valid = 0;
                // 滑动窗口
                int[] window = new int[52];
                int left = 0;
                for (int right = 0; right < s.length(); right++) {
                    char c = s.charAt(right);
                    if (Character.isLetter(c)) {
                        window[c - 'A']++;
                        if ( window[c - 'A'] == need[c - 'A'] && need[c - 'A'] > 0) {
                            valid++;
                        }
                    }
                    while (left <= right && valid == t.length()) {
                        ans = ans.length() > right - left + 1 ? s.substring(left, right + 1) : ans;

                        char lc = s.charAt(left);
                        if (Character.isLetter(lc)) {
                            window[lc - 'A']--;
                            if (window[lc - 'A'] < need[lc - 'A']) {
                                valid--;
                            }
                        }
                        left++;
                    }

                }

                System.out.println(ans);
            }

        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
