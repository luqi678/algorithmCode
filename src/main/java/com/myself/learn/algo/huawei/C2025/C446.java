package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 华为OD机试万能模板
 * 包含：Scanner初始化、常用输入模式、常用工具函数
 */
public class C446 {

    public static class Item{
        public char aChar;
        public int number;
        public Item(char aChar, int number) {
            this.aChar = aChar;
            this.number = number;
        }

        public void addNumber(){
            this.number ++;
        }
    }

    // 全局静态 Scanner，方便在任何函数中使用
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        if (sc.hasNextLine()) {
            String line = sc.nextLine();
            StringBuilder res = new StringBuilder();

            Map<Character,Item> map = new HashMap<Character,Item>();
            for (char c : line.trim().toCharArray()) {
                map.computeIfAbsent(c, k -> new Item(k, 0)).addNumber();
            }
            Item[] array = map.values().toArray(new Item[0]);
            Arrays.sort(array, Comparator.comparingInt(o -> o.aChar));
            Character midChar = null;
            for (Item item : array) {
                if (item.number % 2 == 0) {
                    while (item.number > 0) {
                        res.append(item.aChar);
                        item.number -= 2;
                    }
                }else {
                    // midChar 判空
                    if (midChar == null) {
                        midChar = item.aChar;
                        item.number--;
                    }
                    while (item.number >= 2) {
                        res.append(item.aChar);
                        item.number -= 2;
                    }
                }
            }
            String left = res.toString();
            String right = res.reverse().toString();
            System.out.println(left + (midChar == null ? "" : midChar) + right);

        }

        sc.close();
    }

}