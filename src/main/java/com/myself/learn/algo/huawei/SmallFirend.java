package com.myself.learn.algo.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 小朋友高矮排序
 * @author 2405051
 */
public class SmallFirend {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] in = s.split(" ");
        List<Integer> list = new ArrayList<>(){};
        for (int i = 0; i < in.length; i++) {
            int number = Integer.parseInt(in[i]);
            list.add(number);
            System.out.println(number);
        }

        SmallFirend sf = new SmallFirend();
        List<Integer> integers = sf.hightLow(list);
        // 算法
        for (int i = 0; i < list.size(); i++) {




        }




        System.out.println(integers.stream().map(Object::toString).collect(Collectors.joining(" ")));

    }


    public List<Integer> hightLow(List<Integer> list) {




        return null;
    }



}