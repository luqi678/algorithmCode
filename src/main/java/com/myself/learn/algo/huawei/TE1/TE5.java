package com.myself.learn.algo.huawei.TE1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 5- 三数之和
 * difficulty:中等
 * frequency:高
 * @author luqi
 */
public class TE5 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);

    public static int[] toIntList(String line, String split) {
        String[] arr = line.trim().split(split);
        return Arrays.stream(arr).map(String::trim).mapToInt(Integer::parseInt).toArray();
    }
    public static void main(String[] args) {

        if (sc.hasNext()) {
            int[] list;
            String line = sc.nextLine();
            if (line.contains(",")) {
                list = toIntList(line, ",");
            }else {
                list = toIntList(line, "\\s+");
            }
            // 排序
            Arrays.sort(list);
            // 结果集
            List<int[]> ans = new ArrayList<>();
            for (int i = 0; i < list.length; i++) {
                int current = list[i];
                if (i > 0 && current == list[i - 1]) {
                    continue;
                }
                if (current > 0) {
                    break;
                }
                int left = i + 1, right = list.length - 1;
                while (left < right) {
                    int sum = list[left] + list[right] + current;
                    if (sum == 0) {
                        ans.add(new int[]{list[left], list[right], current});
                        while (left + 1 < list.length && list[left + 1] == list[left] ){
                            left++;
                        }
                        left++;
                        while (right - 1 > i && list[right - 1] == list[right]) {
                            right--;
                        }
                        right--;
                    } else if (sum < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            System.out.println(ans.stream().map(a -> a[0] + "," + a[1] + "," + a[2]).collect(Collectors.joining(" ")));
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
