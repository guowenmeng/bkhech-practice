//给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。 
//
// 例如， 
//
// 
//给定二叉搜索树:
//
//        4
//       / \
//      2   7
//     / \
//    1   3
//
//和值: 2
// 
//
// 你应该返回如下子树: 
//
// 
//      2     
//     / \   
//    1   3
// 
//
// 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。 
// Related Topics 树 二叉搜索树 二叉树 
// 👍 205 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import com.bkhech.home.practice.algorithm.tree_metadata.TreeNode;

/**
 * 二叉搜索树中的搜索
 * guowm
 * 2021-11-26 15:46:11
 */
class SearchInABinarySearchTree{
     public static void main(String[] args) {
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
     * bfs
     * 利用二叉搜索树的特性
     * Time: O(N)，N 为二叉搜索树的结点数，最坏情况，二叉搜索树为链状，为 O(N), 平均情况下，与节点数的对数正相关，为 O(logN)
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
//        if (root == null) {
//            return null;
//        }
//        // Space: O(H)，H 为树的高度
//        Queue<TreeNode> nodeQueue = new LinkedList<>();
//        nodeQueue.offer(root);
//        while (!nodeQueue.isEmpty()) {
//            TreeNode node = nodeQueue.poll();
//            if (node.val == val) {
//                return node;
//            } else if (node.val > val && node.left != null) {
//                nodeQueue.offer(node.left);
//            } else if (node.val < val && node.right != null){
//                nodeQueue.offer(node.right);
//            }
//        }
//        return null;

        // Space: O(1)，没有使用额外的空间
        while (root != null) {
            if (root.val == val) {
                return root;
            }
            if (root.val > val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 递归
     * 利用二叉搜索树的特性
     * Time: O(n)，其中 n 是二叉搜索树的结点数。最坏情况下二叉搜索树是一条链，且要找的元素比链末尾的元素值还要大或者小，这种情况下需要递归 n 次
     * Space: O(n)，最坏情况下递归需要 O(n) 的栈空间
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBSTV1(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else if (root.val > val) {
            return searchBSTV1(root.left, val);
        } else {
            return searchBSTV1(root.right, val);
        }
    }

}