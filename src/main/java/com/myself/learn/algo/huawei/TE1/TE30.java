package com.myself.learn.algo.huawei.TE1;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * 30- 座位预约管理系统设计
 * difficulty:简单
 * frequency:中
 * @author luqi
 */
public class TE30 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            sc.nextLine();
            // 表示i座位是否已经被预约
            boolean[] seats = new boolean[n+1];
            //TreeSet 存储可用编码
            TreeSet<Integer> set = new TreeSet<Integer>();
            // 初始化
            for (int i = 1; i < n + 1; i++) {
                set.add(i);
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.contains("unreserve")) {
                    int i = Integer.parseInt(line.replace("unreserve", "").trim());
                    seats[i] = false;
                    set.add(i);
                }else if(line.contains("reserve")) {
                    int i = Integer.parseInt(line.replace("reserve", "").trim());
                    seats[i] = true;
                    set.remove(i);
                }else if(line.contains("findFirst")) {
                    System.out.println(set.isEmpty() ? "-1" : set.first());
                }else if(line.contains("findLast")) {
                    // 查看优先级队列最后一个元素
                    System.out.println(set.isEmpty() ? "-1" : set.last());
                }
            }
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
