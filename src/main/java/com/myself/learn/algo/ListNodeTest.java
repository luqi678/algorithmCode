package com.myself.learn.algo;


import com.myself.learn.algo.vo.ListNode;

/**
 * 链表测试
 *
 * @author 2405051
 */
public class ListNodeTest {

    public static void main(String[] args) {
        ListNodeTest listNodeTest = new ListNodeTest();
        ListNode param = new ListNode(new Integer[]{1,2,3,4,5,6,7,8});
        // System.out.println(listNodeTest.reverseList(param));
        // System.out.println(listNodeTest.reverseBetween(param,1,4));
        System.out.println(listNodeTest.reverseKGroup(param,3));
    }



    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = new ListNode();
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }


    /**
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode start = null,end = null,current = head,prev = null;
        int count = 1;
        while (current != null) {
            ListNode next = current.next;
            if (left <= count && count <= right) {
                if (left == count) {
                    // 确定start
                    start = prev;
                    end = current;
                }
                // 反转
                current.next = count == left ? null : prev;
                if (right == count) {
                    if (start == null) {
                        head = current;
                    }else {
                        start.next = current;
                    }
                    end.next = next;
                }
                // 下推
                prev = current;
                current = next;
                count++;
            }else {
                // 正常下推
                prev = current;
                current = next;
                count++;
            }
        }
        return head;

    }


    /**
     * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head,prev = null;
        ListNode begin = null,end = null;
        int t = 1,times=0;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        int s = length - length % k;
        cur = head;
        while (cur != null && s > times) {
            ListNode next = cur.next;
            if (t == 1) {
                cur.next = null;
                begin = cur;
            }else if(t == k){
                cur.next = prev;
                begin.next = next;
                if(end == null){
                    head = cur;
                }else {
                    end.next = cur;
                }
                end = begin;
                t = 0;
            }else {
                cur.next = prev;
            }
            t++;
            times++;
            prev = cur;
            cur = next;
        }
        return head;
    }
}