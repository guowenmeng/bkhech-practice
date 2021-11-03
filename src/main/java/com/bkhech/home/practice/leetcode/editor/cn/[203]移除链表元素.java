//给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,6,3,4,5,6], val = 6
//输出：[1,2,3,4,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [], val = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [7,7,7,7], val = 7
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 列表中的节点数目在范围 [0, 104] 内 
// 1 <= Node.val <= 50 
// 0 <= val <= 50 
// 
// Related Topics 递归 链表 
// 👍 728 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

/**
 * 移除链表元素
 * guowm
 * 2021-11-03 09:23:41
 */
class RemoveLinkedListElements{
     public static void main(String[] args) {
         final Solution solution = new RemoveLinkedListElements().new Solution();
         //[1,2,6,3,4,5,6]
         //6
         final int[] dataArr = {1, 2, 6, 3, 4, 5, 6};
         int n = dataArr.length;
         ListNode tailNode = new ListNode(dataArr[n - 1]);
         ListNode headNode = tailNode;
         for (int i = n - 2; i >= 0; i--) {
             headNode = new ListNode(dataArr[i], headNode);
         }

         final ListNode ans = solution.removeElements(headNode, 6);
         System.out.println(ans);
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
    public ListNode removeElements(ListNode head, int val) {
        ListNode ansNode = head;
        if (ansNode == null) {
            return null;
        }
        while (ansNode.next != null) {
            if (ansNode.next.val == val) {
                ansNode.next = ansNode.next.next;
            } else {
                ansNode = ansNode.next;
            }
        }
        if (head.val == val) {
            if (head.next != null) {
                head.val = head.next.val;
                head.next = head.next.next;
            } else {
                return null;
            }
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}