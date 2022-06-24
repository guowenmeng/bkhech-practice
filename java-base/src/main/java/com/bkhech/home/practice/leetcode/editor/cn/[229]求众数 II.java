//给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：[3,2,3]
//输出：[3] 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 示例 3： 
//
// 
//输入：[1,1,1,3,3,2,2,2]
//输出：[1,2] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 104 
// -109 <= nums[i] <= 109 
// 
//
// 
//
// 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。 
// Related Topics 数组 哈希表 计数 排序 
// 👍 507 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.*;

/**
 * 求众数 II
 * guowm
 * 2021-10-23 00:23:29
 */
class MajorityElementIi{
	public static void main(String[] args) {
		Solution solution = new MajorityElementIi().new Solution();
		// TODO test
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> majorityElement(int[] nums) {
    	int n = nums.length;
		int times = n / 3;
		//key:value = 元素：次数
		Map<Integer, Integer> container = new HashMap<>(n);
		for (int i = 0; i < n; i++) {
			container.compute(nums[i], (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
		}
		List<Integer> answers = new ArrayList<>();
		Set<Map.Entry<Integer, Integer>> entries = container.entrySet();
		for (Map.Entry<Integer, Integer> entry : entries) {
			if (entry.getValue() > times) {
				answers.add(entry.getKey());
			}
		}
		return answers;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}