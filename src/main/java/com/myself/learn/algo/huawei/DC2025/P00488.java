package com.myself.learn.algo.huawei.DC2025;

import java.util.Arrays;
import java.util.Scanner;

/**
 * P00488.华为od机试—部门人力分配
 * 双机位C卷
 * difficulty:5
 * @author luqi
 */
public class P00488 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int m = sc.nextInt();
            sc.nextLine();
            int[] requirements = Arrays.stream(sc.nextLine().trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(requirements);
            // 最小月需求量
            int minNeed = 0;
            int left = requirements[requirements.length - 1];
            int right = left * 2;

            // 二分法
            while (left <= right) {
                int middle = left + (right - left) / 2;
                boolean isPossible = checkRequirements(requirements, middle, m);
                if (isPossible) {
                    // 符合要求
                    minNeed = middle;
                    // 尝试更小的值
                    right = middle - 1;
                }else {
                    left = middle + 1;
                }
            }

            System.out.println(minNeed);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    /**
     * 检查给定的 middle 是否满足要求
     * @param requirements 需求数组
     * @param middle 中间值
     * @param m 月份
     * @return 1-need > m  -1:need < m 0 need == m
     */
    private static boolean checkRequirements(int[] requirements, int middle, int m) {
        int left = 0, right = requirements.length - 1;
        int needM = 0;
        while (left <= right) {
            if (requirements[left] + requirements[right] <= middle) {
                left++;
                right--;
                needM ++;
            } else {
                right--;
                needM ++;
            }
            if (needM > m) {
                return false;
            }
        }
        return true;
    }
}
