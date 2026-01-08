package com.myself.learn.algo.huawei.doubleC;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 在图像处理中，坏点(异常值)会影响后续分析，需进行矫正。本题要求对3x3矩阵的中心元素进行坏点检测与矫正，规则如下
 *
 * 1.坏点判断依据：
 *
 * 计算中心元素（位置1，1）与周围8个元素的均值的差值绝对值 diff = |中心元素 - 周围均值|。
 *
 * 2.矫正规则:
 *
 * 若diff>50:用周围均值(周围8个元素的平均值,四舍五入取整数）替代中心元素
 *
 * 若30<=diff<=50:用3x3矩阵的整体均值(9个元素的平均取整数)替代中心元素;值，四舍五入取整数)替代中心元素;
 *
 * 若diff<30:中心元素不变。
 *
 * 3.输出:矫正后的3x3矩阵(每行元素用空格分隔)。
 *
 * 输入描述：
 *
 * 输入共3行，每行3个整数(取值范围0-255)，表示3x3矩阵(每行元素用空格分隔)。
 *
 * 输入描述
 *
 * 输入共3行，表示矫正后的3x3矩阵(每行元素用空格分隔)
 *
 * 示例1
 *
 * 输入：
 *
 * 10 10 10
 * 10 200 10
 * 10 10 10
 *
 * 输出：
 *
 * 10 10 10
 * 10 10 10
 * 10 10 10
 *
 * 示例2
 *
 * 输入：
 *
 * 100 100 100
 * 100 140 100
 * 100 100 100
 *
 * 输出：
 *
 * 100 100 100
 * 100 104 100
 * 100 100 100
 **/
public class ImagesBroke_493 {
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] items = new int[3][3];
        for (int i = 0; i < 3; i++) {
            if (!sc.hasNextLine()) {
                return;
            }
            String line = sc.nextLine();
            String[] split = line.split("\\s+");
            if (split.length != 3) {
                return;
            }
            int[] item = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
            items[i] = item;
        }

        // 计算边缘平均值
        int allSum = 0;
        for (int[] item : items) {
            int sum = Arrays.stream(item).sum();
            allSum += sum;
        }
        double notCenter = (double) (allSum - items[1][1]) /8;
        double diff = Math.abs(notCenter - items[1][1]);
        if (diff > 50) {
            BigDecimal res = BigDecimal.valueOf(notCenter).setScale(0, RoundingMode.HALF_UP);
            items[1][1] = Integer.parseInt(res.toString());
        }else if (diff <= 50 && diff >= 30) {
            double all = (double) allSum / 9;
            BigDecimal res = BigDecimal.valueOf(all).setScale(0, RoundingMode.HALF_UP);
            items[1][1] = Integer.parseInt(res.toString());
        }

        System.out.println(Arrays.deepToString(items));
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] items = new int[3][3];
        int allSum = 0;

        // 1. 读取输入并计算总和
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sc.hasNextInt()) {
                    items[i][j] = sc.nextInt();
                    allSum += items[i][j];
                }
            }
        }

        // 2. 坏点检测与矫正逻辑
        int centerValue = items[1][1];
        int surroundingSum = allSum - centerValue;

        // 周围8个元素的均值（四舍五入）
        // 使用 Math.round 或 (sum + 4) / 8 实现四舍五入
        int avgSurrounding = (int) Math.round(surroundingSum / 8.0);

        double diff = Math.abs(centerValue - (surroundingSum / 8.0));

        if (diff > 50) {
            // 规则：diff > 50，用周围均值替代
            items[1][1] = avgSurrounding;
        } else if (diff >= 30) {
            // 规则：30 <= diff <= 50，用整体均值替代
            int avgAll = (int) Math.round(allSum / 9.0);
            items[1][1] = avgAll;
        }
        // 若 diff < 30，保持不变

        // 3. 按照题目要求的格式输出
        for (int i = 0; i < 3; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                sb.append(items[i][j]);
                if (j < 2) sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }
}