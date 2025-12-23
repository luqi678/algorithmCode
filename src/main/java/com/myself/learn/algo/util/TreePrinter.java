package com.myself.learn.algo.util;

import com.myself.learn.algo.vo.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 2405051
 */
public class TreePrinter {

    // ============================================
    // 方案 A：标准竖向打印 (Root 在最上方)
    // 特点：最符合直觉，像教科书插图，适合层数较少(<=5层)的树
    // ============================================
    public static void printStandard(TreeNode root) {
        if (root == null) {
            System.out.println("(Empty Tree)");
            return;
        }
        int maxLevel = maxLevel(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) return;

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<TreeNode> newNodes = new ArrayList<>();
        for (TreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.val);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }
            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }
                if (nodes.get(j).left != null) System.out.print("/");
                else printWhitespaces(1);
                printWhitespaces(i + i - 1);
                if (nodes.get(j).right != null) System.out.print("\\");
                else printWhitespaces(1);
                printWhitespaces(edgeLines + edgeLines - i);
            }
            System.out.println("");
        }
        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    // ============================================
    // 方案 B：优化的目录风格 (Tree Trunk)
    // 特点：结构紧凑，利用 Unicode 连线，适合层数较深的树
    // ============================================
    public static void printDirectory(TreeNode root) {
        if (root == null) {
            System.out.println("(Empty)");
            return;
        }
        printDirectoryInternal(root, "", true);
    }

    private static void printDirectoryInternal(TreeNode node, String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.val);
        List<TreeNode> children = new ArrayList<>();
        if (node.left != null || node.right != null) {
            children.add(node.left);
            children.add(node.right);
        }
        for (int i = 0; i < children.size() - 1; i++) {
            if (children.get(i) != null) {
                printDirectoryInternal(children.get(i), prefix + (isTail ? "    " : "│   "), false);
            } else {
                // 如果左边是空的但右边有值，打印个占位符
                System.out.println(prefix + (isTail ? "    " : "│   ") + "├── (null)");
            }
        }
        if (children.size() > 0) {
            TreeNode lastChild = children.get(children.size() - 1);
            if (lastChild != null) {
                printDirectoryInternal(lastChild, prefix + (isTail ? "    " : "│   "), true);
            } else {
                System.out.println(prefix + (isTail ? "    " : "│   ") + "└── (null)");
            }
        }
    }

    // --- 辅助函数 ---
    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) System.out.print(" ");
    }
    private static int maxLevel(TreeNode node) {
        if (node == null) return 0;
        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }
    private static boolean isAllElementsNull(List list) {
        for (Object object : list) if (object != null) return false;
        return true;
    }
}