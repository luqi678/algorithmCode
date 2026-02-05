package com.myself.learn.algo.huawei.TE1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 40- 打家劫舍 II
 * difficulty:中等
 * frequency:高
 * @author luqi
 */
public class TE40 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int[] numbers = Arrays.stream(sc.nextLine().trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            if (numbers.length != 4) {
                System.out.println("查询不到");
                return;
            }
            // 尝试使用dfs方式
            dfs(numbers, 1, (double) numbers[0], String.valueOf(numbers[0]));
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    /**
     * dfs方式
     * @param numbers 数组
     * @param i 索引
     * @param count 当前计算值
     *              path 路径
     */
    private static void dfs(int[] numbers, int i, double count, String path) {
        // 跳出条件
        if (i == numbers.length) {
            if (Math.abs(count - 24) < 0.00001) {
                System.out.println(path);
            }
            return;
        }
        // 尝试所有情况
        int number = numbers[i];
        dfs(numbers, i + 1, count +  number, path + "+" + numbers[i]);
        dfs(numbers, i + 1, count -  number, path + "-" + numbers[i]);
        dfs(numbers, i + 1, count * number, path + "*" + numbers[i]);
        dfs(numbers, i + 1, count / number, path + "/" + numbers[i]);

    }
}
