package com.myself.learn.algo.huawei.TE1;

import java.util.ArrayList;
import java.util.List;

/**
 * 14- 字母异位词分组
 * difficulty:中等
 * frequency:中
 * @author luqi
 */
public class TE14 {

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        boolean[] used = new boolean[strs.length];
        int[] count = new int[strs.length];
        // 将字符串转为数字
        for (int i = 0; i < strs.length; i++) {
            String word = strs[i];
            int counter = 0;
            for (int j = 0; j < word.length(); j++) {
                char ch = word.charAt(j);
                counter += ch - 'a' + 257;
            }
            count[i] = counter;
        }
        for (int i = 0; i < strs.length; i++) {
            if (used[i]) {
                continue;
            }
            List<String> list = new ArrayList<>();
            list.add(strs[i]);
            used[i] = true;
            for (int j = 0; j < strs.length; j++) {
                if (count[i] == count[j] && !used[j]) {
                    list.add(strs[j]);
                    used[j] = true;
                }
            }
            ans.add(list);
        }
        return ans;
    }



    public static void main(String[] args) {

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }
}
