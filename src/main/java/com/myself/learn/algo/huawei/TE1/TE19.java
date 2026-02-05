package com.myself.learn.algo.huawei.TE1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 19- 岛屿的周长
 * difficulty:简单
 * frequency:高
 * @author luqi
 */
public class TE19 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);

    static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {

        if (sc.hasNext()) {
            // 周长
            int count = 0;
            // 矩阵
            List<int[]> list = new ArrayList<>();
            while (sc.hasNextLine()) {
                int[] array = Arrays.stream(sc.nextLine().trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
                list.add(array);
            }
            for (int i = 0; i < list.size(); i++) {
                int[] ints = list.get(i);
                for (int j = 0; j < ints.length; j++) {
                    int anInt = ints[j];
                    if (anInt == 0) {
                        // 海洋跳过
                        continue;
                    }
                    for (int m = 0; m < dirs.length; m++) {
                        int x = i + dirs[m][0];
                        int y = j + dirs[m][1];
                        // 越界
                        if (x < 0 || x >= list.size() || y < 0 || y >= list.get(0).length) {
                            count++;
                            continue;
                        }
                        // 是海洋
                        if (list.get(x)[y] == 0) {
                            count++;
                        }
                    }
                }
            }
            System.out.println(count);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
