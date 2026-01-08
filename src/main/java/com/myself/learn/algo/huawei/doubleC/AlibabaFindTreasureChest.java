package com.myself.learn.algo.huawei.doubleC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0~N的箱子，每个箱子上面贴有一个数字。
 * 阿里巴巴念出一个咒语数字，查看宝箱是否存在两个不同箱子，这两个箱子上贴的数字相同，同时这两个箱子的编号之差的绝对值小于等于咒语数字，
 * 如果存在这样的一对宝箱，请返回最先找到的那对宝箱左边箱子的编号，如果不存在则返回-1。
 * 输入描述:
 * 第一行输入一个数字字串，数字之间使用逗号分隔，例如: 1,2,3,1字串中数字个数>=1，<=100000; 每人数字值>=-100000，<=100000:第二行输入咒语数字，例如: 3，咒语数字>=1，<=100000
 * 输出描述:
 * 存在这样的一对宝箱，请返回最先找到的那对宝箱左边箱子的编号，如果不存在则返回-1
 *
 * 示例1
 * 输入:
 * 6,3,1,6
 *
 * 3
 * 输出:
 *
 * 0
 * 示例2
 * 输入:
 * 5, 6, 7, 5, 6,7
 *
 * 2
 * 输出:
 *
 * -1
 *
 * @author 2405051*/
public class AlibabaFindTreasureChest {

    public static void main2(String[] args) {

        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String inputItems = sc.nextLine();

        String[] array = Arrays.stream(inputItems.split(",")).map(String::trim).toArray(String[]::new);
        int[] items = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            items[i] = Integer.parseInt(array[i].trim());
        }
        if (!sc.hasNextLine()) {
            return;
        }
        String inputDistance = sc.nextLine();
        int distance = Integer.parseInt(inputDistance);

        int res = -1;
        for (int i = 0; i < items.length - distance; i++) {
            int cur = items[i];
            for (int j = 0 ; j < distance; j++) {
                int r = items[i + j + 1];
                if (cur == r) {
                    res = i;
                    break;
                }
            }
            // 剪枝
            if (res != -1) {
                break;
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }

        String inputItems = sc.nextLine();
        // 健壮性处理：处理可能为空的情况
        if (inputItems == null || inputItems.trim().isEmpty()) {
            System.out.println(-1);
            return;
        }

        // 解析数组
        int[] items;
        try {
            items = Arrays.stream(inputItems.split(","))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            System.out.println(-1);
            return;
        }

        if (!sc.hasNextLine()) {
            return;
        }
        int distance = Integer.parseInt(sc.nextLine().trim());

        // 核心逻辑优化：O(N) 解法
        System.out.println(solve(items, distance));
    }

    private static int solve(int[] items, int distance) {
        // 使用哈希表存储每个数字出现的所有索引位置
        // Key: 数字值, Value: 该数字出现的所有下标列表
        Map<Integer, List<Integer>> indexMap = new HashMap<>();

        for (int i = 0; i < items.length; i++) {
            indexMap.computeIfAbsent(items[i], k -> new ArrayList<>()).add(i);
        }

        // 从左到右遍历，寻找第一个满足条件的左侧箱子
        for (int i = 0; i < items.length; i++) {
            List<Integer> indices = indexMap.get(items[i]);

            // 如果该数字只出现一次，肯定无法配对，跳过
            if (indices.size() < 2) {
                continue;
            }

            // 在列表中找到当前索引 i 的位置
            // 因为我们是顺序添加的，可以使用 Collections.binarySearch 或者直接记录遍历位置
            // 这里为了简单直观，演示逻辑：
            // 我们只需要看这个数字的"下一次"出现位置是否在距离内

            // 优化查找：由于 indices 是有序的，且我们是按顺序遍历 i
            // 我们可以利用二分查找找到 i 在 list 中的位置，或者更简单的：
            // 既然我们正在访问 i，那么 i 一定是 list 中还没被"配对过"的元素
            // 实际上，我们只需要检查：是否存在一个 idx > i 且 idx - i <= distance

            // 使用二分查找找到大于 i 的第一个索引

            //int nextIndexPos = findNextIndex(indices, i);
            //if (nextIndexPos != -1) {
            //    int nextIndex = indices.get(nextIndexPos);
            //    if (nextIndex - i <= distance) {
            //        return i; // 找到了第一个符合条件的左箱子
            //    }
            //}

            int nextIndexPos = Collections.binarySearch(indices, i) + 1;
            if (nextIndexPos < indices.size()) {
                int nextIndex = indices.get(nextIndexPos);
                if (nextIndex - i <= distance) {
                    return i; // 找到了第一个符合条件的左箱子
                }
            }
        }

        return -1;
    }

    // 辅助方法：在有序列表中寻找大于 target 的第一个元素的索引
    private static int findNextIndex(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) > target) {
                ans = mid; // 记录候选位置，尝试找更左边的
                right = mid - 1; // 实际上对于本题，我们只需要找紧邻的下一个，所以二分不是必须的，
                // 但为了通用性写在这里。对于本题 list.get(currentIndexInList + 1) 即可
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}