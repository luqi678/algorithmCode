package com.myself.learn.algo.huawei.TE1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

/**
 * 65- 接雨水
 * difficulty:困难
 * frequency:低
 * @author luqi
 */
public class TE65 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            // 输入数组
            int[] numbers = Arrays.stream(sc.nextLine().trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            // 接到的雨水容量
            int count = 0;
            // 定义栈
            Deque<Integer> stack = new ArrayDeque<>();
            int[] leftHeight = new int[numbers.length];
            int[] rightHeight = new int[numbers.length];
            // 遍历当前点 填入当前节点比左边节点最矮多少
            for (int i = 0; i < numbers.length; i++) {
                int current = numbers[i];
                if (stack.isEmpty() || current >= stack.peek()) {
                    stack.push(current);
                    leftHeight[i] = 0;
                }else {
                    leftHeight[i] = stack.peek() - current;
                }
            }
            // 遍历当前点 填入当前节点比右边节点最矮多少
            stack.clear();
            for (int i = numbers.length - 1; i >= 0; i--) {
                int current = numbers[i];
                if (stack.isEmpty() || current >= stack.peek()) {
                    stack.push(current);
                    rightHeight[i] = 0;
                }else {
                    rightHeight[i] = stack.peek() - current;
                }
            }
            for (int i = 0; i < numbers.length; i++) {
                count += Math.min(leftHeight[i], rightHeight[i]);
            }
            System.out.println(count);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
