//给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 differen
//ce 。 
//
// 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：arr = [1,2,3,4], difference = 1
//输出：4
//解释：最长的等差子序列是 [1,2,3,4]。 
//
// 示例 2： 
//
// 
//输入：arr = [1,3,5,7], difference = 1
//输出：1
//解释：最长的等差子序列是任意单个元素。
// 
//
// 示例 3： 
//
// 
//输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
//输出：4
//解释：最长的等差子序列是 [7,5,3,1]。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 105 
// -104 <= arr[i], difference <= 104 
// 
// Related Topics 数组 哈希表 动态规划 
// 👍 176 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长定差子序列
 * guowm
 * 2021-11-05 23:31:51
 */
class LongestArithmeticSubsequenceOfGivenDifference{
	public static void main(String[] args) {
		Solution solution = new LongestArithmeticSubsequenceOfGivenDifference().new Solution();
		int[] arr = {4, 2, 6, 10, 8, 12};
		int difference = 2;
		int ans = solution.longestSubsequence(arr, difference);
		System.out.println(ans);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	/**
	 * Time: O(n)，	其中 n 为输入数组的长度
	 * Space: O(n)
	 * @param arr
	 * @param difference
	 * @return
	 */
    public int longestSubsequence(int[] arr, int difference) {
		int ans = 0;
		Map<Integer, Integer> dp = new HashMap<>();
		for (int v : arr) {
			int tempLen = dp.getOrDefault(v - difference, 0);
			dp.put(v, tempLen + 1);
			ans = Math.max(ans, dp.get(v));
		}
		return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}