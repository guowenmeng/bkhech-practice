//给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和
// targetSum 。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,3], targetSum = 5
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 724 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import com.bkhech.home.practice.algorithm.tree_metadata.CreateBinaryTreeByArray;
import com.bkhech.home.practice.algorithm.tree_metadata.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 路径总和
 * guowm
 * 2021-11-26 11:05:07
 */
class PathSum{
     public static void main(String[] args) {
         final Solution solution = new PathSum().new Solution();
         //root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
         Integer[] nums = new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,1};
         final TreeNode root = CreateBinaryTreeByArray.createBinaryTreeByLOTR(nums);
         int targetNum = 22;
//         Integer[] nums = new Integer[]{1,2,null,3,null,4,null,5};
//         final TreeNode root = CreateBinaryTreeByArray.createBinaryTreeByLOTR(nums);
//         int targetNum = 6;
         final boolean ans = solution.hasPathSum(root, targetNum);
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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        //存储当前结点
        Queue<TreeNode> queueNode = new LinkedList<>();
        //存储遍历到对应结点时，从根节点到当前结点值得和
        Queue<Integer> queueVal = new LinkedList<>();
        queueNode.offer(root);
        queueVal.offer(root.val);
        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            int temp = queueVal.poll();
            if (node.left == null && node.right == null) {
                if (temp == targetSum) {
                    return true;
                }
                continue;
            }
            if (node.left != null) {
                queueNode.offer(node.left);
                queueVal.offer(temp + node.left.val);
            }
            if (node.right != null) {
                queueNode.offer(node.right);
                queueVal.offer(temp + node.right.val);
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * dfs
     * Time: O(n)，其中 n 为树的结点数量，每个结点需要遍历一次。
     * Space: O(H), 其中 n 为树的高度，主要取决于递归时栈空间的开销，最坏情况下，树呈现链状，为 O(n),
     *      平均情况下输的高度与结点数的对数正相关，为 O(logn)。
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSumV2(TreeNode root, int targetSum) {
        return dfs(root,targetSum - 0);
    }

    private boolean dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        boolean ans = dfs(root.left, targetSum - root.val);
        if (!ans) {
            ans =dfs(root.right, targetSum - root.val);
        }
        return ans;
    }
    /**
     * dfs
     * Time: O(n)，其中 n 为树的结点数量
     * Space：O(H)，其中 H 为树的高度 ？？？
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSumV1(TreeNode root, int targetSum) {
        List<Integer> values = new ArrayList<>();
        return dfs(root,targetSum, values, new AtomicBoolean());
    }

    private boolean dfs(TreeNode root, int targetSum, List<Integer> values, AtomicBoolean ans) {
        if (root == null) {
            if (values.isEmpty()) {
                return false;
            }
            int sum = values.stream().reduce(0, Integer::sum);
            return sum == targetSum ;
        }
        values.add(root.val);
        boolean leftRet = dfs(root.left, targetSum, values, ans);
        if (ans.get()) {
            return true;
        }
        if (leftRet && root.left == null && root.right == null) {
            ans.set(true);
            return true;
        }
        boolean rightRet = dfs(root.right, targetSum, values, ans);
        if (ans.get()) {
            return true;
        }
        if (rightRet && root.left == null && root.right == null) {
            ans.set(true);
            return true;
        }
        values.remove(values.size() - 1);
        return false;
    }

}