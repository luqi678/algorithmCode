package com.myself.learn.algo.huawei.C2025;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * P00410.华为od机试—优雅子数组
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class C410 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            // 定义数组
            int[] nums = new int[n];
            // 定义元素及其次数映射关系
            Map<Integer, Integer> map = new HashMap<>();
            int left = 0;
            // 子数组个数
            int count = 0;
            for (int right = 0; right < n; right++) {
                int number = sc.nextInt();
                nums[right] = number;
                map.put(number, map.getOrDefault(number, 0) + 1);
                if (map.get(number) >= k) {
                    count += n-right;
                    int leftNumber = nums[left];
                    map.put(leftNumber, map.getOrDefault(leftNumber, 0) - 1);
                    left++;
                }
            }
            System.out.println(count);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
