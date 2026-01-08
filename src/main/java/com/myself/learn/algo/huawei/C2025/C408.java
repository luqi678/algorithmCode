package com.myself.learn.algo.huawei.C2025;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class C408 {
    static class State {
        int sLeft, wLeft, steps;

        State(int sLeft, int wLeft, int steps) {
            this.sLeft = sLeft;
            this.wLeft = wLeft;
            this.steps = steps;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;
            State state = (State) o;
            return sLeft == state.sLeft && wLeft == state.wLeft;
        }

        @Override
        public int hashCode() {
            return Objects.hash(sLeft, wLeft);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int M = sc.nextInt(); // 羊
        int N = sc.nextInt(); // 狼
        int X = sc.nextInt(); // 容量

        System.out.println(solve(M, N, X));
    }

    private static int solve(int M, int N, int X) {
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new State(M, N, 0));
        visited.add(M + "," + N);

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            // 尝试运送 i 只羊和 j 只狼
            for (int i = 0; i <= curr.sLeft; i++) {
                for (int j = 0; j <= curr.wLeft; j++) {
                    // 1. 船不能载 0 个，也不能超过 X 个
                    if (i + j == 0 || i + j > X) continue;

                    int nextSLeft = curr.sLeft - i;
                    int nextWLeft = curr.wLeft - j;
                    int nextSRight = M - nextSLeft;
                    int nextWRight = N - nextWLeft;

                    // 2. 检查左岸安全 (如果还有羊，羊必须 >= 狼)
                    if (nextSLeft > 0 && nextSLeft < nextWLeft) continue;

                    // 3. 检查右岸安全 (如果还有羊，羊必须 >= 狼)
                    if (nextSRight > 0 && nextSRight < nextWRight) continue;

                    // 4. 到达目标
                    if (nextSLeft == 0 && nextWLeft == 0) {
                        return curr.steps + 1;
                    }

                    // 5. 加入新状态
                    String stateKey = nextSLeft + "," + nextWLeft;
                    if (!visited.contains(stateKey)) {
                        visited.add(stateKey);
                        queue.add(new State(nextSLeft, nextWLeft, curr.steps + 1));
                    }
                }
            }
        }

        return 0;
    }
}

