package com.myself.learn.algo.threadCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @projectName（项目名称）:qljt_cosmic
 * @package（包）:com.macro.mall
 * @className（类名称）:StringTest
 * @description（类描述）:一句话描述该类的功能
 * @author（创建人）:lujian
 * @createDate（创建时间）:2025-12-04 15:26
 * @updateUser（修改人）:lujian
 * @updateDate（修改时间）:2025-12-04 15:26
 * @updateRemark（修改备注）:说明本次修改内容
 * @version（版本）:v1.0
 * @form(表单名称):
 **/
public class StringTest {
    public static void main(String[] args) {
        StringTest test = new StringTest();
        //
        //System.out.println(test.trans("This is a sample",16));
        // 给你一个大小为 n 的字符串数组 strs ，其中包含n个字符串 , 编写一个函数来查找字符串数组中的最长公共前缀，返回这个公共前缀。
        //System.out.println(test.longestCommonPrefix(
        //        new String[]{"abc","abd"}
        //));
        // 验证字符串是否是ipv4或者ipv6
        System.out.println(test.solve("2001:db8:85a3:0::8a2E:0370:7334"));
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 验证IP地址
     * @param IP string字符串 一个IP地址字符串
     * @return string字符串
     */
    public String solve (String IP) {
        // write code here
        // write code here
        Boolean isIpv4 = false;
        Boolean isIpv6 = false;
        String[] ipv4s = IP.split("\\.");
        if (ipv4s.length == 4) {
            Boolean tmp = true;
            for (int i = 0; i < ipv4s.length; i++) {
                String ip = ipv4s[i];
                long l;
                try {
                    l = Long.parseLong(ip);
                }catch (NumberFormatException e){
                    tmp = false;
                    break;
                }
                if (0 >= l || l >= 255) {
                    tmp = false;
                    break;
                }
                if (ip.length() > 1 && ip.charAt(0) == '0') {
                    tmp = false;
                    break;
                }
            }
            isIpv4 = tmp;
        }

        String[] ipv6s = IP.split(":");
        long count = IP.chars().filter(e -> e == ':').count();
        if (count == 7 &&ipv6s.length == 8) {
            Boolean tmp = true;
            for (int i = 0; i < ipv6s.length; i++) {
                String ip = ipv6s[i];
                if (ip.length() > 4 || ip.length() < 1) {
                    tmp = false;
                    break;
                }
                Boolean isAll = true;
                for (int j = 0; j < ip.length(); j++) {
                    if (ip.charAt(j) != '0') {
                        isAll = false;
                        break;
                    }
                }
                if (isAll) {
                    if (ip.length() > 1) {
                        tmp = false;
                        break;
                    }
                }
                for (int j = 0; j < ip.length(); j++) {
                    if (( 'F' < ip.charAt(j) && ip.charAt(j) <= 'Z')||('f' < ip.charAt(j) && ip.charAt(j) <= 'z')) {
                        tmp = false;
                        break;
                    }
                }

            }
            isIpv6 = tmp;
        }
        return isIpv4 ? "IPv4" : isIpv6 ? "IPv6" : "Neither";
    }



    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param strs string字符串一维数组
     * @return string字符串
     */
    public String longestCommonPrefix (String[] strs) {
        // write code here
        if(strs.length == 0){
            return "";
        }

        int minIndex = 0;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minLength) {
                minLength = strs[i].length();
                minIndex = i;
            }
        }
        String prefix = strs[minIndex];
        Boolean end = false;
        while (!prefix.isEmpty() && !end) {
            int i ;
            for (i = 0; i < strs.length; i++) {
                if (!strs[i].substring(0, prefix.length()).equals(prefix)) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                    break;
                }
            }
            if (i == strs.length) {
                end = true;
            }
        }
        if(end){
            return prefix;
        }else{
            return "";
        }
    }

    public Boolean minPrefix(String[] strs, String prefix) {
        for (int i = 0; i < strs.length; i++) {
            if (!strs[i].substring(0, prefix.length()).equals(prefix)) {
                if(prefix.length() == 1){
                    return false;
                }
                prefix = strs[i].substring(0, prefix.length() - 1);
                i = strs.length;
                return minPrefix(strs, prefix);
            }
        }
        return true;
    }




    /**
     * 翻转单词
     * @param s
     * @param n
     * @return
     */
    public String trans (String s, int n) {
        StringBuilder res = new StringBuilder();
        Deque<Character> deque = new ArrayDeque<Character>();
        for (int i = 0; i < s.length(); i++) {
            deque.push(s.charAt(i));
        }
        Deque<Character> temp = new ArrayDeque<Character>();
        while (!deque.isEmpty()) {
            Character pop = deque.pop();
            if (pop != ' '){
                temp.push(reverse(pop));
            }else {
                 while (!temp.isEmpty()) {
                     res.append(temp.pop());
                 }
                 res.append(" ");
            }
        }
        while (!temp.isEmpty()) {
            res.append(temp.pop());
        }
        return res.toString();
    }

    /**
     * 翻转字符
     * @param c
     * @return
     */
    public char reverse(char c) {
        if (c >= 'a' && c <= 'z'){
            return (char) (c - 'a' + 'A');
        }else if (c >= 'A' && c <= 'Z'){
            return (char) (c - 'A' + 'a');
        }else {
            return c;
        }
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param nums int整型一维数组
     * @return int整型一维数组
     */
    public int[] FindNumsAppearOnce (int[] nums) {
        // write code here
        int[] result = new int[2];
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<nums.length;j++){
                if(i != j && nums[i] == nums[j]){
                    break;
                }
            }
            list.add(nums[i]);
        }
        result[0] = list.get(0) < list.get(1) ? list.get(0) : list.get(1);
        result[1] = list.get(0) > list.get(1) ? list.get(1) : list.get(0);
        return result;
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param nums int整型一维数组
     * @return int整型
     */
    public int minNumberDisappeared (int[] nums) {
        // write code here
        int res = 0;
        int[] temp = new int[5*10^5];
        for (int i = 0; i < nums.length ;i ++){
            if(nums[i] > 0){
                temp[nums[i]]++;
            }
        }
        for (int i = 0; i < temp.length ; i++) {
            if(temp[i] == 0){
                return i;
            }
        }
        return res;
    }
}