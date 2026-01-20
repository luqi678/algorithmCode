package com.myself.learn.algo.threadCode;

import com.myself.learn.algo.util.TreePrinter;
import com.myself.learn.algo.vo.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 *
 * 二叉树 测试
 * @author 2405051
 */
public class BinaryTreeTest {

    public static void main(String[] args) {
        BinaryTreeTest binaryTreeTest = new BinaryTreeTest();
        TreeNode treeNode = TreeNode.create(new Integer[]{3, 9, 20, null, null, 15, 7, 12, 2, null, 8});
        TreePrinter.printStandard(treeNode);
        // 前序遍历
        // List<Integer> integers = binaryTreeTest.preorderTraversal(treeNode);
        // 中序遍历
        // List<Integer> integers = binaryTreeTest.inorderTraversal(treeNode);
        // 后序遍历
        //List<Integer> integers = binaryTreeTest.postorderTraversal(treeNode);
        // 层序遍历
        List<List<Integer>> integers = binaryTreeTest.levelOrder(treeNode);
        System.out.println(integers);
    }


    /**
     * 前序遍历 根->左->右
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        // 递归方式
        // recursionPreorder(root, list);

        // 栈 方式
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return list;
    }

    public void recursionPreorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            recursionPreorder(root.left, list);
            recursionPreorder(root.right, list);
        }
    }


    /**
     * 中顺遍历 左->根->右
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        // 递归
        // recursionInorder(root, list);

        // 迭代-栈
        if (root == null) {
            return list;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        while (current != null) {
            stack.push(current);
            current = current.left;
        }

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                TreeNode temp = node.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
        }

        return list;
    }

    public void recursionInorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            recursionInorder(root.left, list);
            list.add(root.val);
            recursionInorder(root.right, list);
        }
    }


    /**
     * 后序遍历 左->右->根
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        // 正常迭代
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        TreeNode preIn = new TreeNode();
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right == null || node.right == preIn) {
                list.add(node.val);
                preIn = node;
            }else {
                stack.push(node);
                current = node.right;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }
        }
        // 取巧 用 根->右->左 的逆序排法，将最后结果取倒 就是 左->右->根 后序排法
//        Deque<TreeNode> stack = new ArrayDeque<>();
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            TreeNode node = stack.pop();
//            list.add(node.val);
//            if (node.left != null) {
//                stack.push(node.left);
//            }
//            if (node.right != null) {
//                stack.push(node.right);
//            }
//        }
//        Deque<Integer> deque = new ArrayDeque<>();
//        list.forEach(deque::push);
//        list.clear();
//        while (!deque.isEmpty()) {
//            list.add(deque.pop());
//        }
        return list;
    }


    /**
     * 层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 用一个队列实现
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            list.add(temp);
        }

        return list;





        // 两个栈相互转  不够优雅
//        List<List<Integer>> list = new ArrayList<>();
//        Deque<TreeNode> deque = new ArrayDeque<>();
//        Deque<TreeNode> res = new ArrayDeque<>();
//        if (root == null) {
//            return list;
//        }
//        deque.push(root);
//
//        while (!deque.isEmpty()) {
//            while (!deque.isEmpty()) {
//                TreeNode node = deque.pop();
//                res.push(node);
//            }
//            List<Integer> temp = new ArrayList<>();
//            while (!res.isEmpty()) {
//                TreeNode pop = res.pop();
//                temp.add(pop.val);
//                if (pop.left != null) {
//                    deque.push(pop.left);
//                }
//                if (pop.right != null) {
//                    deque.push(pop.right);
//                }
//
//            }
//            list.add(temp);
//        }
//        return list;
    }
}