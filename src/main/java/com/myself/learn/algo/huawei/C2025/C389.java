package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * P00389.华为od机试—考古学家 / 考古问题
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class C389 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static List<String> res = new ArrayList<>();
    static String[] array;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            sc.nextLine();

            array = Arrays.stream(sc.nextLine().trim().split("\\s+")).map(String::trim).toArray(String[]::new);
            Arrays.sort(array);
            // 全排列 用DFS
            visited = new boolean[array.length];
            int index = 0;
            dfs(index);
            res.forEach(System.out::println);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static void dfs(int index) {
        // 结束条件
        if (index == array.length) {
            res.add(sb.toString());
            return;
        }

        // 遍历
        for (int i = 0; i < array.length; i++) {
            // 做剪枝
            if (i > 0 && array[i].equals(array[i - 1]) && !visited[i - 1]) continue;

            if (visited[i]) continue;
            visited[i] = true;
            sb.append(array[i]);
            dfs(index + 1);
            visited[i] = false;
            sb.delete(sb.length() - array[i].length(),sb.length());
        }
    }
}
