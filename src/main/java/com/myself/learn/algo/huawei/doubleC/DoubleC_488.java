package com.myself.learn.algo.huawei.doubleC;

import java.util.Arrays;
import java.util.Scanner;

/**
 * P00488.—部门人力分配
 **/
public class DoubleC_488 {
    static int m;
    static int n;
    static int res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String inputN = sc.nextLine();
        // m月完成
        m = Integer.parseInt(inputN.trim());
        String line = sc.nextLine();
        // 需求人力表
        int[] requirements = Arrays.stream(line.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(requirements);
        // n个需要
        n = requirements.length;
        if(n < 2){
            System.out.println(requirements[0]);
            return;
        }
        // 人力/月
        int q = 0;
        // q 的范围
        int min = Math.max(requirements[n-1],requirements[0] + requirements[1]);
        int max = requirements[n-1] + requirements[n-2];
        int limit = 0;
        while (min <= max){
            limit = (min + max) / 2 ;
            boolean isOk = checkLimit(limit,requirements);
            if (isOk){
                res = limit;
                max = limit - 1;
            }else {
                min = limit + 1;
            }
        }
        System.out.println(res);
    }

    private static boolean checkLimit(int limit, int[] requirements) {
        int count = 0;
        int left = 0,right = requirements.length-1;
        while (left <= right) {
            if (requirements[left] + requirements[right] > limit) {
                right --;
            }else{
                left ++;
                right --;
            }
            count++;
        }
        return m >= count;
    }
}