package com.myself.learn.algo.huawei.C2025;

import java.util.Scanner;

/**
 * 华为OD机试万能模板
 * 包含：Scanner初始化、常用输入模式、常用工具函数
 */
public class C445 {


    // 全局静态 Scanner，方便在任何函数中使用
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        /* * ============================================================
         * 模式 A: 固定行数/组数输入
         * 场景：第一行是 N，后面紧跟 N 行数据
         * ============================================================
         */
        /*
        if (sc.hasNext()) {
            int n = sc.nextInt();
            sc.nextLine(); // 【重要】吃掉整数后的换行符

            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                // TODO: 处理每一行逻辑
                System.out.println("处理第 " + (i+1) + " 行: " + line);
            }
        }
        */

        /* * ============================================================
         * 模式 B: 多行输入，直到 EOF (End Of File)
         * 场景：题目没说多少行，只说“多组输入”
         * ============================================================
         */
        /*
        while (sc.hasNext()) {
            String line = sc.next(); // 或者 nextInt()
            // TODO: 处理逻辑
            System.out.println("读入: " + line);
        }
        */

        /* * ============================================================
         * 模式 C: 一行字符串转数组 (空格分隔) (逗号分隔)
         * 场景：输入 "1 2 3 4 5" "1,2,3,4,5"
         * ============================================================
         */
        /*
        if (sc.hasNextLine()) {
            String line = sc.nextLine();
            // 使用下方封装的工具方法快速转换
            int[] nums = toIntArray(line, " ");
            int[] nums = toIntArray(line, "\\s+");

            // TODO: 逻辑处理
            System.out.println(Arrays.toString(nums));
        }
        */

        /* * ============================================================
         * 模式 E: 二维矩阵输入
         * 场景：第一行 m n，后续 m 行数据
         * ============================================================
         */
        if (sc.hasNext()) {
            int m = sc.nextInt();
            int[][] grid = new int[m][m];

            for(int i=0; i<m; i++){
                for(int j=0; j<m; j++){
                    grid[i][j] = sc.nextInt();
                }
            }
            Long S;
            long M = (long) m;
            S =  (M * M + 1) * M / 2;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    // 这里
                    for (int k = 0; k < m; k++) {
                        for (int l = 0; l < m; l++) {
                            if (check(grid, S, i, j,k,l)) {
                                System.out.println((i + 1) + " " + (j+1) + " " + grid[i][j]);
                                System.out.println((k + 1) + " " + (l+1) + " " + grid[i][j]);
                            }
                        }
                    }

                }
            }


        }

        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static Boolean check(int[][] grid, Long s, int i, int j, int k, int l) {
        if (i == k && j == l) {
            return false;
        }

        // 交换
        int temp = grid[i][j];
        grid[i][j] = grid[k][l];
        grid[k][l] = temp;

        long iS = 0,jS= 0,kS = 0,lS = 0,dilog1 =0,dilog2 = 0;
        for (int m = 0; m < grid.length; m++) {
            iS = iS + grid[i][m];
            jS = jS + grid[m][j];
            kS = kS + grid[k][m];
            lS = lS + grid[m][l];
            dilog1 = dilog1 + grid[m][m];
            dilog2 = dilog2 + grid[m][grid.length - 1 - m];
        }
        if (iS == s && jS == s && kS == s && lS == s && dilog1 == s && dilog2 == s) {
            return true;
        }else{
            // 恢复
            grid[k][l] = grid[i][j];
            grid[i][j] = temp;
        }
        return false;
    }

}