package com.myself.learn.algo.huawei.C2025;

import java.util.Scanner;

/**
 * P00392.华为od机试—字符串中找出连续最长的数字串
 * 2025C卷
 * difficulty:4
 * @author luqi
 */
public class C392 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            int n = line.length();
            String maxStr = "";

            for (int i = 0; i < n; i++) {
                char c = line.charAt(i);

                // 1. 快速判断当前字符是否可能是数字串的开头：数字 或 正负号
                if (Character.isDigit(c) || c == '+' || c == '-') {
                    // 开始尝试向后延伸
                    StringBuilder temp = new StringBuilder();
                    temp.append(c);

                    // 标记状态
                    boolean hasDot = false;
                    // 如果开头是符号，必须保证后面还有字符且是数字
                    if ((c == '+' || c == '-') && (i + 1 >= n || !Character.isDigit(line.charAt(i + 1)))) {
                        continue; // 只有符号没有数字，跳过
                    }

                    int j = i + 1;
                    while (j < n) {
                        char nextC = line.charAt(j);

                        if (Character.isDigit(nextC)) {
                            temp.append(nextC);
                            j++;
                        } else if (nextC == '.') {
                            // 遇到点：不能已经有点，且点后面必须紧跟数字
                            if (!hasDot && j + 1 < n && Character.isDigit(line.charAt(j + 1))) {
                                hasDot = true;
                                temp.append(nextC); // 把点加进去
                                j++;
                            } else {
                                break; // 非法点，停止延伸
                            }
                        } else {
                            break; // 遇到其他字符，停止
                        }
                    }

                    // 更新最大值
                    if (temp.length() >= maxStr.length()) {
                        maxStr = temp.toString();
                    }

                    // 优化：i 可以跳到 j 的位置继续（但在嵌套循环里直接改i容易出错，
                    // 且题目允许重叠部分重新判定吗？题目没明说，通常不用跳，
                    // 让外层循环自然+1即可，为了保险起见，建议不手动跳i）
                }
            }
            System.out.println(maxStr);
        }
        sc.close();
    }
}
