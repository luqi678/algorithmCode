package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * P00412.华为od机试—任务调度
 * 2025C卷
 * difficulty:4
 * @author luqi
 */
public class C412 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);

    static class Task implements Comparable<Task>{
        int id;
        int priority;
        int times;
        int reachTime;
        Task(int id, int priority, int times, int reachTime) {
            this.id = id;
            this.priority = priority;
            this.times = times;
            this.reachTime = reachTime;
        }
        @Override
        public int compareTo(Task other) {
            if (this.priority == other.priority) {
                // Java PriorityQueue 是小顶堆
                return this.reachTime - other.reachTime;
            }
            return other.priority - this.priority;
        }
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            int[] split = Arrays.stream(line.trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int id = split[0];
            int priority = split[1];
            int times = split[2];
            int reachTime = split[3];
            tasks.add(new Task(id, priority, times, reachTime));
        }
        PriorityQueue<Task> queue = new PriorityQueue<>();
        int currentTime = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int hasTime = task.reachTime - currentTime;
            while (!queue.isEmpty() && hasTime > 0){
                Task poll = queue.poll();
                if(poll.times > hasTime) {
                    poll.times -= hasTime;
                    queue.offer(poll);
                    hasTime = 0;
                } else {
                    hasTime -= poll.times;
                    System.out.println(poll.id + " " + (currentTime + poll.times));
                }
            }
            currentTime = task.reachTime;
            queue.offer(task);
        }
        while (!queue.isEmpty()){
            Task poll = queue.poll();
            currentTime += poll.times;
            System.out.println(poll.id + " " + (currentTime));
        }

        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
