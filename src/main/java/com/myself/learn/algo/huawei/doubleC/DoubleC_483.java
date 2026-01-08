package com.myself.learn.algo.huawei.doubleC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * P00483. 华为od机试—零食奖励-取零食
 **/
public class DoubleC_483 {

    static class Item {
        // 为了避免歧义，这里由 price/quantity/favourability 改为 cost(总花费) 和 value(总喜爱度)
        // 因为拆分后，它就是一个独立的 "0/1背包" 物品
        public int cost;
        public int value;

        public Item(int cost, int value) {
            this.cost = cost;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextLine()) {
            return;
        }
        String inputX = sc.nextLine();
        // 增加判空逻辑，防止空行报错
        if (inputX.trim().isEmpty()) return;

        String[] split = inputX.trim().split("\\s+");
        int x = Integer.parseInt(split[0].trim());
        int n = Integer.parseInt(split[1].trim());

        int zeroDp = 0;

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!sc.hasNextLine()) {
                break;
            }
            String itemString = sc.nextLine();
            // 处理可能的空行
            if (itemString.trim().isEmpty()) {
                i--;
                continue;
            }

            int[] taps = Arrays.stream(itemString.split("\\s+"))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt).toArray();

            if (taps.length != 3) {
                continue;
            }

            int unitPrice = taps[0];
            int totalCount = taps[1];
            int unitFavor = taps[2];

            // 0元商品直接拿满
            if (unitPrice == 0) {
                zeroDp += totalCount * unitFavor;
                continue;
            }

            // --- 二进制拆分优化 修正部分 ---
            // 将多重背包拆分为 0/1 背包
            // 重点：新物品的花费 = k * 单价，新物品的价值 = k * 单体喜爱度
            int k = 1;
            while (totalCount >= k) { // 通常写 >= k, 虽然 > k 在某些逻辑下也能跑通，但 >= 更标准
                items.add(new Item(k * unitPrice, k * unitFavor));
                totalCount -= k;
                k *= 2;
            }
            // 处理余数
            if (totalCount > 0) {
                items.add(new Item(totalCount * unitPrice, totalCount * unitFavor));
            }
        }

        // 动态规划求解 0/1 背包
        int[] dp = new int[x + 1];

        for (Item item : items) {
            // 0/1 背包必须逆序遍历
            // item.cost 就是这个拆分包的总价格
            for (int j = x; j >= item.cost; j--) {
                dp[j] = Math.max(dp[j], dp[j - item.cost] + item.value);
            }
        }

        System.out.println(dp[x] + zeroDp);
    }
}