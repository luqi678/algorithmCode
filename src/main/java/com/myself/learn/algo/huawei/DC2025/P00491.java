package com.myself.learn.algo.huawei.DC2025;

import java.util.Scanner;

/**
 * P00491.华为od机试—分月饼
 * 双机位C卷
 * difficulty:3
 * @author luqi
 */
public class P00491 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static int m;
    static int n;
    static int total = 0;

    public static void main(String[] args) {

        if (sc.hasNext()) {
            // m个员工
            m = sc.nextInt();
            // n个月饼
            n = sc.nextInt();

            // 用dfs递归解决
            for (int i = 1; i <= n - m; i++) {
                dfs(n - m - i, 1, i);
            }


            System.out.println(total);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    /**
     * 递归函数，用于计算所有可能的分月饼方案
     * @param allNums 剩余的月饼数
     * @param index 当前员工编号
     * @param preNums 上一次员工分月饼数
     */
    private static void dfs(int allNums, int index, int preNums) {
        // 结束条件
        if(index == m){
            total++;
            return;
        }
        // 选择
        for (int i = 0; i <= 3; i++) {
            int currentNums = preNums - i;
            // 剪枝
            // 剩余的月饼数小于0
            if (allNums - currentNums < 0) {
                continue;
            }
            // 剩余的月饼数大于剩余员工数*当前员工分月饼数
            if ((allNums - currentNums) > (m - index - 1 )* currentNums) {
                continue;
            }
            if (allNums == 0 && currentNums > 3 ){
                continue;
            }
            dfs(allNums - currentNums, index + 1, currentNums);
        }

    }
}
