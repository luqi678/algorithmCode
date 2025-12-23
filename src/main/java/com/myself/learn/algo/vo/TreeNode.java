package com.myself.learn.algo.vo;

// 文件名: TreeNode.java
import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    // 1. LeetCode 标准构造函数
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    // ==========================================
    //  新增：可视化打印函数
    // ==========================================
    public void print() {
        print("", this, false);
    }

    /**
     * 递归打印树结构
     * @param prefix 前缀字符（用来控制缩进和线条）
     * @param node 当前节点
     * @param isLeft 是否是左子节点
     */
    private void print(String prefix, TreeNode node, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "|-- " : "\\-- ") + node.val);
            // 递归打印子节点
            // 如果是左孩子，下一层的前缀需要加 "|   "，否则加 "    "
            print(prefix + (isLeft ? "|   " : "    "), node.left, true);
            print(prefix + (isLeft ? "|   " : "    "), node.right, false);
        }
    }

    // 重写 toString，方便在 IDEA 调试窗口鼠标悬停时看值
    @Override
    public String toString() {
        return "val=" + val;
    }

    // 2. [辅助方法] 核心：根据层序数组构建二叉树
    // 输入: {3, 9, 20, null, null, 15, 7}
    // 注意：这里使用 Integer[] 而不是 int[]，因为需要识别 null
    // 修复后的构建方法
    public static TreeNode create(Integer[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(nums[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < nums.length) {
            TreeNode curr = queue.poll();

            // 1. 处理左子节点
            if (i < nums.length) {
                if (nums[i] != null) {
                    curr.left = new TreeNode(nums[i]);
                    queue.add(curr.left);
                }
                i++; // 处理完左节点，索引后移
            }

            // 2. 处理右子节点
            if (i < nums.length) {
                if (nums[i] != null) {
                    curr.right = new TreeNode(nums[i]);
                    queue.add(curr.right);
                }
                i++; // 处理完右节点，索引后移
            }
        }
        return root;
    }
}