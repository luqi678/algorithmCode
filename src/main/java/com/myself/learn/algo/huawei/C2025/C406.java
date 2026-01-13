package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * P00406.华为od机试—最大N个数与最小N个数的和
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class C406 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (!sc.hasNextLine()) {
            return;
        }
        try {
            String line = sc.nextLine();
            int i = Integer.parseInt(line.trim());
            if (!sc.hasNextLine()) {
                return;
            }
            String line1 = sc.nextLine();
            Integer[] ints = Arrays.stream(line1.trim().split("\\s+")).map(Integer::parseInt).toArray(Integer[]::new);
            Set<Integer> set = new HashSet<>(Arrays.asList(ints));
            Integer[] number = set.toArray(new Integer[0]);
            Arrays.sort(number);
            if (!sc.hasNextLine()) {
                return;
            }
            String line2 = sc.nextLine();
            int n = Integer.parseInt(line2.trim());
            // 有重叠
            if (2 * n > number.length) {
                System.out.println("-1");
                return;
            }
            // 双指针
            int left = 0;
            int right = number.length - 1;
            int sum = 0;
            for (int n1 = 0; n1 < n; n1++) {
                sum += number[left];
                sum += number[right];
                left ++;
                right --;
            }
            System.out.println(sum);


        } catch (Exception e) {
            // 防止空输入等异常
            System.out.println("-1");
            return;
        }



        if (!sc.hasNextLine()) {
            return;
        }

        if (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            System.out.println(n);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
