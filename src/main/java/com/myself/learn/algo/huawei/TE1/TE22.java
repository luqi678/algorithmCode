package com.myself.learn.algo.huawei.TE1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 22- 最长公共前缀
 * difficulty:简单
 * frequency:高
 * @author luqi
 */
public class TE22 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            StringBuffer sb = new StringBuffer();
            String[] strs = sc.nextLine().trim().replaceAll("\\[\\s+]", "").split(",");
            long l = Arrays.stream(strs).mapToLong(String::length).min().orElse(0);
            for (long i = 0; i < l; i++) {
                boolean flag = true;
                Character c = null;
                for (int j = 0; j < strs.length; j++) {
                    c = c == null ? strs[j].charAt((int) i) : c;
                    if (c != strs[j].charAt((int) i)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    sb.append(c);
                }
            }
            System.out.println(sb.toString());

        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
