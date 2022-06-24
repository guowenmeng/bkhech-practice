//给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。 
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，po
//s 仅仅是用于标识环的情况，并不会作为参数传递到函数中。 
//
// 说明：不允许修改给定的链表。 
//
// 进阶： 
//
// 
// 你是否可以使用 O(1) 空间解决此题？ 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [3,2,0,-4], pos = 1
//输出：返回索引为 1 的链表节点
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2], pos = 0
//输出：返回索引为 0 的链表节点
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：head = [1], pos = -1
//输出：返回 null
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围在范围 [0, 104] 内 
// -105 <= Node.val <= 105 
// pos 的值为 -1 或者链表中的一个有效索引 
// 
// Related Topics 哈希表 链表 双指针 
// 👍 1050 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.HashSet;

/**
 * 如果链表有环，获取入环点
 * guowm
 * 2021-06-29 22:37:35
 */
class LinkedListCycleIi{
	public static void main(String[] args) {
		Solution solution = new LinkedListCycleIi().new Solution();
	}

	/**
	 * 使用一个HashSet作为额外的缓存，存储已经访问的节点
	 * Time O(N)
	 * Space O(n)
	 * @param head
	 * @return
	 */
	public ListNode detectCycleV2(ListNode head) {
		if (head == null) {
			return null;
		}

		HashSet<ListNode> visited = new HashSet<>();
		visited.add(head);

		ListNode currentNode = head;
		while (currentNode.next != null) {
			if (visited.contains(currentNode.next)) {
				return currentNode.next;
			}

			currentNode = currentNode.next;
			visited.add(currentNode);
		}
		return null;
	}

	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {

	/**
	 * 公式：从链表头结点到入环点的距离，等于从首次相遇点到入环点的距离
	 * Time O(n)
	 * Space O(1)
	 * @param head
	 * @return
	 */
	public ListNode detectCycle(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return pointIntoCycle(slow=head, fast);
			}
		}
		return null;
	}

	/**
	 * 获取入环点
	 * 首次相遇后，将head结点赋值给其中一个结点，另外一个指针保持在首次相遇点，
	 * 两个指针都是每次向前走一步
	 * @param slow
	 * @param fast
	 * @return
	 */
	private ListNode pointIntoCycle(ListNode slow, ListNode fast) {
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}
}


//leetcode submit region end(Prohibit modification and deletion)

}