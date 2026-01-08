package com.myself.learn.algo.huawei.doubleC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 分解正整数
 **/
public class DoubleC_487 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String inputN = sc.nextLine();
        long n = Long.parseLong(inputN.trim());
        String res = "N";

        for (int m = 2; m * m < 2 * n; m++) {
            if (m%2==0) {
                if ((n - m / 2) % m == 0) {
                    long center = (n - m / 2)/m;
                    long first = center - (m/2 - 1);
                    List<Long> list = new ArrayList<>();
                    for (int i = 0; i < m; i++) {
                        list.add(first + i);
                    }
                    res = n +
                            "=" +
                            list.stream().map(String::valueOf).collect(Collectors.joining("+"));
                    break;
                }
            }else {
                if (n%m == 0) {
                    long center = n/m;
                    long first = center - (m-1)/2 ;
                    List<Long> list = new ArrayList<>();
                    for (int i = 0; i < m; i++) {
                        list.add(first + i);
                    }
                    res = n +
                            "=" +
                            list.stream().map(String::valueOf).collect(Collectors.joining("+"));
                    break;
                }
            }
        }

        System.out.println(res);
    }
}