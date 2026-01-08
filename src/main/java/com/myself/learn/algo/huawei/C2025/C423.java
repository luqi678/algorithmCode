package com.myself.learn.algo.huawei.C2025;

import java.util.Scanner;
import java.util.Stack;

/**
 * P00423.华为od机试—简单的解压缩算法
 * 修正点：
 * 1. 支持多位数字 (如 A12)。
 * 2. 增加栈操作的安全性检查。
 * 3. 优化逻辑结构。
 */
public class C423 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        if (sc.hasNext()) {
            String str = sc.nextLine().trim();
            // 使用 String 栈更方便处理 "整体" 重复的情况
            Stack<String> stack = new Stack<>();

            // 使用 i 索引遍历，方便处理多位数字
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (Character.isDigit(c)) {
                    // 1. 解析完整的数字 (处理 A12 这种情况)
                    StringBuilder numBuilder = new StringBuilder();
                    while (i < str.length() && Character.isDigit(str.charAt(i))) {
                        numBuilder.append(str.charAt(i));
                        i++;
                    }
                    i--; // 回退一步，因为 for 循环会 i++
                    int count = Integer.parseInt(numBuilder.toString());

                    // 2. 取出要重复的内容
                    if (stack.isEmpty()) continue; // 防御性编程

                    String top = stack.pop();
                    StringBuilder contentToRepeat = new StringBuilder();

                    if ("}".equals(top)) {
                        // 如果栈顶是 }，说明要重复的是 { ... } 里面的内容
                        while (!stack.isEmpty() && !"{".equals(stack.peek())) {
                            // 注意：栈是后进先出，所以拼接时要反过来，或者 insert(0, ...)
                            // 这里使用 insert(0) 保持顺序，或者 pop 出来后 reverse
                            contentToRepeat.insert(0, stack.pop());
                        }
                        // 弹出左括号 {
                        if (!stack.isEmpty()) {
                            stack.pop();
                        }
                    } else {
                        // 如果栈顶是普通字符/字符串 (处理 A3 这种情况)
                        contentToRepeat.append(top);
                    }

                    // 3. 执行重复并压回栈
                    String repeated = contentToRepeat.toString();
                    StringBuilder sb = new StringBuilder();
                    for (int k = 0; k < count; k++) {
                        sb.append(repeated);
                    }
                    stack.push(sb.toString());

                } else {
                    // 非数字，直接压栈 (转换为字符串)
                    stack.push(String.valueOf(c));
                }
            }

            // 4. 拼接最终结果
            StringBuilder res = new StringBuilder();
            for (String s : stack) {
                res.append(s);
            }
            System.out.println(res.toString());
        }
        sc.close();
    }
}