package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class C422 {
    static class Edge {
        int u, v, cost;
        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }

    static int[] parent;
    static int count; // 连通分量数量

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        int n = sc.nextInt(); // 城市数
        int m = sc.nextInt(); // 可选路径数
        int k = sc.nextInt(); // 必建路径数

        // 初始化并查集
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;
        count = n;

        // 存储所有可选边，方便根据 u,v 查 cost
        // 注意：题目说必建路径是参数2的子集
        Map<String, Integer> edgeMap = new HashMap<>();
        List<Edge> allEdges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int cost = sc.nextInt();
            allEdges.add(new Edge(u, v, cost));
            // 保持 u < v 方便查找
            String key = Math.min(u, v) + "_" + Math.max(u, v);
            edgeMap.put(key, cost);
        }

        long totalCost = 0;
        // 记录哪些边已经被必建占用了，防止重复计算（虽然 Kruskal 会通过 find 过滤，但必建的钱必须先收）
        // 实际上题目说必建是子集，我们要先处理这些
        Set<String> mandatorySet = new HashSet<>();
        for (int i = 0; i < k; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            String key = Math.min(u, v) + "_" + Math.max(u, v);
            mandatorySet.add(key);

            // 累加必建成本并合并
            if (union(u, v)) {
                // 如果这次 union 成功，说明连接了新的组件
            }
            // 无论是否已经连通，必建的成本都要加（题目逻辑：固定建设）
            // 注意：如果同一个必建边输入多次，按题意理解只需加一次成本
            // 这里假设每种 u,v 组合在必建中只出现一次且对应 M 中的一条边
            totalCost += edgeMap.getOrDefault(key, 0);
        }

        // Kruskal 算法
        // 按成本升序排序
        allEdges.sort(Comparator.comparingInt(e -> e.cost));

        for (Edge edge : allEdges) {
            String key = Math.min(edge.u, edge.v) + "_" + Math.max(edge.u, edge.v);
            // 如果这条边不是必建边，且当前不连通，则选择它
            if (!mandatorySet.contains(key)) {
                if (union(edge.u, edge.v)) {
                    totalCost += edge.cost;
                }
            }
        }

        // 检查最终是否所有城市都连通
        if (count == 1) {
            System.out.println(totalCost);
        } else {
            System.out.println("-1");
        }
    }

    static int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }

    static boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
            count--;
            return true;
        }
        return false;
    }
}

