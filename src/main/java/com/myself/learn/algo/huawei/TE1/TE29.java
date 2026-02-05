package com.myself.learn.algo.huawei.TE1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * 29- 反转每对括号间的子串
 * difficulty:简单
 * frequency:高
 * @author luqi
 */
public class TE29 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            String line = sc.nextLine().trim();
            Deque<Character> stack = new ArrayDeque<Character>();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == '(') {
                    stack.push(c);
                }else if (c == ')') {
                    StringBuilder sb = new StringBuilder();
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    String res = sb.toString();
                    for (int j = 0; j < res.length(); j++) {
                        stack.push(res.charAt(j));
                    }
                }else {
                    stack.push(c);
                }
            }
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            System.out.println(sb.reverse().toString());
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
