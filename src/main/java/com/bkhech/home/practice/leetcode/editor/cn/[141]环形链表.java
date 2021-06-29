//给定一个链表，判断链表中是否有环。 
//
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的
//位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。 
//
// 如果链表中存在环，则返回 true 。 否则，返回 false 。 
//
// 
//
// 进阶： 
//
// 你能用 O(1)（即，常量）内存解决此问题吗？ 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [3,2,0,-4], pos = 1
//输出：true
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 输入：head = [1,2], pos = 0
//输出：true
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 输入：head = [1], pos = -1
//输出：false
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 104] 
// -105 <= Node.val <= 105 
// pos 为 -1 或者链表中的一个 有效索引 。 
// 
// Related Topics 哈希表 链表 双指针 
// 👍 1106 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.HashSet;

/**
 * 判断一个链表中是否有环
 * guowm
 * 2021-06-29 21:33:47
 */
class LinkedListCycle{
	public static void main(String[] args) {
		Solution solution = new LinkedListCycle().new Solution();
//		[3,2,0,-4]
		ListNode root = new ListNode(3);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(0);
		ListNode node4 = new ListNode(-4);
		root.next = node2;
		node2.next = node3;
		node3.next = node4;

		boolean hasCycle = solution.hasCycle(root);
		System.out.println(hasCycle);
	}

	/**
	 * 求环长
	 * 思路：第一次相遇时，继续前进，统计循环次数，直到再次相遇，循环次数即为环长。
	 * slow每次一步，fast每次两步，速度差是1步。当slow和fast再次相遇时，fast比slow多走了一圈
	 * @param head
	 * @return
	 */
	public int getCycleLength(ListNode head) {
		int len = 0;
		int meetCount = 0;
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (meetCount == 1) {
				len++;
			}
			if (slow == fast) {
				meetCount++;
				if (meetCount == 2) {
					return len;
				}
			}
		}
		return -1;
	}

	/**
	 * 使用一个HashSet作为额外的缓存，存储已经访问的节点
	 * Time O(N)
	 * Space O(n)
	 * @param head
	 * @return
	 */
	public boolean hasCycleV1(ListNode head) {
		if (head == null) {
			return false;
		}

		HashSet<ListNode> visited = new HashSet<>();
		visited.add(head);

		ListNode currentNode = head;
		while (currentNode.next != null) {
			if (visited.contains(currentNode.next)) {
				return true;
			}

			currentNode = currentNode.next;
			visited.add(currentNode);
		}
		return false;
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
	 * 双指针
	 * 快慢指针
	 * Time O(n)
	 * Space O(1)
	 * @param head
	 * @return
	 */
	public boolean hasCycle(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

}
//leetcode submit region end(Prohibit modification and deletion)

}