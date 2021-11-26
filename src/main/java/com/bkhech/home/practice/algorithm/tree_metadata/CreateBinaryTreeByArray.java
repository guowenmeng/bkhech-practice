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
     * @param input
     * @return
     */
    public static TreeNode createBinaryTreeByLOTR(Integer[] input) {
        int floor = 0, count = 0;
        TreeNode[] treeNodes = new TreeNode[input.length];
        while (input != null && count < input.length) {
            int start = (int) Math.pow(2, floor) - 1;
            int end = (int) Math.pow(2, floor + 1) - 1;
            if (end > input.length) {
                end = input.length;
            }
            for (int i = start; i < end; i++) {
                if (input[i] != null) {
                    treeNodes[i] = new TreeNode(input[i]);
                } else {
                    treeNodes[i] = null;
                }
                if (i > 0) {
                    int parent = (i - 1) / 2;
                    if (parent >= 0) {
                        TreeNode pNode = treeNodes[parent];
                        if (pNode != null) {
                            if (i % 2 == 1) {
                                pNode.left = treeNodes[i];
                            } else {
                                pNode.right = treeNodes[i];
                            }
                        }
                    }
                }
                count++;
            }
            floor++;
        }
        return treeNodes[0];
    }

    /**
     * 根据先序遍历结果构建二叉树
     *  createBinaryTree By PreOrderTraversalResult(POTR)
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
