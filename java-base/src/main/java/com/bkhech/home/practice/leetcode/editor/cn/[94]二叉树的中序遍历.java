//给定一个二叉树的根节点 root ，返回它的 中序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
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
//输出：[2,1]
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
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 1156 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.*;

/**
 * 二叉树的中序遍历
 * guowm
 * 2021-11-09 21:12:27
 */
class BinaryTreeInorderTraversal{
	public static void main(String[] args) {
		Solution solution = new BinaryTreeInorderTraversal().new Solution();
//		[1,null,2,3]
		TreeNode node2 = new TreeNode(3);
		TreeNode node1 = new TreeNode(2, node2, null);
		TreeNode root = new TreeNode(1, null, node1);
		List<Integer> ans = solution.inorderTraversal(root);
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
	 * 方法二：迭代
	 * 中序遍历特点：左节点 -> 根节点 -> 右节点
	 * Time: O(n)，其中 n 为二叉树结点的个数。二叉树的遍历中每个结点会被访问一次且只会被访问一次。
	 * Space: O(n), 空间复杂度取决于递归的栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 级别。
	 * @param root
	 * @return
	 */
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> ans = new ArrayList<>();
		Deque<TreeNode> stack = new LinkedList<>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			ans.add(root.val);
			root = root.right;
		}
    	return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
	/**
	 * 方法一：递归
	 * Time: O(n)，其中 n 为二叉树结点的个数。二叉树的遍历中每个结点会被访问一次且只会被访问一次。
	 * Space: O(n), 空间复杂度取决于递归的栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 级别。
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversalV1(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		inorder(root, ans);
		return ans;
	}

	private void inorder(TreeNode root, List<Integer> ans) {
		if (root == null) {
			return;
		}
		inorder(root.left, ans);
		ans.add(root.val);
		inorder(root.right, ans);
	}
	/**
	 * 	Definition for a binary tree node.
	 */
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
}