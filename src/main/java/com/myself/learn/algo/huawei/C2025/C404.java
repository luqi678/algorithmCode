package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.Scanner;

public class C404 {
    static int[] nums = new int[6];
    static int[] result = new int[6];
    static boolean found = false; // 标记是否找到解
    static boolean[] visited = new boolean[6];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            String line = sc.nextLine();
            // 解析输入，替换掉括号并分割
            try {
                nums = Arrays.stream(line.replaceAll("\\[|\\]", "").split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            } catch (Exception e) {
                // 防止空输入等异常
                System.out.println("invalid");
                return;
            }

            // 策略：从大到小排序
            // Java Arrays.sort 默认是升序，我们先升序再倒着遍历，或者手动反转
            Arrays.sort(nums);
            // 反转数组使其变为降序: [0,2,3,0,5,6] -> [0,0,2,3,5,6] -> [6,5,3,2,0,0]
            for(int i = 0, j = nums.length - 1; i < j; i++, j--) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }

            dfs(0);

            if (found) {
                System.out.println(
                        "" + result[0] + result[1] + ":" +
                                result[2] + result[3] + ":" +
                                result[4] + result[5]
                );
            } else {
                System.out.println("invalid");
            }
        }
        sc.close();
    }

    private static void dfs(int index) {
        // 如果已经找到最大值，直接停止后续所有搜索（剪枝）
        if (found) return;

        // 填满了6位，说明找到了合法的最大时间
        if (index == 6) {
            found = true;
            return;
        }

        for (int i = 0; i < 6; i++) {
            // 如果该数字用过，跳过
            if (visited[i]) continue;

            // 如果已经找到，跳过后续循环
            if (found) return;

            int val = nums[i];

            // --- 核心剪枝逻辑：检查当前位是否合法 ---
            // 填第1位 (Hour 十位): 不能超过 2
            if (index == 0 && val > 2) continue;

            // 填第2位 (Hour 个位): 如果十位是2，个位不能超过3 (即 < 24)
            if (index == 1 && (result[0] * 10 + val) >= 24) continue;

            // 填第3位 (Minute 十位): 不能超过 5
            if (index == 2 && val > 5) continue;

            // 填第4位 (Minute 个位): 无限制，只要前面合法即可 (但必须配合十位 < 60，上面已限制十位)
            // 其实这里只要 index==2限制了 val<=5，那么 index==3 随便填都 < 60

            // 填第5位 (Second 十位): 不能超过 5
            if (index == 4 && val > 5) continue;

            // 填第6位 (Second 个位): 无限制

            // --- 递归 ---
            result[index] = val;
            visited[i] = true;
            dfs(index + 1);
            visited[i] = false; // 回溯
        }
    }
}