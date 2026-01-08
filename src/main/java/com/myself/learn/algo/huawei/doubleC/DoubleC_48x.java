package com.myself.learn.algo.huawei.doubleC;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 **/
public class DoubleC_48x {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String inputN = sc.nextLine();
        int n = Integer.parseInt(inputN.trim());

        if (!sc.hasNextLine()) {
            return;
        }
        String inputX = sc.nextLine();
        int x = Integer.parseInt(inputX.trim());

        if (!sc.hasNextLine()) {
            return;
        }
        String inputY = sc.nextLine();
        int y = Integer.parseInt(inputY.trim());

        if (!sc.hasNextLine()) {
            return;
        }
        String line = sc.nextLine();
        int[] nList = Arrays.stream(line.split(","))
                .map(String::trim).mapToInt(Integer::parseInt).toArray();


    }
}