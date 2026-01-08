package com.myself.learn.algo.huawei.C2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class C440 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] jobs = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split("\\s+");
            jobs[i][0] = Integer.parseInt(parts[0]); // SLA 时间
            jobs[i][1] = Integer.parseInt(parts[1]); // 积分

        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] job : jobs) {
            int sla = job[0];
            int score = job[1];
            pq.offer(score);
            while (pq.size() > sla) {
                pq.poll();
            }
        }
        int totalScore = 0;
        while (!pq.isEmpty()) {
            totalScore += pq.poll();
        }
        System.out.println(totalScore);

    }


    public static void main1(String[] args) throws IOException {
        // 使用快读，防止 N=10^6 时读取超时
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] jobs = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            jobs[i][0] = Integer.parseInt(parts[0]); // SLA 时间
            jobs[i][1] = Integer.parseInt(parts[1]); // 积分
        }

        // 1. 按 SLA 时间从小到大排序
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        // 2. 最小堆，存储已选择工单的分数
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int sla = jobs[i][0];
            int score = jobs[i][1];

            if (sla > pq.size()) {
                // 如果当前截止时间大于已选任务数，直接添加
                pq.offer(score);
            } else if (!pq.isEmpty() && score > pq.peek()) {
                // 如果时间满了，但当前任务积分更高，替换掉堆顶（最小积分任务）
                pq.poll();
                pq.offer(score);
            }
        }

        // 3. 计算堆中所有积分总和
        long totalScore = 0;
        while (!pq.isEmpty()) {
            totalScore += pq.poll();
        }

        System.out.println(totalScore);
    }
}
