package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.Scanner;

/**
 * P00393.华为od机试—流量波峰【2025新题】
 * 2025C卷,双机位A卷
 * difficulty:3
 * @author luqi
 */
public class C393 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int min = Integer.MAX_VALUE;
            boolean found = false;
            String line = sc.nextLine();
            int[] nums = Arrays.stream(line.trim().split("\\s+")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            for (int i = 1; i < nums.length - 1; i++) {
                int number = nums[i];
                for (int left = i - 1; left >= 0; left--) {
                    for (int right = i + 1; right < nums.length; right++) {
                        if (nums[left] < number && number > nums[right]) {
                            min = Math.min(min, right - left);
                            found = true;
                            break;
                        }
                    }
                }
            }
            System.out.println(found ? min : -1);

        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
