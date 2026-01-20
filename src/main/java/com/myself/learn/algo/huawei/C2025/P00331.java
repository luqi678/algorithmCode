package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * P00331.华为od机试—最小资金赢得最多选票 / 选举拉票
 * 2025C卷
 * difficulty:5
 * @author luqi
 */
public class P00331 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            long res = Integer.MAX_VALUE;
            int n = sc.nextInt();
            // 各个候选者初始票数
            Map<Integer,List<Integer>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int id = sc.nextInt();
                int cost = sc.nextInt();
                map.computeIfAbsent(id,k->new ArrayList<>()).add(cost);
            }
            // 假设票数到达x需要的最小资金为f(x)
            for (int i = 1; i <= n; i++) {
                long totalCost = 0;
                int currentVotes = map.getOrDefault(0, new ArrayList<>()).size();
                List<Integer> remainCosts = new ArrayList<>();
                // 将其他候选者的票数买到x-1
                for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
                    Integer k = entry.getKey();
                    List<Integer> v = entry.getValue();
                    if (k != 0) {
                        Collections.sort(v);
                        int size = v.size();
                        int needBuy = 0;
                        if (size >= i) {
                            needBuy = size - (i - 1);
                        }
                        for (int j = 0; j < v.size(); j++) {
                            if (j < needBuy) {
                                totalCost += v.get(j);
                                currentVotes++;
                            } else {
                                remainCosts.add(v.get(j));
                            }
                        }
                    }
                }
                // 如何还是不够，补齐
                if(currentVotes < i) {
                    Collections.sort(remainCosts);
                    int needMore = i - currentVotes;
                    for (int j = 0; j < needMore; j++) {
                        totalCost += remainCosts.get(j);
                    }
                }
                res = Math.min(res, totalCost);
            }

            System.out.println(res);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
