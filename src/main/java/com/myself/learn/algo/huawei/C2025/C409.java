package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * P00409.华为od机试—去除多余空格
 * 2025C卷
 * difficulty:4
 * @author luqi
 */
public class C409 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        if (!sc.hasNextLine()) {
            return;
        }
        // 原字符串
        String line = sc.nextLine();
        if (!sc.hasNextLine()) {
            return;
        }
        String digits = sc.nextLine();
        // 坐标
        List<int[]> words = new ArrayList<>();
        String[] split = digits.split(",");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            String[] word = s.trim().split("\\s+");
            words.add(new int[]{Integer.parseInt(word[0]), Integer.parseInt(word[1])});
        }
        // 处理原字符串
        boolean change = true;
        int[] newLine = new int[line.length()];
        Stack<Character> stack = new Stack<>();
        for (int i = 0,newIndex = 0; i < line.length(); i++,newIndex++) {
            char c = line.charAt(i);
            if (!change) {
                stack.push(c);
                newLine[i] = newIndex;
            }else {
                if (Character.isWhitespace(c)) {
                    while (i + 1 < line.length() && Character.isWhitespace(line.charAt(i + 1))){
                        i ++ ;
                        c = line.charAt(i);
                    }
                    stack.push(c);
                    newLine[i] = newIndex;
                }else if(c == '\''){
                    stack.push(c);
                    newLine[i] = newIndex;
                    change = !change;
                }else{
                    stack.push(c);
                    newLine[i] = newIndex;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.insert(0,stack.pop());
        }
        System.out.println(sb.toString());

        words.forEach(word -> {
            System.out.println(newLine[word[0]] + "," + newLine[word[1]]);
        });

        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }
}
