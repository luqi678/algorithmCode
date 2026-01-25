package com.myself.learn.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 描述：
 * This is a one_2.
 *
 * @author lujian
 * @since 2026/1/21.
 */
public class one_2 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static String res = "";

    public static void main(String[] args) {


        if (sc.hasNext()) {
            String line = sc.nextLine().trim();
            // 定义字符数量map，key为字符，value为node对象
            TreeMap<Character, Integer> charNodeMap = new TreeMap<>();
            for (int i = line.length() - 1; i >= 0; i--) {
                // 当前字符
                char currentChar = line.charAt(i);
                // 带入顺序，为之后相同数量排序做准备
                Integer currentNode = charNodeMap.getOrDefault(currentChar, 0);
                currentNode++;
                charNodeMap.put(currentChar, currentNode);
            }
            // 重写treemap排序
            List<Map.Entry<Character, Integer>> nodeList = new ArrayList<>(charNodeMap.entrySet());
            Collections.sort(nodeList, (o1, o2) -> {
                if (o1.getValue().equals(o2.getValue())) {
                    return o1.getKey() - o2.getKey();
                }
                return o2.getValue() - o1.getValue();
            });
            // 打印排序后的值
            for (Map.Entry<Character, Integer> entry : nodeList) {
                System.out.print(entry.getKey());
            }
        }

        // 关闭输入
        sc.close();
    }

}
