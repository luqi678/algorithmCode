package com.myself.learn.algo.huawei.two;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * VLAN id
 *
 * @author 2405051
 */
public class VLAN {

    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String input = sc.nextLine();
        String input2 = sc.nextLine();
        int[] words = new int[4096];
        int word = -1;
        try {
            String[] split = input.split(",");
            for (String s : split) {
                if (s.contains("-")) {
                    String[] split1 = s.split("-");
                    int begin = Integer.parseInt(split1[0]);
                    int end = Integer.parseInt(split1[1]);
                    for (int i = begin - 1; i < end; i++) {
                        words[i] = 1;
                    }
                } else {
                    words[Integer.parseInt(s)-1] = 1;
                }
            }
            word = Integer.parseInt(input2);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(input);
        }
        words[word - 1] = 0;
        List<String> res = new ArrayList<>();
        int begin = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i] == 1) {
                if (begin == -1) {
                    begin = i;
                }
            }else {
                if (begin != -1) {
                    if (i == begin + 1) {
                        res.add(String.valueOf(i));
                    }else {
                        res.add(String.valueOf(begin)  + "-" + String.valueOf(i+1));
                    }
                    begin = -1;
                }
            }
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String input = sc.nextLine();
        // 处理可能没有第二行输入的情况（虽然题目通常会有）
        if (!sc.hasNextLine()) {
            return;
        }
        String input2 = sc.nextLine();

        // 1. 使用位图，VLAN ID 范围 1-4094。
        // 开大一点防止越界，words[i] == 1 表示 VLAN (i+1) 存在
        int[] words = new int[4096];

        try {
            String[] split = input.split(",");
            for (String s : split) {
                if (s.contains("-")) {
                    String[] split1 = s.split("-");
                    int begin = Integer.parseInt(split1[0]);
                    int end = Integer.parseInt(split1[1]);
                    // 题目是以 1 为起始 ID，存入数组 index = ID - 1
                    for (int i = begin - 1; i < end; i++) {
                        words[i] = 1;
                    }
                } else {
                    words[Integer.parseInt(s) - 1] = 1;
                }
            }

            // 2. 移除目标 VLAN
            int removeTarget = Integer.parseInt(input2);
            // 只有当目标在合法范围内时才移除
            if (removeTarget > 0 && removeTarget <= 4094) {
                words[removeTarget - 1] = 0;
            }

        } catch (Exception e) {
            // 异常处理保持原样或根据题目要求调整
            System.out.println("[]");
            return;
        }

        // 3. 生成结果
        // 使用 StringJoiner 来处理逗号分隔，避免 List toString 的 [] 问题
        StringJoiner sj = new StringJoiner(",");

        int begin = -1;
        // 循环到 length，注意 4095 是为了处理最后一个元素如果是 1 的情况
        // 因为我们逻辑是遇到 0 结算上一段，所以数组必须比最大 VLAN ID 多一位
        for (int i = 0; i < words.length; i++) {
            if (words[i] == 1) {
                if (begin == -1) {
                    begin = i; // 记录段开始的 index
                }
            } else {
                // 当前 words[i] == 0，说明刚才那一段结束了
                if (begin != -1) {
                    int startVlan = begin + 1; // 索引转数值
                    int endVlan = i;           // 索引转数值 (i-1)+1 = i

                    if (startVlan == endVlan) {
                        sj.add(String.valueOf(startVlan));
                    } else {
                        sj.add(startVlan + "-" + endVlan);
                    }
                    begin = -1; // 重置
                }
            }
        }

        System.out.println(sj.toString());
    }
}