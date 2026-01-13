package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.Scanner;

/**
 * P00390.华为od机试—连续出牌数量 / 最大出牌数量
 * 2025C卷
 * difficulty:5
 * @author luqi
 */
public class C390 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);

    static int maxCount = 0;
    static int[] nums;
    static String[] colors;
    static int m;
    static boolean[] visited;

    public static void main(String[] args) {

        if (sc.hasNext()) {
            String line = sc.nextLine();
            nums = Arrays.stream(line.trim().split("\\s+")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            String line2 = sc.nextLine();
            colors = line2.trim().split("\\s+");
            if (nums.length != colors.length) {
                System.out.println("Error");
                return;
            }
            m = nums.length;
            int index = 0;
            // 深度优先
            visited = new boolean[m];
            bfs(index, -1,visited);
            System.out.println(maxCount);

        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static void bfs(int index,int prev, boolean[] visited) {
        // 无论是否可以继续 记录当前最大长度
        maxCount = Math.max(maxCount, index);
        // 选择
        for (int i = 0; i < m; i++) {
            // 过滤掉已访问的
            if (visited[i]) continue;
            // 数字或者颜色匹配的牌
            if (index == 0 || nums[i] == nums[prev] || colors[i].equals(colors[prev])) {
                visited[i] = true;
                bfs(index + 1, i, visited);
                visited[i] = false;
            }
        }

    }
}
