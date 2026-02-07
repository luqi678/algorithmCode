package com.myself.learn.algo.huawei.TE1;

import java.util.HashSet;
import java.util.Set;

/**
 * 6- 无重复字符的最长子串
 * difficulty:中等
 * frequency:中
 * @author luqi
 */
public class TE6 {

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        Set<Character> set = new HashSet<Character>();
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            Character c = s.charAt(right);
            while (set.contains(c)) {
                char leftChar = s.charAt(left);
                set.remove(leftChar);
                left++;
            }
            set.add(c);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int i = new TE6().lengthOfLongestSubstring("abcabcbb");
        System.out.println(i);
    }

}
