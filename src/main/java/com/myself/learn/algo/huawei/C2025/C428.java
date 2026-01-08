package com.myself.learn.algo.huawei.C2025;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * P00428.华为od机试—优秀学员统计
 * 2025C卷
 * difficulty:3
 * @author luqi
 */
public class C428 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);

    static public class Student {
        public int id;
        public int firstDay;
        public int nums;
        public Student(int id, int firstDay, int nums) {
            this.id = id;
            this.firstDay = firstDay;
            this.nums = nums;
        }
    }

    public static void main(String[] args) {

        if (sc.hasNext()) {
            int n = sc.nextInt();
            int[] nums = new int[30];
            for (int i = 0; i < 30; i++) {
                nums[i] = sc.nextInt();
            }
            Student[] students = new Student[n];
            for (int i = 0; i < students.length; i++) {
                students[i] = new Student(i, 31, 0);
            }
            for (int day = 0; day < nums.length; day++) {
                int num = nums[day];
                for (int i = 0; i < num; i++) {
                    int id = sc.nextInt();
                    if (students[id].firstDay == 31) {
                        students[id].firstDay = day;
                    }
                    students[id].nums++;
                }
            }
            // 排序
            Arrays.sort(students, (a, b) -> {
                if (a.nums == b.nums){
                    if (a.firstDay == b.firstDay) {
                        return a.id - b.id;
                    }
                    return a.firstDay - b.firstDay;
                }
                return b.nums - a.nums;
            });
            printArray(Arrays.stream(students).map(e->e.id).map(String::valueOf).limit(5).collect(Collectors.toList()));
        }
        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }

    public static void printArray(List<String> list) {
        if (list.isEmpty()) {
            System.out.println("null");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}
