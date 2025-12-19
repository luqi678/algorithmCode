package com.myself.learn.algo.vo;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 节点
 *
 * @author 2405051
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(Integer[] values) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (Integer value : values) {
            stack.push(value);
        }
        ListNode temp = null;
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            temp = new ListNode(pop, temp);
        }
        this.val = temp.val;
        this.next = temp.next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        int limit = 15;
        while (cur != null && limit > 0) {
            sb.append(cur.val);
            sb.append("->");
            cur = cur.next;
            limit--;
        }
        return "ListNode [" + sb.toString() + "]";
    }
}