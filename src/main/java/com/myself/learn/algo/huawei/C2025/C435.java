package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * P00435.华为od机试—九宫格游戏 / 三阶积幻方
 * 修正点：
 * 1. 结果保存时使用 clone() 防止引用覆盖。
 * 2. 输入数组先 sort()，确保输出结果按字典序排列。
 * 3. 输出格式去除 Arrays.toString 的方括号和逗号。
 */
public class C435 {

    static Scanner sc = new Scanner(System.in);
    // 保存最终结果的列表
    static List<int[]> res = new ArrayList<>();

    public static void main(String[] args) {
        if (sc.hasNext()) {
            String input = sc.nextLine();
            // 解析输入
            Integer[] array = Arrays.stream(input.trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);

            if (array.length != 9) {
                // 题目通常保证输入合法，但也为了健壮性保留
                return;
            }

            // 【关键修正1】必须先排序！
            // 题目要求输出按升序排列，DFS优先取小数字即可保证结果有序
            Arrays.sort(array);

            int[] grid = new int[9];
            boolean[] visited = new boolean[9];

            dfs(array, grid, visited, 0);

            // 【关键修正2】格式化输出
            for (int[] re : res) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < re.length; i++) {
                    sb.append(re[i]);
                    if (i != re.length - 1) {
                        sb.append(" ");
                    }
                }
                System.out.println(sb.toString());
            }
        }
        sc.close();
    }

    private static void dfs(Integer[] array, int[] grid, boolean[] visited, int index) {
        // 结束条件
        if (index == 9) {
            if (isValid(grid)) {
                // 【关键修正3】必须存副本 (clone)，否则存的是引用，后续回溯会修改它
                res.add(grid.clone());
            }
            return;
        }

        // 剪枝优化（可选）：
        // 可以在 index == 3 时检查第一行，index == 6 时检查第二行，不满足直接 return
        // 但由于 9! 较小，不剪枝也能过。

        // 递归
        for (int j = 0; j < array.length; j++) {
            if (!visited[j]) {
                visited[j] = true;
                grid[index] = array[j];
                dfs(array, grid, visited, index + 1);
                visited[j] = false;
            }
        }
    }

    private static boolean isValid(int[] grid) {
        // 使用 long 防止乘积溢出 (题目说 < 2^31-1，其实 int 也够，但 long 更稳)
        long target = (long) grid[0] * grid[1] * grid[2];

        // 检查每一行
        for (int i = 1; i < 3; i++) {
            long rowProd = (long) grid[i * 3] * grid[i * 3 + 1] * grid[i * 3 + 2];
            if (rowProd != target) return false;
        }

        // 检查每一列
        for (int i = 0; i < 3; i++) {
            long colProd = (long) grid[i] * grid[i + 3] * grid[i + 6];
            if (colProd != target) return false;
        }

        // 检查对角线
        long diag1 = (long) grid[0] * grid[4] * grid[8];
        if (diag1 != target) return false;

        long diag2 = (long) grid[2] * grid[4] * grid[6];
        if (diag2 != target) return false;

        return true;
    }
}