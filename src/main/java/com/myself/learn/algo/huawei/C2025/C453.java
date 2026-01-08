package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 图论模板
 *
 * @author 2405051*/
public class C453 {

    public static class Item{
        public int begin;
        public int end;
        public long price;
        public Item(int begin, int end, long price){
            this.begin = begin;
            this.end = end;
            this.price = price;
        }
    }

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        List<Item> items = new ArrayList<Item>();
        // 输入图
        if (sc.hasNextInt()) {
            // 图论前有几个入参
            int t = sc.nextInt();
            int m = sc.nextInt();

            // 图
            for (int i = 0; i < m; i++) {
                int begin = sc.nextInt();
                int end = sc.nextInt();
                long price = sc.nextLong();
                Item item = new Item(begin, end, price);
                items.add(item);
            }
            // 逻辑
            long[] minPrice = new long[t + 1];
            for (int i = 0; i < minPrice.length; i++) {
                minPrice[i] = Long.MAX_VALUE;
            }
            for (Item item : items) {
                for (int i = item.begin; i <= item.end; i++) {
                    minPrice[i] = Math.min(minPrice[i], item.price);
                }
            }
            long sum = 0;
            for (int i = 1; i < minPrice.length; i++) {
                sum += minPrice[i];
            }
            System.out.println(sum);
        }
    }

}