package com.myself.learn.algo.huawei.DC2025;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * P00497.华为od机试—魔法积木消消乐
 * 双机位C卷
 * difficulty:4
 * @author luqi
 */
public class P00497 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            int cout = 0;
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                set.add(sc.nextInt());
            }
            while (set.size() > 1 || set.higher(0) != null ) {
                Integer i = set.pollLast();
                set.add(i / 2);
                cout++;
            }
            System.out.println(cout);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
