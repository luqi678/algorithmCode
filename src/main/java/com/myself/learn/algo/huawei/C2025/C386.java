package com.myself.learn.algo.huawei.C2025;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * P00386.华为od机试—N进制减法
 * 2025C卷
 * difficulty:4
 *
 * @author luqi
 */
public class C386 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            try {
                String line = sc.nextLine();
                // 分割输入，处理可能的多个空格
                String[] parts = line.trim().split("\\s+");

                if (parts.length != 3) {
                    System.out.println("-1");
                    return;
                }

                int radix = Integer.parseInt(parts[0]);
                String str1 = parts[1];
                String str2 = parts[2];

                // 1. 检查进制范围
                if (radix < 2 || radix > 35) {
                    System.out.println("-1");
                    return;
                }

                // 2. 检查前导0 (题目特别强调：012 这种是非法的，但 0 是合法的)
                if (!isValidFormat(str1) || !isValidFormat(str2)) {
                    System.out.println("-1");
                    return;
                }

                // 3. 使用 BigInteger 进行进制转换和计算
                // BigInteger(String val, int radix) 构造函数会自动处理进制转换
                // 如果字符超出进制范围（例如2进制里出现3），它会抛出 NumberFormatException
                BigInteger num1 = new BigInteger(str1, radix);
                BigInteger num2 = new BigInteger(str2, radix);

                BigInteger diff = num1.subtract(num2);

                // 4. 处理符号
                // signum(): -1表示负数, 0表示零, 1表示正数
                int signTag = diff.signum() == -1 ? 1 : 0;

                // 5. 转换回 N 进制字符串
                // toString(radix) 会自动转换回对应进制的字符串
                String resultStr = diff.abs().toString(radix);

                System.out.println(signTag + " " + resultStr);

            } catch (Exception e) {
                // 捕获所有解析异常（如非法字符、进制错误等）
                System.out.println("-1");
            }
        }
        sc.close();
    }

    // 独立校验格式：除了单独的 "0"，不能以 "0" 开头
    private static boolean isValidFormat(String s) {
        if (s == null || s.length() == 0) return false;
        if (s.length() > 1 && s.startsWith("0")) return false;
        return true;
    }
}