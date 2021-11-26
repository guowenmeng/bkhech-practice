package com.bkhech.home.practice.algorithm.tree_metadata;

import java.util.*;

/**
 * 根据数组创建二叉树
 *
 * @author guowm
 * @date 2021/11/23
 */
public class CreateBinaryTreeByArray {

    public static void main(String[] args) {
        // 先序遍历结果：3, 9, null, null, 20, 15, null, null, 7
//        LinkedList<Integer> arr = new LinkedList<>(Arrays.asList(3, 9, null, null, 20, 15, null, null, 7));
//        TreeNode root = createBinaryTreeByPOTR(arr);
//        System.out.println(root);

        // 层序遍历结果：3, 9, 20, null, null, 15, 7
        Integer[] array = new Integer[]{3, 9, 20, null, null, 15, 7};
        TreeNode root2 = createBinaryTreeByLOTR(array);
        System.out.println(root2);

        List<Integer> ans = new ArrayList<>();
        levelOrderTraversalPrint(root2, ans);
        Integer[] arrAns = ans.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arrAns));
    }

    /**
     * 层序遍历二叉树
     *
     * @param root
     * @param ans
     */
    public static void levelOrderTraversalPrint(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 根据层序遍历结果构建二叉树
     * createBinaryTree By LevelOrderTraversalResult(LOTR)
     * @param nums
     * @return
     */
    public static TreeNode createBinaryTreeByLOTR(Integer[] nums) {
        TreeNode root = null;
        TreeNode point;
        Queue<TreeNode> queue = new LinkedList<>();
        if (nums.length >= 1) {
            root = new TreeNode(nums[0]);
            queue.add(root);
        }
        int i = 1;
        while (i < nums.length) {
            point = queue.poll();
            if (null != nums[i]) {
                point.left = new TreeNode(nums[i]);
                queue.add(point.left);
            }
            i++;
            if (i >= nums.length) {
                break;
            }
            if (null != nums[i]) {
                point.right = new TreeNode(nums[i]);
                queue.add(point.right);
            }
            i++;
        }
        return root;
    }

    /**
     * 根据先序遍历结果构建二叉树
     * createBinaryTree By PreOrderTraversalResult(POTR)
     *
     * @param inputList
     * @return
     */
    public static TreeNode createBinaryTreeByPOTR(LinkedList<Integer> inputList) {
        TreeNode node = null;
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }
        Integer value = inputList.removeFirst();
        if (value != null) {
            node = new TreeNode(value);
            node.left = createBinaryTreeByPOTR(inputList);
            node.right = createBinaryTreeByPOTR(inputList);
        }
        return node;
    }

}
