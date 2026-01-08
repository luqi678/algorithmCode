package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * P00419.华为od机试—核酸最快检测效率
 * 优化方案：贪心算法 (Greedy)
 * 时间复杂度：O(S * logS)，完全满足要求
 */
public class C419 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        if (sc.hasNext()) {
            int s = sc.nextInt(); // 采样员人数
            int v = sc.nextInt(); // 志愿者人数

            long baseTotalEff = 0; // 0志愿者时的保底总效率
            List<Integer> gains = new ArrayList<>(); // 存储所有可能的增益机会

            for (int i = 0; i < s; i++) {
                int n = sc.nextInt();

                // 1. 先加上每个人最惨情况下的效率 (0.8N)
                // 题目保证 n*10% 是整数，所以 n*0.8 也是整数
                baseTotalEff += (long)(n * 0.8);

                // 2. 将每个人的4个提升机会放入池子
                // 第1个志愿者带来的增益: 0.2N
                gains.add((int)(n * 0.2));

                // 第2个志愿者带来的增益: 0.1N
                gains.add((int)(n * 0.1));

                // 第3个志愿者带来的增益: 0.1N
                gains.add((int)(n * 0.1));

                // 第4个志愿者带来的增益: 0.1N
                gains.add((int)(n * 0.1));
            }

            // 3. 贪心策略：将所有增益机会从大到小排序
            // Collections.sort 默认是升序，我们需要降序
            gains.sort(Collections.reverseOrder());

            // 4. 选取前 V 个最大的增益
            // 注意：志愿者数量可能超过需要的最大数量 (4*S)，所以要取 min
            int count = Math.min(v, gains.size());

            long maxTotalEff = baseTotalEff;
            for (int i = 0; i < count; i++) {
                maxTotalEff += gains.get(i);
            }

            System.out.println(maxTotalEff);
        }
        sc.close();
    }
}