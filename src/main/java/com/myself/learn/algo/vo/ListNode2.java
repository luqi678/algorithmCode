package com.myself.learn.algo.vo;

// 文件名: ListNode.java

/**
 *
 */
public class ListNode2 {
    public int val;
    public ListNode next;

    // 1. LeetCode 标准构造函数
    public ListNode2() {}
    public ListNode2(int val) { this.val = val; }
    public ListNode2(int val, ListNode next) { this.val = val; this.next = next; }

    // 2. [辅助方法] 方便调试：传入数组，返回链表头节点
    // 输入: {1, 2, 3, 4, 5} -> 输出: 1->2->3->4->5
    public static ListNode create(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(-1); // 虚拟头节点
        ListNode current = dummy;
        for (int num : nums) {
            current.next = new ListNode(num);
            current = current.next;
        }
        return dummy.next;
    }

    // 3. [辅助方法] 方便调试：打印链表
    public static void print(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" -> null");
    }

    // 重写 toString 方便在 Debug 窗口直接看值
    @Override
    public String toString() {
        return "Node:" + val;
    }
}