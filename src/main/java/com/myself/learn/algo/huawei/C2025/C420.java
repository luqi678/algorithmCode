package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * P00420.华为od机试—统计友好度最大值 / 新员工座位
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class C420 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        int leftNum = 0,rightNum = 0;
        int max = 0;
//        if (sc.hasNextLine()) {
//            String input = sc.nextLine();
//            Arrays.stream(input.trim().split("\\s+")).mapToInt(Integer::parseInt).forEach(list::add);
//        }
        while (sc.hasNextInt()) {
            int num = sc.nextInt();
            list.add(num);
        }
        for (int i = 0; i < list.size(); i++) {
            int n = list.get(i);
            switch (n) {
                case 0:
                    while (!stack.isEmpty()&& stack.peek() == 1) {
                        stack.pop();
                        leftNum++;
                    }
                    stack.push(n);
                    while (i+1 < list.size() && list.get(i + 1) == 1) {
                        stack.push(list.get(i + 1));
                        i ++;
                        rightNum++;
                    }
                    max = Math.max(max, leftNum + rightNum);
                    leftNum = 0;
                    rightNum = 0;
                    break;
                case 1:
                    stack.push(n);
                    break;
                case 2:
                    stack.push(n);
                    break;
                default:
                    break;
            }
        }

        System.out.println(max);
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
