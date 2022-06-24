//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性： 
//
// 
// 每行的元素从左到右升序排列。 
// 每列的元素从上到下升序排列。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 5
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 20
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= n, m <= 300 
// -109 <= matrix[i][j] <= 109 
// 每行的所有元素从左到右升序排列 
// 每列的所有元素从上到下升序排列 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 分治 矩阵 
// 👍 815 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

/**
 * 搜索二维矩阵II
 * guowm
 * 2021-10-25 18:44:28
 */
class SearchA2dMatrixIi{
     public static void main(String[] args) {
         // TODO test
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 方法一：直接查找
     * Time: O(m * n)
     * Space: O(1)
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixV1(int[][] matrix, int target) {
        int x = matrix.length;
        int y = matrix[0].length;
        for (int i = 0; i < x; i++) {
        for (int j = 0; j < y; j++) {
            if (matrix[i][j] == target) {
                return true;
            }
        }
    }
    return false;
}
}