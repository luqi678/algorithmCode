package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * P00429.华为od机试—寻找相似单词
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class C429 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            sc.nextLine();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String string = sc.nextLine().trim();
                list.add(string);
            }
            String like = sc.nextLine().trim();
            list.removeIf(string -> string.length() != like.length());
            Collections.sort(list);

            Map<Character, Integer> map = new HashMap<>();
            for (char c : like.toCharArray()) {
                Integer i = map.computeIfAbsent(c, k -> 0);
                map.put(c, i + 1);
            }
            List<String> res = new ArrayList<>();
            list.forEach(item -> {
                boolean flag = true;
                Map<Character, Integer> clone = new HashMap<>(map);
                for (char c : item.toCharArray()) {
                    Integer i = clone.computeIfAbsent(c, k -> 0);
                    clone.put(c, i - 1);
                }
                // 判断全为 0
                for (Map.Entry<Character, Integer> entry : clone.entrySet()) {
                    Character k = entry.getKey();
                    Integer v = entry.getValue();
                    if (v != 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    res.add(item);
                 }
            });
            printArray(res);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }


    public static void printArray(List<String> list) {
        if (list.isEmpty()) {
            System.out.println("null");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}
