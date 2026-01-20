package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * P00500.华为od机试—明日之星选举
 * 双机位C卷
 * difficulty:3
 * @author luqi
 */
public class P00500 {

    static class Person implements Comparable<Person> {
        String name;
        int voteNum;
        int noNum;

        public Person(String name, int voteNum, int noNum) {
            this.name = name;
            this.voteNum = voteNum;
            this.noNum = noNum;
        }
        @Override
        public int compareTo(Person other) {
            return this.voteNum != other.voteNum ? other.voteNum - this.voteNum :
                    this.noNum != other.noNum ? this.noNum - other.noNum : this.name.compareTo(other.name);
        }
    }


    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = Integer.parseInt(sc.nextLine().trim());
            Map<String, Person> personMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String trim = sc.nextLine().trim();
                String vote = "";
                String no = "";
                String[] split = trim.split(",");
                vote = split[0];
                personMap.computeIfAbsent(vote, k -> new Person(k, 0, 0)).voteNum++;
                if (trim.contains(",")) {
                    no = split[1];
                    personMap.computeIfAbsent(no, k -> new Person(k, 0, 0)).noNum++;
                }
            }
            int top = Integer.parseInt(sc.nextLine().trim());
            List<Person> personList = new ArrayList<>(personMap.values());
            Collections.sort(personList);
            System.out.println(personList.stream().limit(top).map(e->e.name).collect(Collectors.joining(",")));

        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
