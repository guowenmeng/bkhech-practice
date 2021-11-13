//珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。 
//
// 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后
//这一小时内不会再吃更多的香蕉。 
//
// 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。 
//
// 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入: piles = [3,6,7,11], H = 8
//输出: 4
// 
//
// 示例 2： 
//
// 输入: piles = [30,11,23,4,20], H = 5
//输出: 30
// 
//
// 示例 3： 
//
// 输入: piles = [30,11,23,4,20], H = 6
//输出: 23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= piles.length <= 10^4 
// piles.length <= H <= 10^9 
// 1 <= piles[i] <= 10^9 
// 
// Related Topics 数组 二分查找 
// 👍 223 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.Arrays;

/**
 * 爱吃香蕉的珂珂
 * guowm
 * 2021-11-13 17:51:04
 */
class KokoEatingBananas{
	public static void main(String[] args) {
		Solution solution = new KokoEatingBananas().new Solution();
		int[] piles = new int[] {332484035,524908576,855865114,632922376,222257295,690155293,112677673,679580077,337406589,290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184};
		int h = 823855818;
		int ans = solution.minEatingSpeed(piles, h);
		System.out.println(ans);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	/**
	 * Time: O(n) + O(logm) = O(nlogm)，其中 n 为数组的长度，m 为数组元素的最大值。
	 * Space：O(1)
	 * @param piles
	 * @param h
	 * @return
	 */
	public int minEatingSpeed(int[] piles, int h) {
		int maxK = 0;
		for (int i = 0; i < piles.length; i++) {
			maxK = Math.max(maxK, piles[i]);
		}
		int left = 1;
		int right = maxK;
		while (left < right) {
			int time = 0;
			int mid = left + (right - left) / 2;
			for (int i = 0; i < piles.length; i++) {
				time += piles[i] % mid == 0 ? piles[i] / mid : piles[i] /mid + 1;
			}
			if (time > h) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}