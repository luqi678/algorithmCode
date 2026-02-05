package com.myself.learn.algo.huawei.TE1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 71- 合并区间
 * difficulty:中等
 * frequency:中
 * @author luqi
 */
public class TE71 {

    public static List<List<Integer>> merge(List<List<Integer>> intervals) {
        List<List<Integer>> res = new ArrayList<>();
        intervals.sort((a, b) -> a.get(0) - b.get(0));
        for (int i = 0; i < intervals.size(); i++) {
            List<Integer> interval = intervals.get(i);
            int start = interval.get(0);
            int end = interval.get(1);
            while (i+1 < intervals.size() && intervals.get(i+1).get(0) <= end) {
                end = Math.max(end, intervals.get(i+1).get(1));
                i++;
            }
            res.add(Arrays.asList(start, end));
        }
        return res;
    }

    public static void main(String[] args) {
        // [[1,3],[2,6],[8,10],[15,18]] 构建
        List<List<Integer>> list = Arrays.asList(
                Arrays.asList(1, 3),
                Arrays.asList(2, 6),
                Arrays.asList(8, 10),
                Arrays.asList(15, 18)
        );

        System.out.println(merge(list));
    }
}
