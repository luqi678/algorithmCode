package com.myself.learn.algo.huawei.TE1;

/**
 * 125- 最长回文串
 * difficulty:简单
 * frequency:中
 * @author luqi
 */
public class TE125 {

    public static String longestPalindrome(String s) {
        int maxLen = 0;
        int maxStart = 0;
        for (int i = 0; i < s.length(); i++) {
            // 奇数回文
            int left = i - 1, right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > maxLen) {
                    maxStart = left;
                    maxLen = right - left + 1;
                }
                left -- ;
                right ++;
            }
            // 偶数回文
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > maxLen) {
                    maxStart = left;
                    maxLen = right - left + 1;
                }
                left -- ;
                right ++;
            }
        }
        return s.substring(maxStart, maxStart + maxLen);
    }


    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbbd"));
    }
}
