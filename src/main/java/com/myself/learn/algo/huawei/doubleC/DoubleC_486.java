package com.myself.learn.algo.huawei.doubleC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * P00486. 华为od机试—幸存数之和
 **/
public class DoubleC_486 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextLine()) {
            return;
        }
        String inputNList = sc.nextLine();
        List<Integer> nums = new ArrayList<>(Arrays.stream(inputNList.split(",")).map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList()));

        if (!sc.hasNextLine()) {
            return;
        }
        String inputN = sc.nextLine();
        int jump = Integer.parseInt(inputN.trim());

        if (!sc.hasNextLine()) {
            return;
        }
        String inputX = sc.nextLine();
        int left = Integer.parseInt(inputX.trim());

        int currentIndex = 0;
        while (nums.size() > left) {
            int removeIndex = (currentIndex + jump + 1) % nums.size();
            nums.remove(removeIndex);
            currentIndex = removeIndex - 1;
        }
        System.out.println(nums.stream().reduce(0, Integer::sum));

    }
}