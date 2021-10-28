//给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。 
//
// 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：1
//输出：true
// 
//
// 示例 2： 
//
// 输入：10
//输出：false
// 
//
// 示例 3： 
//
// 输入：16
//输出：true
// 
//
// 示例 4： 
//
// 输入：24
//输出：false
// 
//
// 示例 5： 
//
// 输入：46
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= N <= 10^9 
// 
// Related Topics 数学 计数 枚举 排序 
// 👍 125 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * TODO 重新排序得到 2 的幂
 * guowm
 * 2021-10-28 20:33:25
 */
class ReorderedPowerOf2{
	public static void main(String[] args) {
		Solution solution = new ReorderedPowerOf2().new Solution();
		boolean ans = solution.reorderedPowerOf2(2014);
		System.out.println(ans);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	Set<String> powerOf2Digits = new HashSet<>();

	public boolean reorderedPowerOf2(int n) {
		init();
		return powerOf2Digits.contains(countDigits(n));
	}

	public void init() {
		for (int n = 1; n <= 1e9; n <<= 1) {
			powerOf2Digits.add(countDigits(n));
		}
	}

	public String countDigits(int n) {
		char[] cnt = new char[10];
		while (n > 0) {
			++cnt[n % 10];
			n /= 10;
		}
		return new String(cnt);
	}

}
//leetcode submit region end(Prohibit modification and deletion)

	boolean[] visited;

	public boolean reorderedPowerOf2V1(int n) {
		char[] nums = Integer.toString(n).toCharArray();
		Arrays.sort(nums);
		visited = new boolean[nums.length];
		return backtrack(nums, 0, 0);
	}

	private boolean backtrack(char[] nums, int index, int num) {
		if (index == nums.length) {
			return isPowOfTwo(num);
		}
		for (int i = 0; i < nums.length; i++) {
			//不能有前导 0
			if ((num == 0 && nums[i] == '0')
					|| visited[i]
					|| (i > 0 && !visited[i - 1] && nums[i] == nums[i - 1])
			) {
				continue;
			}
			visited[i] = true;
			if (backtrack(nums, index + 1, num * 10 + nums[i] - '0')) {
				return true;
			}
			visited[i] = false;
		}
		return false;
	}

	private boolean isPowOfTwo(int num) {
		return (num & (num - 1)) == 0;
	}
}