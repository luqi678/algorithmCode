package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * P00379.华为od机试—记票统计
 * 2025C卷
 * difficulty:5
 * @author luqi
 */
public class C379 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            sc.nextLine();
            String line = sc.nextLine();
            String[] names = Arrays.stream(line.trim().split("\\s+")).map(String::trim).map(String::valueOf).toArray(String[]::new);
            int m = sc.nextInt();
            sc.nextLine();
            String[] votes = Arrays.stream(sc.nextLine().trim().split("\\s+")).map(String::trim).map(String::valueOf).toArray(String[]::new);
            Map<String, Integer> map = new HashMap<>();
            int errCount = 0;
            for (int i = 0; i < n; i++) {
                map.putIfAbsent(names[i], 0);
            }
            for (int i = 0; i < m; i++) {
                if (map.containsKey(votes[i])) {
                    map.put(votes[i], map.get(votes[i]) + 1);
                }else {
                    errCount++;
                }
            }


            System.out.println(map.toString());
            System.out.println("Invalid : " + errCount);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
