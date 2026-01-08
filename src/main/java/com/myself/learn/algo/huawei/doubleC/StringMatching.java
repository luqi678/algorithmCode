package com.myself.learn.algo.huawei.doubleC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * P00494. 华为od机试—字符串计数匹配
 * 给你一个字符串str和整数k，返回满足以下条件的所有子字符串个数:
 * 1.恰好包含k个字母。
 * 2.数字0-9各出现至少一次。
 *
 * 输入描述
 * 第一行字符串str (1≤ str.length≤ 100000),仅包含数字和小写字母
 * 第二行为整数k(0≤k≤100000)
 *
 * 输出描述
 * 输出一个整数，表示满足所有条件的子字符串的个数，
 * 子字符串是字符串中连续的非空字符序列
 * 示例1
 * 输入：
 * a0123456789aa
 * 1
 * 输出：
 * 2
 *
 *
 * @author 2405051*/
public class StringMatching {
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String str = sc.nextLine();
        if (!sc.hasNextLine()) {
            return;
        }
        String inputNumber = sc.nextLine();
        int k = Integer.parseInt(inputNumber.trim());

        long length = str.length();
        int left = 0,right = 0;
        // 数字出现个数
        int[] digitCounts = new int[10];
        // 不同数字个数
        int uniqueDigitsCount =  0;
        // 字母索引号
        List<Integer> letterIndex = new ArrayList<Integer>();
        // 结果数量
        int res = 0;

        // 找出满足全数字的左右界点
        for (int r = 0; r < length; r++) {
            char cur = str.charAt(r);
            if (cur >= '0' && cur <= '9') {
                if (digitCounts[cur - '0'] == 0) {
                    uniqueDigitsCount++;
                }
                digitCounts[cur - '0']++;
            }else {
                letterIndex.add(r);
            }

            if (uniqueDigitsCount < 10) {
                continue;
            }
            // left 指针确定
            while (left < r) {
                char leftChar = str.charAt(left);
                if (leftChar >= '0' && leftChar <= '9') {
                    if (digitCounts[leftChar - '0'] > 1) {
                        digitCounts[leftChar - '0']--;
                        left++;
                    }else {
                        break;
                    }
                }else {
                    left++;
                }
            }
            // 开始k个字母的确认 确定字母的左右边界
            int leftLetter = 0, rightLetter = 0;

            if (k == 0){
                if (letterIndex.size()==0) {
                    leftLetter = 0;
                }else {
                    leftLetter = letterIndex.get(letterIndex.size()-1);
                }
                rightLetter = left;
            }else if ( k > 0){
                rightLetter = letterIndex.get(letterIndex.size()-k);
                if (letterIndex.size() == k){
                    leftLetter = 0;
                }else {
                    leftLetter = letterIndex.get(letterIndex.size()-k-1) +1;
                }
            }

            // 计算合集
            int low = leftLetter, high = Math.min(rightLetter, left);
            if (low <= high) {
                res = res + (high - low + 1);
            }
        }
        System.out.println(res);
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String str = sc.nextLine();
        if (!sc.hasNextLine()) {
            return;
        }
        String inputNumber = sc.nextLine();
        int k = Integer.parseInt(inputNumber.trim());

        long length = str.length();
        int left = 0; // 数字条件的最右合法左边界

        int[] digitCounts = new int[10];
        int uniqueDigitsCount = 0;
        List<Integer> letterIndex = new ArrayList<>();

        // [修改点1] 结果必须用 long，防止 10万长度时溢出 int
        long res = 0;

        for (int r = 0; r < length; r++) {
            char cur = str.charAt(r);
            if (cur >= '0' && cur <= '9') {
                if (digitCounts[cur - '0'] == 0) {
                    uniqueDigitsCount++;
                }
                digitCounts[cur - '0']++;
            } else {
                letterIndex.add(r);
            }

            if (uniqueDigitsCount < 10) {
                continue;
            }

            // left 指针确定 (维护满足数字条件的区间 [0, left])
            while (left <= r) { // 这里的 <= r 更严谨，虽然 < r 在大多数情况也能跑
                char leftChar = str.charAt(left);
                // [修改点2] 必须包含 '0' 和 '9'，原来的写法漏掉了
                if (leftChar >= '0' && leftChar <= '9') {
                    if (digitCounts[leftChar - '0'] > 1) {
                        digitCounts[leftChar - '0']--;
                        left++;
                    } else {
                        break; // 这个数字只剩一个了，不能删，停止
                    }
                } else {
                    left++; // 字母可以直接删掉，不影响数字完整性
                }
            }

            // 开始k个字母的确认 确定字母的左右边界
            // letterStartRangeLower: 满足字母条件的左起点下限
            // letterStartRangeUpper: 满足字母条件的左起点上限
            int letterStartRangeLower = 0;
            int letterStartRangeUpper = 0;

            if (k == 0) {
                // [修改点3] 处理 k=0 且没有字母的情况，防止越界
                if (letterIndex.isEmpty()) {
                    letterStartRangeLower = 0;
                } else {
                    // 必须在最后一个字母之后
                    letterStartRangeLower = letterIndex.get(letterIndex.size() - 1) + 1;
                }
                // 如果 k=0，左起点只要在最后一个字母后面，一直到当前 r 都是满足“0个字母”的
                letterStartRangeUpper = r;
            } else {
                // [修改点4] 先判断字母够不够 k 个，不够直接跳过
                if (letterIndex.size() < k) {
                    continue;
                }

                // 必须包含倒数第 k 个字母 -> 起点最大只能是该字母的位置
                letterStartRangeUpper = letterIndex.get(letterIndex.size() - k);

                if (letterIndex.size() == k) {
                    letterStartRangeLower = 0;
                } else {
                    // 必须排除倒数第 k+1 个字母 -> 起点必须在该字母后面
                    letterStartRangeLower = letterIndex.get(letterIndex.size() - k - 1) + 1;
                }
            }

            // 计算交集
            // 数字允许的左起点范围: [0, left]
            // 字母允许的左起点范围: [letterStartRangeLower, letterStartRangeUpper]

            int high = Math.min(letterStartRangeUpper, left);
            int low = letterStartRangeLower;

            if (low <= high) {
                res = res + (high - low + 1);
            }
        }

        System.out.println(res);
    }
}