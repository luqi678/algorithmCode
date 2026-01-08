package com.myself.learn.algo.huawei.doubleC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * P00478. 华为od机试—查字典
 **/
public class DoubleC_478 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextLine()) {
            return;
        }
        String line = sc.nextLine();
        String[] split = line.split("\\s+");
        String sub = split[0];
        int num = Integer.parseInt(split[1]);
        if (split.length != num + 2) {
            System.out.println("-1");
            return;
        }
        String[] items = new String[num];
        for (int i = 2; i < split.length; i++) {
            items[i - 2] = split[i];
        }
        boolean isHas = false;
        for (int i = 0; i < items.length; i++) {
            String item = items[i];
            if (item.length() < sub.length()) {
                continue;
            }
            boolean check = true;
            for (int j = 0; j < sub.length(); j++) {
                if (sub.charAt(j) != item.charAt(j)) {
                    check = false;
                    break;
                }
            }
            if (check) {
                isHas = true;
                System.out.println(item);
            }
        }

        if (!isHas) {
            System.out.println("-1");
        }
    }



    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 读取前缀 (使用 next() 自动处理空格和换行)
        if (!sc.hasNext()) {
            return;
        }
        String sub = sc.next();

        // 2. 读取数量
        if (!sc.hasNextInt()) {
            return;
        }
        int num = sc.nextInt();

        List<String> result = new ArrayList<>();

        // 3. 循环读取指定数量的单词
        for (int i = 0; i < num; i++) {
            // 这里必须检查 hasNext，防止输入中断
            if (sc.hasNext()) {
                String item = sc.next();

                // 使用官方 API 替代手写循环，更稳健
                if (item.startsWith(sub)) {
                    result.add(item);
                }
            }
        }

        // 4. 输出逻辑
        if (result.isEmpty()) {
            System.out.println("-1");
        } else {
            for (String s : result) {
                System.out.println(s);
            }
        }
    }
}