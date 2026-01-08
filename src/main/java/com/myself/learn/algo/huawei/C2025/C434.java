package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.Scanner;

/**
 * P00434.华为od机试—信号发射与接收
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class C434 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static int number1;
    static int number2;
    static int number3;


    public static void main(String[] args) {


        if (sc.hasNext()) {
            number1 = sc.nextInt();
            number2 = sc.nextInt();
            number3 = sc.nextInt();
            int n = sc.nextInt();
            int[] prices = new int[n];
            for (int i = 0; i < n; i++) {
                int price = sc.nextInt();
                prices[i] = price;
            }
            String[] res = new String[n];
            for (int i = 0; i < n; i++) {
                int price = prices[i];
                int[] m1 = method(1, price, new int[]{price, 0});
                int[] m2 = method(2, price, new int[]{price, 0});
                int[] m3 = method(3, price, new int[]{price, 0});

                int[] m12 = method(2, price, m1);
                int[] m13 = method(3, price, m1);
                int[] m21 = method(1, price, m2);
                int[] m23 = method(3, price, m2);
                int[] m31 = method(1, price, m3);
                int[] m32 = method(2, price, m3);

                int[][] arry = new int[][]{m1, m2, m3, m12, m13, m21, m23, m31, m32};
                Arrays.sort(arry, (a, b) -> {
                    if (a[0] == b[0]) {
                        return a[1] - b[1];
                    }
                    return a[0] - b[0];
                });
                res[i] =  arry[0][0] + " " + arry[0][1];
            }

            // 打印结果
            for (String re : res) {
                System.out.println(re);
            }
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static int[] method(int type, int oldPrice, int[] old) {
        int newPrice = old[0];
        int newNumber = old[1];
        int number = 0;
        switch (type) {
            case 1:
                number = number1;
                break;
            case 2:
                number = number2;
                break;
            case 3:
                number = number3;
                break;
            default:
                break;
        }

        switch (type) {
            case 1:
                int i = newPrice / 100;
                while (number > 0 && i > 0) {
                    newPrice -= 10;
                    number--;
                    i--;
                    newNumber++;
                }
                break;
            case 2:
                if (number > 0) {
                    newPrice = newPrice * 92 / 100;
                    newNumber++;
                }
                break;

            case 3:
                while (number > 0) {
                    newPrice -= 5;
                    number--;
                    newNumber ++;
                }
            default:
                break;
        }
        return new int[]{newPrice, newNumber};
    }
}
