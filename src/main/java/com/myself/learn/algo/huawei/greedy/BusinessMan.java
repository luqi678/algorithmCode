package com.myself.learn.algo.huawei.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最大利润贪心的商人
 * {@link com.myself.learn.algo.huawei.test_questions}
 * @author 2405051
 * */
public class BusinessMan {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String inputNumber = sc.nextLine();
        int number = 0;
        if (!sc.hasNextLine()) {
            return;
        }
        int days = 0;
        String inputDays = sc.nextLine();
        try {
            // 商品数量
            number = Integer.parseInt(inputNumber);
            // 天数
            days = Integer.parseInt(inputDays);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        if (!sc.hasNextLine()) {
            return;
        }
        String inputItem = sc.nextLine();
        String[] itemStrings = inputItem.split("\\s+");
        if (itemStrings.length != number) {
            System.out.println("最大商品持有数量有误");
            return;
        }
        Integer[] items = Arrays.stream(itemStrings).map(Integer::valueOf).toArray(Integer[]::new);
        // 商品价钱变化组
        Integer[][] price = new Integer[number][days];
        for (int i = 0; i < number; i++) {
            if (!sc.hasNextLine()) {
                return;
            }
            String inputPriceDay = sc.nextLine();
            String[] priceDayStrings = inputPriceDay.split("\\s");
            price[i] = Arrays.stream(priceDayStrings).map(Integer::valueOf).toArray(Integer[]::new);
        }
        // 算法部分 用动态规划(x) 贪心即可（✔)
        // 总利润
        int totalSell = 0;
        //  计算每一个商品的最大利润
        for (int j = 0; j < number; j++) {
            // 买入利润初始化
            // int b = -price[j][0];
            // 卖出利润初始化
            int s = 0;
            // 天数作为阶段
            for (int i = 1; i < days; i++) {
                s = price[j][i] > price[j][i-1] ? s - price[j][i-1] + price[j][i] : s;
                // b = price[j][i] > price[j][i-1] ? - price[j][i] : b;
                // b = Math.max(b, -price[j][i]);
                // s = Math.max(s, b + price[j][i]);
            }
            totalSell = totalSell + s*items[j];
        }
        System.out.println(totalSell);
    }
}