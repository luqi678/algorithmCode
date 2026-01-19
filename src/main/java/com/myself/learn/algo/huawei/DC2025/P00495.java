package com.myself.learn.algo.huawei.DC2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * P00495.华为od机试—统计射击比赛成绩
 * 双机位C卷
 * difficulty:3
 * @author luqi
 */
public class P00495 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);

    static class Person implements Comparable<Person> {
        int id;
        long score;
        Person(int id, long score) {
            this.id = id;
            this.score = score;
        }
        @Override
        public int compareTo(Person other) {
            if (this.score == other.score) {
                return other.id - this.id;
            }
            return (int) (other.score - this.score);

        }
    }


    public static void main(String[] args) {

        if (sc.hasNext()) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            List<Person> persons = new ArrayList<>();
            int n = sc.nextInt();
            sc.nextLine();
            int[] ids = Arrays.stream(sc.nextLine().trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            int[] scores = Arrays.stream(sc.nextLine().trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            for (int i = 0; i < n; i++) {
                int id = ids[i];
                int score = scores[i];
                map.computeIfAbsent(id, k -> new ArrayList<>()).add(score);
            }
            map.forEach((k, v) -> {
                if (v.size() >= 3) {
                    long sum = v.stream().sorted((a, b) -> {
                        return b - a;
                    }).limit(3).mapToLong(Long::valueOf).sum();
                    persons.add(new Person(k, sum));
                }
            });
            Collections.sort(persons);
            String result = persons.stream().map(e->e.id).map(String::valueOf).collect(Collectors.joining(","));
            System.out.println(result);
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
