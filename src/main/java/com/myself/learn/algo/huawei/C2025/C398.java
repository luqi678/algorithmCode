package com.myself.learn.algo.huawei.C2025;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * P00398.华为od机试—内存资源分配
 * 2025C卷
 * difficulty:4
 * @author luqi
 */
public class C398 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 读取内存池配置
        if (!sc.hasNextLine()) return;
        String line1 = sc.nextLine();

        // 使用 TreeMap 存储内存：Key=大小, Value=数量
        // TreeMap 会自动按 Key 升序排序
        TreeMap<Integer, Integer> pool = new TreeMap<>();

        // 简单的解析逻辑
        String[] poolStrs = line1.trim().split(",");
        for (String str : poolStrs) {
            String[] parts = str.trim().split(":");
            int size = Integer.parseInt(parts[0]);
            int count = Integer.parseInt(parts[1]);
            pool.put(size, pool.getOrDefault(size, 0) + count);
        }

        // 2. 读取申请列表
        if (!sc.hasNextLine()) return;
        String line2 = sc.nextLine();
        String[] reqStrs = line2.trim().split(",");

        // 3. 处理请求
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < reqStrs.length; i++) {
            int request = Integer.parseInt(reqStrs[i]);

            // 核心优化：寻找大于等于 request 的最小 Key
            // ceilingKey 方法的时间复杂度是 O(log N)
            Integer bestFitSize = pool.ceilingKey(request);

            if (bestFitSize != null) {
                // 分配成功
                sb.append("true");

                // 扣减库存
                int currentCount = pool.get(bestFitSize);
                if (currentCount > 1) {
                    pool.put(bestFitSize, currentCount - 1);
                } else {
                    // 如果数量为0，直接移除该 Key，减少后续查找范围
                    pool.remove(bestFitSize);
                }
            } else {
                // 分配失败
                sb.append("false");
            }

            // 处理逗号
            if (i < reqStrs.length - 1) {
                sb.append(",");
            }
        }

        System.out.println(sb.toString());
        sc.close();
    }
}