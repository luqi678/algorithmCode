package com.myself.learn.algo.huawei.TE1;

import java.util.Stack;

/**
 * 53- 最大矩形
 * difficulty:困难
 * frequency:低
 * @author luqi
 */
public class TE53 {

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(new TE53().maximalRectangle(matrix));
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;

        for (int i = 0; i < rows; i++) {
            // 1. 更新当前行作为底边时的直方图高度
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            // 2. 利用单调栈计算当前直方图的最大面积
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        // 为了简化逻辑，我们在数组末尾虚拟一个高度为0的柱子
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            // 如果当前高度小于栈顶高度，说明找到了栈顶柱子的右边界
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                // 确定宽度：如果栈为空，说明左边没有比它矮的，宽为 i；
                // 否则左边界为新的栈顶索引
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
