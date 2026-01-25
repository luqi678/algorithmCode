package com.myself.learn.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 描述：
 * This is a one.
 *
 * @author lujian
 * @since 2026/1/21.
 */
public class one {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);

    static class node implements Comparable<node> {
        char val;
        int number;
        int index;

        @Override
        public int compareTo(node other) {
            return this.number != other.number ? other.number - this.number : this.index - other.index;
        }


        public node(char val, int number,int index) {
            this.val = val;
            this.number = number;
            this.index = index;
        }
    }

    public static void main(String[] args) {

        if (sc.hasNext()) {
            String line = sc.nextLine().trim();
            // 定义字符数量map，key为字符，value为node对象
            Map<Character, node> charNodeMap = new HashMap<>();
            for (int i = line.length() - 1; i >= 0; i--) {
                // 当前字符
                char currentChar = line.charAt(i);
                if (!Character.isLetter(currentChar) && !Character.isDigit(currentChar)) {
                    continue;
                }
                // 带入顺序，为之后相同数量排序做准备
                node currentNode = charNodeMap.getOrDefault(currentChar, new node(currentChar, 0, i));
                currentNode.number ++;
                charNodeMap.put(currentChar, currentNode);
            }
            List<node> nodeList = new ArrayList<>(charNodeMap.values());
            Collections.sort(nodeList);
            System.out.println(nodeList.stream().map(node -> node.val).map(String::valueOf).collect(Collectors.joining()));
        }

        // 关闭输入
        sc.close();
    }

}
