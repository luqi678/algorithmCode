package com.myself.learn.algo.huawei.C2025;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * P00399.华为od机试—判断一组不等式是否满足约束并输出最大差
 * 2025C卷
 * difficulty:3
 *
 * @author luqi
 */
public class C399 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        if (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] split = line.trim().split(";");
            String opsLine = split[split.length - 1];
            String[] ops = opsLine.trim().split(",");
            // a
            double[] a1 = Arrays.stream(split[0].trim().split(",")).mapToDouble(Double::parseDouble).toArray();
            int n = ops.length;
            int m = a1.length;
            double[][] as = new double[n][m];
            int[] xs = new int[n];
            double[] bs = new double[n];
            for (int i = 0; i < n; i++) {
                as[i] = Arrays.stream(split[i].trim().split(",")).mapToDouble(Double::parseDouble).toArray();
            }
            xs = Arrays.stream(split[split.length - 3].trim().split(",")).mapToInt(Integer::parseInt).toArray();
            bs = Arrays.stream(split[split.length - 2].trim().split(",")).mapToDouble(Double::parseDouble).toArray();
            boolean flag = true;
            // 计算最大
            double max = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < n; i++) {
                double temp = 0;
                for (int j = 0; j < m; j++) {
                    temp += as[i][j] * xs[j];
                }
                max = Math.max(max, temp - bs[i]);
                switch (ops[i]) {
                    case "<":
                        if (temp - bs[i] < 0){
                        }else {
                            flag = false;
                        }
                        break;

                    case "<=":
                        if (temp - bs[i] <= 0){

                        }else {
                            flag = false;
                        }
                        break;

                    case ">":
                        if (temp - bs[i] > 0){

                        }else {
                            flag = false;
                        }
                        break;

                    case ">=":
                        if (temp - bs[i] >= 0) {

                        } else {
                            flag = false;
                        }
                        break;
                    case "=":
                        if (temp - bs[i] == 0) {

                        } else {
                            flag = false;
                        }
                        break;
                    default:
                        flag = false;
                }
            }
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false); // 不使用千分位逗号
            nf.setMaximumFractionDigits(0); // 最多保留3位小数
            System.out.println(flag + "," + (int) max);
        }


    }
}
