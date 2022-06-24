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
    /**
     * 递归
     * Time: O(n)，其中 n 是链表的长度。需要遍历一次链表
     * Space: O(n), 其中 n 是链表的长度。空间复杂度取决于递归调用栈。最多不会超过 n 层。
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElementsRecursion(ListNode head, int val) {
        //入栈
        //递归的终止条件是 head 为空
        if (head == null) {
            return null;
        }
        head.next = removeElementsRecursion(head.next, val);
        //出栈
        return head.val == val ? head.next : head;
    }

    /**
     * 迭代
     *    巧妙使用虚拟结点，解决要删除结点时一个的问题：
     *    由于链表的头结点 head 有可能需要被删除，因此创建虚拟结点 dumpHead，
     *    令 dumpHead.next = head，初始化 temp = dumpHead，然后遍历链表进行删除。
     *    最终返回 dumpHead.next 即为删除操作后的头结点。
     * Time: O(n)，其中 n 是链表的长度。需要遍历一次链表
     * Space: O(1)
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        //创建虚拟结点
        ListNode dumpHead = new ListNode(0);
        //将头结点挂到虚拟结点下
        dumpHead.next = head;
        ListNode ansNode = dumpHead;
        // 遍历链表，删除链表值等于 val 的结点
        while (ansNode.next != null) {
            if (ansNode.next.val == val) {
                ansNode.next = ansNode.next.next;
            } else {
                ansNode = ansNode.next;
            }
        }
        return dumpHead.next;
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

    /**
     * 迭代
     * Time: O(n)，其中 n 是链表的长度
     * Space: O(1)
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElementsV1(ListNode head, int val) {
        ListNode ansNode = head;
        // 若头结点为空，直接返回
        if (ansNode == null) {
            return null;
        }
        // 遍历链表，删除链表值等于 val 的结点
        while (ansNode.next != null) {
            if (ansNode.next.val == val) {
                ansNode.next = ansNode.next.next;
            } else {
                ansNode = ansNode.next;
            }
        }
        // 是否需要删除头结点，若头结点的值等于 val ，则删除
        if (head.val == val) {
            if (head.next != null) {
                //由于头结点没有前一个结点，把自己伪装成下一个节点（技巧）
                //注意：要删除当前结点，当不知道当前结点的上一个结点时，可以使用此技巧
                head.val = head.next.val;
                head.next = head.next.next;
            } else {
                return null;
            }
        }
        return head;
    }

}