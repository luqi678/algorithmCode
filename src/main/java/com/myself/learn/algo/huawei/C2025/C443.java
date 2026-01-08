package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class C443 {

    static Scanner sc = new Scanner(System.in);

    static class Customer implements Comparable<Customer> {
        int number;
        int priority;
        int order; // 新增：记录到达的顺序（时间戳）

        public Customer(int number, int priority, int order) {
            this.number = number;
            this.priority = priority;
            this.order = order;
        }

        @Override
        public int compareTo(Customer o) {
            // 1. 优先比较优先级
            if (this.priority != o.priority) {
                // Java PriorityQueue 是小顶堆
                // 题目说 1级最高，5级最低。
                // 我们希望 1 排在前面。 1 - 5 = -4 (负数排前)，逻辑正确。
                return this.priority - o.priority;
            }
            // 2. 优先级相同，比较到达顺序（order小的排前面）
            return this.order - o.order;
        }
    }

    public static void main(String[] args) {
        if (sc.hasNext()) {
            int n = sc.nextInt();
            sc.nextLine();

            PriorityQueue<Customer> queue = new PriorityQueue<>();
            List<Integer> res = new ArrayList<>();

            // 使用 i 作为唯一的时间戳/顺序ID
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                // 判空处理，防止空行报错
                if (line.isEmpty()) continue;

                String[] split = line.trim().split("\\s+");
                String operate = split[0];

                if ("a".equals(operate)) {
                    int number = Integer.parseInt(split[1]);
                    int priority = Integer.parseInt(split[2]);
                    // 将 i 传入构造函数
                    queue.offer(new Customer(number, priority, i));
                } else if ("p".equals(operate)) {
                    Customer poll = queue.poll();
                    // 题目未说明队列为空时p怎么处理，通常机试数据是合法的
                    // 但为了稳健，可以加个判断
                    if (poll != null) {
                        res.add(poll.number);
                    }
                }
            }

            for (Integer re : res) {
                System.out.println(re);
            }
        }
        sc.close();
    }
}