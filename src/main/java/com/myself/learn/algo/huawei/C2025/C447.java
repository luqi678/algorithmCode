package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 华为OD机试万能模板
 * 包含：Scanner初始化、常用输入模式、常用工具函数
 */
public class C447 {

    public static class Item implements Comparable<Item>{
        int number;
        char aChar;
        public Item(int number, char aChar) {
            this.number = number;
            this.aChar = aChar;
        }
        public void addNumber() {
            this.number ++ ;
        }
        // 排序重写 按number从大到小，相同时按ASCII码从大到小排序
        @Override
        public int compareTo(Item o) {
            if (this.number != o.number) {
                return o.number - this.number;
            } else {
                return o.aChar - this.aChar;
            }
        }

    }


    // 全局静态 Scanner，方便在任何函数中使用
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        if (sc.hasNextLine()) {
            String line = sc.nextLine();
            // 使用下方封装的工具方法快速转换
            String[] split = line.split("\\s+");
            int n = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);
            String itemString = split[2];
            char[] charArray = itemString.trim().toCharArray();
            StringBuilder res = new StringBuilder();
            Map<Character, Item> map = new HashMap<>();
            int t = charArray.length % m == 0 ? charArray.length / m : charArray.length / m + 1;
            for (int i = 0; i < t; i++) {
                int start = i * m;
                int end = Math.min((i + 1) * m, charArray.length);
                for (int j = start; j < end; j++) {
                    map.computeIfAbsent(charArray[j], key -> new Item(0, key)).addNumber();
                }
                // map 转 优先级队列
                List<Item> list = new ArrayList<>(map.values());
                PriorityQueue<Item> pq = new PriorityQueue<>(list);
                int count = 3;
                while (!pq.isEmpty() && count > 0) {
                    Item item = pq.poll();
                    res.append(item.aChar);
                    count--;
                }
            }
            System.out.println(res);
        }
    }
}