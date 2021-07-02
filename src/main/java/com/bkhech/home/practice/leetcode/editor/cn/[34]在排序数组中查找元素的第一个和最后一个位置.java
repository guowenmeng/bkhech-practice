//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组
// -109 <= target <= 109
// 
// Related Topics 数组 二分查找 
// 👍 1080 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * guowm
 * 2021-07-02 21:04:28
 */
class FindFirstAndLastPositionOfElementInSortedArray{
	public static void main(String[] args) {
		Solution solution = new FindFirstAndLastPositionOfElementInSortedArray().new Solution();
		int[] nums = {5,7,7,8,8,10};
		int target = 8;
		int[] searchRange = solution.searchRange(nums, target);
		Arrays.stream(searchRange).forEach(System.out::println);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 注释：第一个位置和第二个位置分开查找
     * Time O(logn)
     * Space O(1)
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
    	if (nums == null || nums.length == 0) {
    		return new int[]{-1, -1};
		}

		int leftIndex = binarySearch(nums, target, true);
		int rightIndex = binarySearch(nums, target, false);
		return new int[] {leftIndex, rightIndex};
    }

	/**
	 * @param nums
	 * @param target
	 * @param toLeft true 往左边搜索， false 往右边搜索
	 * @return
	 */
	private int binarySearch(int[] nums, int target, boolean toLeft) {
		int left = 0;
		int right = nums.length - 1;
		int ans = -1;
		while (left <= right) {
			int mid = (left + right) >>> 1;
			if (nums[mid] == target) {
				if (toLeft) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
				ans = mid;
			} else if (nums[mid] > target) {
				right = mid - 1;
			} else {
				// nums[mid] < target
				left = mid + 1;
			}
		}
		return ans;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

	/**
	 * Time O(n)
	 * Space O(1)
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] searchRangeV1(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return new int[]{-1, -1};
		}

		int currentIndex = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target) {
				nums[currentIndex] = i;
				currentIndex++;
			} else if (currentIndex > 0) {
				break;
			}
		}

		int start = -1, end = -1;
		if (currentIndex > 0) {
			start = nums[0];
			end = nums[--currentIndex];
		}

		return new int[]{start, end};
	}

}