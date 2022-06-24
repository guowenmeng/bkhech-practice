//给定一个二叉树，找出其最大深度。 
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 1042 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import com.bkhech.home.practice.algorithm.tree_metadata.CreateBinaryTreeByArray;
import com.bkhech.home.practice.algorithm.tree_metadata.TreeNode;

/**
 * 二叉树的最大深度
 * guowm
 * 2021-11-23 16:16:41
 */
class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        final Solution solution = new MaximumDepthOfBinaryTree().new Solution();
        Integer[] input = new Integer[]{3, 9, 20, null, null, 15, 7};
        final TreeNode root = CreateBinaryTreeByArray.createBinaryTreeByLOTR(input);
        final int ans = solution.maxDepth(root);
        System.out.println(ans);
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}