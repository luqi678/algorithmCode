package com.myself.learn.algo.huawei.C2025;

import java.util.Scanner;

/**
 * P00424.华为od机试—区块链文件转储系统
 * 修正点：使用标准的滑动窗口模板，修复边界丢失问题
 */
public class C424 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        if (sc.hasNext()) {
            try {
                int limit = sc.nextInt();

                // 处理输入可能存在的换行或空行问题
                // 建议直接用循环读取整数，比 split 字符串更稳健且省内存
                // 但为了保持和你原代码风格一致，这里修正一下读取逻辑
                String arrayStr = "";
                while (arrayStr.isEmpty() && sc.hasNext()) {
                    arrayStr = sc.nextLine(); // 吃掉上一行的换行符，或者读取直到有内容
                    // 如果读到的是纯数字的行（limit后的换行），可能需要再读一次
                    if (arrayStr.trim().isEmpty()) {
                        continue;
                    }
                    // 判断一下这行是不是我们要的数组（简单判断是否包含数字）
                    if (!Character.isDigit(arrayStr.trim().charAt(0))) {
                        arrayStr = "";
                    }
                }

                // 更推荐的读取方式：直接循环 nextInt
                /*
                List<Integer> list = new ArrayList<>();
                while(sc.hasNextInt()) {
                    list.add(sc.nextInt());
                }
                int[] array = list.stream().mapToInt(i->i).toArray();
                */

                // 沿用你的 split 方式（但在机考中要注意 split 可能会因为输入格式导致 NFE）
                String[] parts = arrayStr.trim().split("\\s+");
                int[] array = new int[parts.length];
                for(int i=0; i<parts.length; i++) {
                    array[i] = Integer.parseInt(parts[i]);
                }

                // --- 核心逻辑修正 ---
                int max = 0;
                int sum = 0;
                int left = 0;

                // 1. 主动移动右指针 right
                for (int right = 0; right < array.length; right++) {
                    // 进窗口
                    sum += array[right];

                    // 2. 当窗口超限时，循环移动左指针 left 直到合规
                    while (sum > limit) {
                        sum -= array[left];
                        left++;
                    }

                    // 3. 此时窗口一定是合规的 (sum <= limit)，更新最大值
                    max = Math.max(max, sum);
                }

                System.out.println(max);

            } catch (Exception e) {
                // 兜底，防止输入格式异常
                System.out.println(0);
            }
        }
        sc.close();
    }
}