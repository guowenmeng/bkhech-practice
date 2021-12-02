//给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。 
//
// 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况： 
//
// 
// 名次第 1 的运动员获金牌 "Gold Medal" 。 
// 名次第 2 的运动员获银牌 "Silver Medal" 。 
// 名次第 3 的运动员获铜牌 "Bronze Medal" 。 
// 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。 
// 
//
// 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。 
//
// 
//
// 示例 1： 
//
// 
//输入：score = [5,4,3,2,1]
//输出：["Gold Medal","Silver Medal","Bronze Medal","4","5"]
//解释：名次为 [1st, 2nd, 3rd, 4th, 5th] 。 
//
// 示例 2： 
//
// 
//输入：score = [10,3,8,9,4]
//输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
//解释：名次为 [1st, 5th, 3rd, 2nd, 4th] 。
// 
//
// 
//
// 提示： 
//
// 
// n == score.length 
// 1 <= n <= 104 
// 0 <= score[i] <= 106 
// score 中的所有值 互不相同 
// 
// Related Topics 数组 排序 堆（优先队列） 
// 👍 154 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 相对名次
 * guowm
 * 2021-12-02 21:34:34
 */
class RelativeRanks{
	public static void main(String[] args) {
		Solution solution = new RelativeRanks().new Solution();
		int[] score = new int[]{3,1,5,2,4};
		String[] ans = solution.findRelativeRanks(score);
		System.out.println(Arrays.toString(ans));
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	/**
	 * 官方题解：
	 * Time: O(nlogn)，其中 n 为数组的长度
	 * Space: O(n)
	 * @param score
	 * @return
	 */
    public String[] findRelativeRanks(int[] score) {
    	int n = score.length;
		String[] desc = {"Gold Medal", "Silver Medal", "Bronze Medal"};
		int[][] arr = new int[n][2];
    	for (int i = 0; i < score.length; i++) {
    		arr[i][0] = score[i];
    		arr[i][1] = i;
		}
    	//逆序
		Arrays.sort(arr, (a, b) -> b[0] - a[0]);
    	String[] ans = new String[n];
		for (int i = 0; i < n; i++) {
			if (i >= 3) {
				ans[arr[i][1]] = Integer.toString(i + 1);
			} else {
				ans[arr[i][1]] = desc[i];
			}
		}
		return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

	/**
	 * my solution
	 * Time: O(nlogn)，其中 n 为数组的长度
	 * Space: O(n)
	 * @param score
	 * @return
	 */
	public String[] findRelativeRanksV1(int[] score) {
		String[] desc = {"Gold Medal", "Silver Medal", "Bronze Medal"};
		Map<Integer, String> map = new LinkedHashMap<>(score.length);
		for (int i = 0; i < score.length; i++) {
			map.put(score[i], String.valueOf(i));
		}
		int[] sortScore = quickSort(score, 0, score.length - 1);

		int rank = 0;
		for (int i = sortScore.length - 1; i >= 0 ; i--) {
			map.put(sortScore[i], rank < 3 ? desc[rank] : String.valueOf(rank + 1));
			rank++;
		}
		return map.values().toArray(new String[0]);
	}

	private int[] quickSort(int[] score, int start, int end) {
		if (start < end) {
			int pivot = score[start];
			int low = start, high = end;
			while (low < high) {
				while (low < high && pivot <= score[high]) {
					high--;
				}
				score[low] = score[high];

				while (low < high && pivot >= score[low]) {
					low++;
				}
				score[high] = score[low];
			}
			score[low] = pivot;
			quickSort(score, start, low - 1);
			quickSort(score, low + 1, end);
		}
		return score;
	}
}