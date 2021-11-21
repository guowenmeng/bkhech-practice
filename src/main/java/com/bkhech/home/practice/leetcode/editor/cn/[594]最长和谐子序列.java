//和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。 
//
// 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。 
//
// 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,2,2,5,2,3,7]
//输出：5
//解释：最长的和谐子序列是 [3,2,2,2,3]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,1,1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 104 
// -109 <= nums[i] <= 109 
// 
// Related Topics 数组 哈希表 排序 
// 👍 236 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 最长和谐子序列
 * guowm
 * 2021-11-20 11:47:39
 */
class LongestHarmoniousSubsequence{
	public static void main(String[] args) {
		Solution solution = new LongestHarmoniousSubsequence().new Solution();
		int ans = solution.findLHS(new int[]{1, 3, 2, 2, 5, 2, 3, 7});
		System.out.println(ans);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 枚举
     * 滑动窗口 + 双指针
     * Time: O(nlogn), 其中 n 为数组的长度。我们首先需要对数组进行排序，花费的时间复杂度是 O(nlogn)，
     * 我们需要利用双指针遍历数组花费的时间为 O(2n)，总的时间复杂度为 T(n) = O(nlogn) + O(2n) = O(nlogn)
     * Space: O(1)，需要常数个空间保存中间变量
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
//        Arrays.sort(nums);
        quickSort(nums, 0, nums.length - 1);
        int begin = 0;
        int ans = 0;
        for (int end = 0; end < nums.length; end++) {
            while (nums[end] - nums[begin] > 1) {
                begin++;
            }
            if (nums[end] - nums[begin] == 1) {
                ans = Math.max(ans, end - begin + 1);
            }
        }
        return ans;
    }
    private void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int pivot = nums[start];
            int low = start;
            int high = end;
            while (low < high) {
                while (low < high && nums[high] >= pivot) {
                    high--;
                }
                nums[low] = nums[high];
                while (low < high && nums[low] <= pivot) {
                    low++;
                }
                nums[high] = nums[low];
            }
            nums[low] = pivot;
            quickSort(nums, start, low);
            quickSort(nums, low + 1, end);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 哈希表
     * Time: O(n)，其中 n 为数组的长度。
     * Space: O(n)，其中 n 为数组的长度。数组中最多有 n 个不同元素，因此哈希表中最多存储 n 个数据
     * @param nums
     * @return
     */
    public int findLHSV1(int[] nums) {
    if (nums.length == 1) {
        return 0;
    }
    Map<Integer, Integer> cnt = new HashMap<>();
//    	for (int i = 0; i < nums.length; i++) {
//            cnt.compute(nums[i], (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
//		}
    for (Integer num : nums) {
        cnt.put(num, cnt.getOrDefault(num, 0) + 1);
    }

    int ans = 0;
//		for (int i = 0; i < nums.length; i++) {
//			if (cnt.containsKey(nums[i] + 1)) {
//				ans = Math.max(cnt.get(nums[i]) + cnt.get(nums[i] + 1), ans);
//			}
//		}
    for (Integer key : cnt.keySet()) {
        if (cnt.containsKey(key + 1)) {
            ans = Math.max(ans, cnt.get(key) + cnt.get(key + 1));
        }
    }
    return ans;
}
}