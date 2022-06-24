//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
// Related Topics 数组 回溯 
// 👍 1605 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.*;

/**
 * 全排列
 * guowm
 * 2021-10-28 16:36:03
 */
class Permutations{
     public static void main(String[] args) {
         final Solution solution = new Permutations().new Solution();
         int[] nums = new int[]{1,2,3};
         List<List<Integer>> answers = solution.permute(nums);
         System.out.println(answers.toString());
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
         //定义返回结果集
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 当前排列数据
        LinkedList<Integer> output = new LinkedList<>();
        //记录数组元素是否被访问
        //Map<Integer, Boolean> visited，key 为 num，value 为该数字是否被访问，false 表示该数字未被访问
        Map<Integer, Boolean> visited = new HashMap<>(nums.length);
        for (int num : nums) {
            visited.put(num, false);
        }
        backtrack(output, visited, nums);
        return ans;
    }

    /**
     * 回溯法 + 状态重置（暴力搜索的变种算法）
     * @param output 当前排列数据
     * @param visited 记录 nums[i] 处数字是否被使用
     * @param nums  原数组
     */
    private void backtrack(LinkedList<Integer> output, Map<Integer, Boolean> visited, int[] nums) {
        //所有数都填完了
        if (output.size() == nums.length) {
            //此处需要重新 new 一个 list 实现，然后进行赋值，因为 java 是值传递,在回溯过程中会影响上次结果值
            ans.add(new ArrayList<>(output));
            return;
        }
        //开始遍历数组数字
        for (int num : nums) {
            //
            if (!visited.get(num)) {
                //将当前数字添加到output中
                output.add(num);
                //更新哈希表中关于该数字的使用状态
                visited.put(num, true);
                //再次进行backtrack，以当前数字为基础进行排列
                backtrack(output, visited, nums);
                //当结束上面的backtrack时，说明基于当前数字进行的回溯已经结束
                output.removeLast();
                visited.put(num, false);
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

    //返回结果集
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permuteV2(int[] nums) {
        List<Integer> output = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(output, visited, nums);
        return ans;
    }

    /**
     * 回溯算法 + 状态标记重置
     * @param output 当前排列数据
     * @param visited 数组记录 nums[i] 处数字是否被使用
     * @param nums 原数组
     */
    private void dfs(List<Integer> output, boolean[] visited, int[] nums) {
        if (output.size() == nums.length) {
            ans.add(new ArrayList<>(output));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                output.add(nums[i]);
                visited[i] = true;
                dfs(output, visited, nums);
                output.remove(output.size() - 1);
                visited[i] = false;
            }
        }
    }

    /**
     * Time: O(n * n!)，其中 n 为序列的长度
     * Space: O(n)
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteV1(int[] nums) {
    List<Integer> output = new ArrayList<>();
    for (int num : nums) {
        output.add(num);
    }

    int n = nums.length;
    backtrackV1(n, output, 0);
    return ans;
}

    /**
     * 回溯
     *  表示从左到右填充到第 first 个位置
     * @param n 数组长度
     * @param output 当前排列数据
     * @param first 当前填充到的位置
     */
    private void backtrackV1(int n, List<Integer> output, int first) {
        //所有数都填完了
        if (first == n) {
            ans.add(new ArrayList<>(output));
        }
        for (int i = first; i < n; i++) {
            //动态维护数组
            Collections.swap(output, first, i);
            //继续递归填下一个数
            backtrackV1(n, output, first + 1);
            //撤销操作
            Collections.swap(output, first, i);
        }
    }
}