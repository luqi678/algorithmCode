package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * 华为OD机试万能模板
 * 包含：Scanner初始化、常用输入模式、常用工具函数
 * 要求： 稳 < 10^7 < 可能出现问题 < 10^8 < 可能出现问题 < 3.1 * 10^8 < 一定有问题
 */
public class C444 {

    // 全局静态 Scanner，方便在任何函数中使用
    static Scanner sc = new Scanner(System.in);

    public static class Node {
        int N;
        int min;
        public Node(int N, int min) {
            this.N = N;
            this.min = min;
        }
    }


    public static void main(String[] args) {


        if (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            System.out.println(bfs(n, m));



        }

        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static Integer bfs(int n, int m) {
        // 初始化
        Queue<Node> queue = new ArrayDeque<>();
        List<Integer> visited = new ArrayList<>();
        queue.offer(new Node(n, 0));
        visited.add(n);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            // 结束条件
            if (current.N == m) {
                return current.min;
            }

            // 逻辑处理
            int newN = current.N;
            newN = current.N + 1;
            extracted(newN, visited, queue, current);
            newN = current.N - 1;
            extracted(newN, visited, queue, current);
            newN = current.N * 2;
            extracted(newN, visited, queue, current);
        }
        return -1;
    }

    private static void extracted(int newN, List<Integer> visited, Queue<Node> queue, Node current) {
        // 越界处理
        if (newN < 1 || newN > 100000) {
            return ;
        }
        // 访问处理
        if (visited.contains(newN)) {
            return ;
        }
        visited.add(newN);
        queue.offer(new Node(newN, current.min + 1));
    }
}