//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：[2,1]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000 
// 
//
// 
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
// 
// 
// Related Topics 递归 链表 
// 👍 1822 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import lombok.Data;

/**
 *
 * 反转链表
 * 1 -> 2 -> 3 -> 4 -> null
 * null <- 1 <- 2 <- 3 <- 4
 * guowm
 * 2021-07-05 13:54:30
 */
class ReverseLinkedList{
     public static void main(String[] args) {
         final Solution solution = new ReverseLinkedList().new Solution();
//         [1,2,3,4,5]
         ListNode head = new ListNode(1);
         ListNode listNode2 = new ListNode(2);
         ListNode listNode3 = new ListNode(3);
         ListNode listNode4 = new ListNode(4);
         ListNode listNode5 = new ListNode(5);
         head.next = listNode2;
         listNode2.next = listNode3;
         listNode3.next = listNode4;
         listNode4.next = listNode5;
         final ListNode listNode = solution.reverseList(head);
         System.out.println(listNode);
     }

     @Data
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /**
     * 迭代方法，双指针
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode prev = null; //前指针节点
        ListNode cur = head; //当前指针节点
        //每次循环，都将当前节点指向它前面的节点，然后当前节点和前节点后移
        while (cur != null) { // 边界条件：cur == null表明链表遍历完成
            //临时节点，暂存当前节点的下一节点，用于后移
            ListNode next = cur.next;
            //将当前节点指针（curr.next）指向它前面的节点
            cur.next = prev;

            //前指针（prev节点）后移一步
            prev = cur;
            //当前指针（cur节点）后移一步
            cur = next;
        }
        //最终返回的是，以prev为尾节点的链表
        return prev;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}