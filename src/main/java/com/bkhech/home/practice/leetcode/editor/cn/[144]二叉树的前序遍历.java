//给你二叉树的根节点 root ，返回它节点值的 前序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 677 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.*;

/**
 * 二叉树的前序遍历
 * guowm
 * 2021-11-13 22:49:08
 */
class BinaryTreePreorderTraversal{
	public static void main(String[] args) {
		Solution solution = new BinaryTreePreorderTraversal().new Solution();
		//[1,null,2,3]
		TreeNode node2 = new TreeNode(3);
		TreeNode node1 = new TreeNode(2, node2, null);
		TreeNode root = new TreeNode(1, null, node1);
		List<Integer> ans = solution.preorderTraversal(root);
		System.out.println(ans);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
	/**
	 * 迭代 V2
	 * 前序遍历特点：根节点 -> 左节点 -> 右节点
	 *
	 * @param root
	 * @return
	 */
    public List<Integer> preorderTraversal(TreeNode root) {
    	List<Integer> ans = new ArrayList<>();
    	if (root == null) {
    		return ans;
		}
		Deque<TreeNode> stack = new LinkedList<>();
		stack.push(root);
    	while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			if (node == null) {
				continue;
			}
			ans.add(node.val);
			// 栈的特点是先进后出
			stack.push(node.right);
			stack.push(node.left);
		}
    	return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
	//Definition for a binary tree node.
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	/**
	 * 迭代 V1
	 * 前序遍历特点：根节点 -> 左节点 -> 右节点
	 *
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversalV2(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		Deque<TreeNode> stack = new LinkedList<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			if (node == null) {
				continue;
			}
			ans.add(node.val);
			// 栈的特点是先进后出
			stack.push(node.right);
			stack.push(node.left);
		}
		return ans;
	}

	/**
	 * 递归
	 * Time: O(n)，其中 n 为二叉树的结点数。每一次结点恰好被遍历一次。
	 * Space：O(n), 为递归过程中栈的开销，平均情况下为 O(logn)，最坏情况下树呈链状，为O(n)。即空间复杂度为 二叉树的高度
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversalV1(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		dfs(root, ans);
		return ans;
	}

	private void dfs(TreeNode root, List<Integer> ans) {
		if (root == null) {
			return;
		}
		ans.add(root.val);
		dfs(root.left, ans);
		dfs(root.right, ans);
	}
}