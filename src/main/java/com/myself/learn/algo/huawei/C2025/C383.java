package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.Scanner;

/**
 * P00383.华为od机试—找单词
 * 2025C卷
 * difficulty:4
 * @author luqi
 */
public class C383 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static boolean[][] visited ;
    static int n;
    static String[][] arr;
    static String point;
    static boolean found = false;

    public static void main(String[] args) {

        if (sc.hasNext()) {
            n = sc.nextInt();
            sc.nextLine();
            arr = new String[n][n];
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                String[] words = Arrays.stream(line.trim().split(",")).map(String::trim).toArray(String[]::new);
                arr[i] = words;
            }
            point = sc.nextLine();
            // 找到开头元素位置
            int firstX = -1, firstY = -1;
            boolean found = false;
            for (int i = 0; i < arr.length; i++) {
                if (found) break;
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j].equals(String.valueOf(point.charAt(0)))) {
                        firstX = i;
                        firstY = j;
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                System.out.println("N");
                return;
            }
            visited = new boolean[n][n];
            StringBuilder path = new StringBuilder();
            visited[firstX][firstY] = true;
            path.append(firstX).append(",").append(firstY).append(",");
            // dfs 遍历
            dfs(firstX, firstY,1,path);
            if (!found) {
                System.out.println("N");
             }
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    private static void dfs(int x, int y, int index,StringBuilder path) {
        // 跳出
        if (index == point.length()) {
            System.out.println(path.toString());
            found = true;
            return;
        }
        String currentChar = String.valueOf(point.charAt(index));
        for (int k = 0; k < dirs.length; k++) {
            int newX = x + dirs[k][0];
            int newY = y + dirs[k][1];
            if (newX < 0 || newX >= n || newY < 0 || newY >= n || visited[newX][newY]) continue;
            if (arr[newX][newY].equals(currentChar)){
                visited[newX][newY] = true;
                path.append(newX).append(",").append(newY).append(",");
                dfs(newX, newY, index + 1, path);
            }
        }
    }
}
