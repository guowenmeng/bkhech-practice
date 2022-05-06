//给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组： 
//
// 
// 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。 
// 
//
// 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。 
//
// 以这种方式修改数组后，返回数组 可能的最大和 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,2,3], k = 1
//输出：5
//解释：选择下标 1 ，nums 变为 [4,-2,3] 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,-1,0,2], k = 3
//输出：6
//解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [2,-3,-1,5,-4], k = 2
//输出：13
//解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 104 
// -100 <= nums[i] <= 100 
// 1 <= k <= 104 
// 
// Related Topics 贪心 数组 排序 
// 👍 185 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.Arrays;

/**
 * K 次取反后最大化的数组和
 * guowm
 * 2021-12-03 22:37:45
 */
class MaximizeSumOfArrayAfterKNegations{
	public static void main(String[] args) {
		Solution solution = new MaximizeSumOfArrayAfterKNegations().new Solution();
		//[2,-3,-1,5,-4]
		//2
		//13
		int[] nums = new int[]{-2,4,4,4,6,3};
		int k = 1; //13
		int ans = solution.largestSumAfterKNegations(nums, k);
		System.out.println(ans);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
//		quickSort(nums, 0, nums.length);
		Arrays.sort(nums);
		int ans = 0;
		while (k < 0) {

		}

		for (int n : nums) {
			ans += n;
		}
		return ans;
    }

	private void quickSort(int[] nums, int start, int end) {
		if (start < end) {
			int pivot = nums[start];
			int low = start, high = end;
			while (low < high) {
				while (low < high && nums[high] >= pivot) {
					high--;
				}
				nums[start] = nums[high];

				while (low < high && nums[low] <= pivot) {
					low++;
				}
				nums[high] = nums[low];
			}
			nums[low] = pivot;
			quickSort(nums, start, low - 1);
			quickSort(nums, low + 1, end);
		}
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}