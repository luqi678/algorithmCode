package com.myself.learn.algo.huawei.doubleC;

import java.util.Arrays;
import java.util.Scanner;

public class DoubleC_481 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;

        // 1. 使用 Long 防止 N 过大溢出（虽然题目说是int范围，但在逻辑判断中long更安全）
        long n = Long.parseLong(sc.nextLine().trim());

        if (!sc.hasNextLine()) return;

        int[] original = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(String::trim).mapToInt(Integer::parseInt).toArray();

        int len = original.length;

        // 2. 【核心】构造双倍数组，物理上模拟环
        // 这样就不需要复杂的取模逻辑，也不会死循环
        int[] nums = new int[len * 2];
        for (int i = 0; i < len * 2; i++) {
            nums[i] = original[i % len];
        }

        int left = 0;
        int right = 0;
        long currentSum = 0;
        int maxLen = 0;
        int bestStart = -1; // 记录最佳起始位置

        // 3. 遍历双倍数组
        while (right < nums.length) {
            currentSum += nums[right];

            // 4. 如果和大于容忍值，收缩左边
            while (currentSum > n) {
                currentSum -= nums[left];
                left++;
            }

            // 5. 【关键】如果窗口长度超过原数组长度（168），强制收缩左边
            while (right - left + 1 > len) {
                currentSum -= nums[left];
                left++;
            }

            // 6. 更新最大值
            // 此时窗口合法 (sum <= n 且 len <= 168)
            int currentLen = right - left + 1;
            if (currentLen > maxLen) {
                maxLen = currentLen;
                bestStart = left;
            }

            right++;
        }

        if (bestStart == -1) {
            System.out.println("-1 -1");
        } else {
            // 7. 计算结束位置并取模
            // bestStart 是在双倍数组中的下标，需要 % len
            // bestEnd 同理
            int finalStart = bestStart % len;
            int finalEnd = (bestStart + maxLen - 1) % len;
            System.out.println(finalStart + " " + finalEnd);
        }
    }
}