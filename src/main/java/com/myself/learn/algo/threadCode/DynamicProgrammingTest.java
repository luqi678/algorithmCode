package com.myself.learn.algo.threadCode;

/**
 * @projectName（项目名称）:qljt_cosmic
 * @package（包）:com.macro.mall
 * @className（类名称）:DynamicProgrammingTest
 * @description（类描述）:动态规划
 * @author（创建人）:lujian
 * @createDate（创建时间）:2025-12-11 11:01
 * @updateUser（修改人）:lujian
 * @updateDate（修改时间）:2025-12-11 11:01
 * @updateRemark（修改备注）:说明本次修改内容
 * @version（版本）:v1.0
 * @form(表单名称):
 **/
public class DynamicProgrammingTest {

    public static void main(String[] args) {
        DynamicProgrammingTest test = new DynamicProgrammingTest();
        System.out.println(test.maxProfit(new int[]{1,2,3,0,2}
        ));
    }


    /**
     * 假设你有一个数组prices，长度为n，其中prices[i]是股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
     * 1.你可以买入一次股票和卖出一次股票，并非每天都可以买入或卖出一次，总共只能买入和卖出一次，且买入必须在卖出的前面的某一天
     * 2.如果不能获取到任何利润，请返回0
     * 3.假设买入卖出均无手续费
     *
     * 数据范围： 0≤n≤105,0≤val≤1040≤n≤10 5 ,0≤val≤10 4
     * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
     *
     * @param prices int整型一维数组
     * @return int整型
     */
    public int maxProfit (int[] prices) {
        // write code here
        int n = prices.length;
        int maxProfit = 0;
        int min= prices[0],max = prices[0];
        int minIndex= 0,maxIndex = 0;
        for(int i = 0; i < n; i++){
            if(prices[i] < min){
                min = prices[i];
                minIndex = i;
            }
            if(prices[i] > max ){
                max = prices[i];
                maxIndex = i;
            }
            if(maxIndex < minIndex){
                max = prices[i];
                maxIndex = i;
            }
            maxProfit = Math.max(maxProfit, max - min);
        }
        return maxProfit;
    }

    /**
     * 假设你有一个数组prices，长度为n，其中prices[i]是某只股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
     * 1. 你可以多次买卖该只股票，但是再次购买前必须卖出之前的股票
     * 2. 如果不能获取收益，请返回0
     * 3. 假设买入卖出均无手续费
     *
     * 数据范围：
     * 1≤n≤1×1051≤n≤1×10^5  ， 1≤prices[i]≤1041≤prices[i]≤10
     * 4 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
     *   进阶：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
     *
     * 计算最大收益
     * @param prices int整型一维数组 股票每一天的价格
     * @return int整型
     */
    public int maxProfit2 (int[] prices) {
        // write code here
        int maxProfit = 0;
        // 每天都买入卖出
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > prices[i-1]){
                maxProfit = maxProfit + prices[i] - prices[i-1];
            }
        }
        return maxProfit;
    }

    /**
     *
     * 计算最大收益
     * @param prices int整型一维数组 股票每一天的价格
     * @return int整型
     */
    public int maxProfit3 (int[] prices) {
        // write code here

        int s1 = -prices[0];
        int s2 = 0;
        int s3 = Integer.MIN_VALUE;
        int s4 = 0;
        for(int i = 1; i < prices.length; i++){
            s1 = Math.max(s1, -prices[i]);
            s2 = Math.max(s2, s1 + prices[i]);
            s3 = Math.max(s3, s2 - prices[i]);
            s4 = Math.max(s4, s3 + prices[i]);
        }

        return s4;
    }
    /**
     * 买卖股票的最佳时机 IV (188)
     * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 计算最大收益
     * @param prices int整型一维数组 股票每一天的价格
     * @return int整型
     */
    public int maxProfit4 (int k, int[] prices) {
        // write code here
        k = 2*k;
        int[] states = new int[k];
        // 初始化
        states[0] = -prices[0];
        for(int i = 1; i < k; i++){
            if (i%2 == 0) {
                states[i] = Integer.MIN_VALUE;
            }else {
                states[i] = 0;
            }
        }
        for(int i = 1; i < prices.length; i++){
            // 状态机状态变化
            states[0] = Math.max(states[0], -prices[i]);
            for (int j = 1; j < k; j++) {
                if (j%2 == 0) {
                    states[j] = Math.max(states[j], states[j-1]-prices[i]);
                }else {
                    states[j] = Math.max(states[j], states[j-1]+prices[i]);
                }
            }
        }
        return states[k-1];
    }

    /**
     * 309. 买卖股票的最佳时机含冷冻期
     * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 计算最大收益
     * @param prices int整型一维数组 股票每一天的价格
     * @return int整型
     */
    public int maxProfit5(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        // buy: 当前持有股票时的最大净利润（最小负数，即最低成本）
        // sell: 今天卖出股票后的最大利润
        // cool: 今天处于休息/冷冻期状态的最大利润

        // 初始化：
        // 第 0 天，只能买入。
        int buy = -prices[0];
        int sell = 0; // 第 0 天不能卖出
        int cool = 0; // 第 0 天处于休息状态，利润为 0

        for (int price : prices) {

            // 1. 计算今天的状态 (必须使用昨天的状态值！)
            //    (注意：如果使用 in-place 更新，需要临时变量保存昨天的状态)
            int old_buy = buy;
            int old_sell = sell;
            int old_cool = cool;

            // 2. 更新 buy 状态（今天结束时持有股票的最大利润）
            //    A. 保持持有 (昨天就买了: old_buy)
            //    B. 今天买入 (昨天处于休息状态: old_cool - price)
            // ps. 如果没有冷冻期 buy = Math.max(old_buy, Math.max(old_cool - price, sell - price));
            buy = Math.max(old_buy, old_cool - price);

            // 3. 更新 sell 状态（今天必须卖出）
            //    A. 只能由昨天的 buy 状态转移而来：old_buy + price
            sell = old_buy + price;

            // 4. 更新 cool 状态（今天休息/冷冻期）
            //    A. 昨天是卖出状态，今天必须进入冷冻期: old_sell
            //    B. 昨天是休息状态，今天继续休息: old_cool
            cool = Math.max(old_sell, old_cool);
        }

        // 最终最大利润是 sell (今天卖出) 或 cool (今天休息/冷冻) 状态中的最大值
        // 因为 buy 是负数，不可能最大；且 cool 状态继承自 sell，所以只比较 sell 和 cool 即可。
        // 在最后一天，sell 和 cool 都是最终的最大利润。
        return Math.max(sell, cool);
    }

    /**
     * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     *
     * 计算最大收益
     * @param prices int整型一维数组 股票每一天的价格
     * @return int整型
     */
    public int maxProfit6(int[] prices, int fee) {

        int buy = -prices[0];
        int sell = 0;
        for (int price : prices) {
            int old_buy = buy;
            int old_sell = sell;

            buy = Math.max(old_buy, old_sell - price);
            sell = Math.max(old_sell, buy + price -fee);
        }

        return sell;

    }

}