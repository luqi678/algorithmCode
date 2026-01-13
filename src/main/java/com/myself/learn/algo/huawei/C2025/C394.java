package com.myself.learn.algo.huawei.C2025;

import java.util.Scanner;

/**
 * P00394.华为od机试—用连续自然数之和来表达整数
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class C394 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        if (sc.hasNext()) {
            int number = sc.nextInt();
            // 成功次数
            int success = 0;
            StringBuilder sb = new StringBuilder();
            for (int n = 1; n * (n + 1) / 2 <= number; n++) {
                // 由连续自然数和公式得
                if ((2 * number - n * (n - 1)) % (2 * n) == 0) {
                    int first = (2 * number - n * (n - 1)) / (2 * n);
                    if (first <= 0) {
                        continue;
                    }
                    sb.append(number).append("=");
                    boolean flag = false;
                    for (int k = 0; k < n; k++) {
                        if (flag){
                            sb.append("+");
                        }
                        flag = true;
                        sb.append(first + k);
                    }
                    sb.append(System.lineSeparator());
                    success++;
                }

            }
            sb.append("Result:").append(success);
            System.out.println(sb.toString());
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
