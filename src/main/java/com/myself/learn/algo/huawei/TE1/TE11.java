package com.myself.learn.algo.huawei.TE1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * 11- 移除K位数字
 * difficulty:中等
 * frequency:高
 * @author luqi
 */
public class TE11 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int k = sc.nextInt();
            sc.nextLine();
            String line = sc.nextLine().trim();
            // 用单调栈来实现取值
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                int number = c - '0';
                while (!stack.isEmpty() && number < stack.peek() && k > 0) {
                    stack.pop();
                    k--;
                }
                if (number != 0) {
                    stack.push(number);
                }
            }
            // 处理多余的k
            while (k > 0 && !stack.isEmpty()) {
                stack.pop();
                k--;
            }
            // 如果栈为空，输出0
            if (k>0) {
                System.out.println("0");
            }else {
                StringBuilder res = new StringBuilder();
                while (!stack.isEmpty()) {
                    res.append(stack.pop());
                }
                System.out.println(res.reverse().toString());
            }
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
