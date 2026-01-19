package com.myself.learn.algo.huawei.two;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * P00496.华为od机试—信号发射与接收
 * 双机位C卷
 * difficulty:4
 * @author luqi
 */
public class P004963 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] grid = new int[n][m];
            int[][] res = new int[n][m];
            for (int i = 0; i < n * m; i++) {
                grid[i / m][i % m] = sc.nextInt();
            }
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    while (!stack.isEmpty() && grid[i][j] >= stack.peek()) {
                        stack.pop();
                        res[i][j] ++;
                    }
                    if (j > 0 && grid[i][j - 1] > grid[i][j] ) {
                        res[i][j] ++;
                    }
                    stack.push(grid[i][j]);
                }
                stack.clear();
            }

            for (int j = 0; j < m; j++) {
                for (int i = 0; i < n; i++) {
                    while (!stack.isEmpty() && grid[i][j] >= stack.peek()) {
                        stack.pop();
                        res[i][j] ++;
                    }
                    if (i > 0 && grid[i - 1][j] > grid[i][j] ) {
                        res[i][j] ++;
                    }
                    stack.push(grid[i][j]);
                }
                stack.clear();
            }
            System.out.println(n + " " + m);
            System.out.println(Arrays.stream(res).map(Arrays::toString).collect(Collectors.joining("\n")));
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

}
