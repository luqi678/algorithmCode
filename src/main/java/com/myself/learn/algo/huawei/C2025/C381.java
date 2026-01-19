package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * P00381.华为od机试—停车场费用统计【2025新题】
 * 2025C卷
 * difficulty:3
 *
 * @author luqi
 */
public class C381 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNext()) {
            // 1. 读取包月车数量
            int n = 0;
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                sc.nextLine(); // 消耗换行符
            }

            // 2. 读取包月车列表
            Set<String> freeCars = new HashSet<>();
            if (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split("\\s+");
                    // 注意：如果n=0，这里可能没有车牌，但题目格式通常会给空行或不给
                    freeCars.addAll(Arrays.asList(parts));
                }
            }

            // 3. 读取记录并按车牌分组
            Map<String, List<Record>> recordsMap = new HashMap<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) break;

                String[] parts = line.split("\\s+");
                // 格式：时间 车牌号 状态 (8:00 AAA enter)
                // 注意题目描述的格式顺序，示例是: 8:00 AAA enter
                String timeStr = parts[0];
                String plate = parts[1];
                String status = parts[2];

                // 如果是包月车，直接忽略，不参与计算
                if (freeCars.contains(plate)) {
                    continue;
                }

                recordsMap.putIfAbsent(plate, new ArrayList<>());
                recordsMap.get(plate).add(new Record(timeStr, status));
            }

            long totalMoney = 0;

            // 4. 遍历每一辆车，计算费用
            for (String plate : recordsMap.keySet()) {
                List<Record> list = recordsMap.get(plate);
                // 按时间排序，确保先入后出配对正确
                list.sort(Comparator.comparingInt(o -> o.totalMinutes));

                // 假设记录是成对的 (enter, leave)，题目通常保证数据完整性
                // 如果不完整，可以加校验
                for (int i = 0; i < list.size() - 1; i += 2) {
                    Record enter = list.get(i);
                    Record leave = list.get(i + 1);

                    // 只有 enter 配对 leave 才计算 (防止数据异常)
                    if ("enter".equals(enter.status) && "leave".equals(leave.status)) {
                        totalMoney += calculateFee(enter.totalMinutes, leave.totalMinutes);
                    }
                }
            }
            System.out.println(totalMoney);
        }
        sc.close();
    }

    /**
     * 计算单次停车的费用
     */
    private static long calculateFee(int start, int end) {
        // 免费时段区间 (11:30 - 13:30) 转换为分钟
        int freeStart = 11 * 60 + 30; // 690
        int freeEnd = 13 * 60 + 30;   // 810

        // 1. 计算重叠的免费时长 (区间求交集逻辑)
        // 交集 start = max(停车开始, 免费开始)
        // 交集 end   = min(停车结束, 免费结束)
        int overlapStart = Math.max(start, freeStart);
        int overlapEnd = Math.min(end, freeEnd);
        int freeDuration = Math.max(0, overlapEnd - overlapStart);

        // 2. 实际计费时长
        int totalDuration = end - start;
        int chargeDuration = totalDuration - freeDuration;

        // 3. 根据规则计费
        if (chargeDuration < 30) {
            return 0;
        }

        // 规则：超过半小时，不满半小时按半小时算 -> 向上取整
        // 30min -> 1 unit, 31min -> 2 units
        long units = (long) Math.ceil(chargeDuration / 30.0);

        // 4. 8小时封顶 (8小时 = 16个半小时)
        return Math.min(units, 16);
    }

    // 辅助类：解析时间
    static class Record {
        int totalMinutes;
        String status;

        public Record(String timeStr, String status) {
            this.status = status;
            String[] t = timeStr.split(":");
            this.totalMinutes = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
        }
    }
}
