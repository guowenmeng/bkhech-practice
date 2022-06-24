//在 LeetCode 商店中， 有 n 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。 
//
// 给你一个整数数组 price 表示物品价格，其中 price[i] 是第 i 件物品的价格。另有一个整数数组 needs 表示购物清单，其中 needs[
//i] 是需要购买第 i 件物品的数量。 
//
// 还有一个数组 special 表示大礼包，special[i] 的长度为 n + 1 ，其中 special[i][j] 表示第 i 个大礼包中内含第 j
// 件物品的数量，且 special[i][n] （也就是数组中的最后一个整数）为第 i 个大礼包的价格。 
//
// 返回 确切 满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。任意大礼包可无限
//次购买。 
//
// 
//
// 示例 1： 
//
// 
//输入：price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
//输出：14
//解释：有 A 和 B 两种物品，价格分别为 ¥2 和 ¥5 。 
//大礼包 1 ，你可以以 ¥5 的价格购买 3A 和 0B 。 
//大礼包 2 ，你可以以 ¥10 的价格购买 1A 和 2B 。 
//需要购买 3 个 A 和 2 个 B ， 所以付 ¥10 购买 1A 和 2B（大礼包 2），以及 ¥4 购买 2A 。 
//
// 示例 2： 
//
// 
//输入：price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
//输出：11
//解释：A ，B ，C 的价格分别为 ¥2 ，¥3 ，¥4 。
//可以用 ¥4 购买 1A 和 1B ，也可以用 ¥9 购买 2A ，2B 和 1C 。 
//需要买 1A ，2B 和 1C ，所以付 ¥4 买 1A 和 1B（大礼包 1），以及 ¥3 购买 1B ， ¥4 购买 1C 。 
//不可以购买超出待购清单的物品，尽管购买大礼包 2 更加便宜。 
//
// 
//
// 提示： 
//
// 
// n == price.length 
// n == needs.length 
// 1 <= n <= 6 
// 0 <= price[i] <= 10 
// 0 <= needs[i] <= 10 
// 1 <= special.length <= 100 
// special[i].length == n + 1 
// 0 <= special[i][j] <= 50 
// 
// Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩 
// 👍 255 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.*;

/**
 * 大礼包
 * 方法：记忆化搜索（背包问题）
 * 时间复杂度：mes m^n)O(n×k×m^n)，其中 k 表示大礼包的数量，m 表示每种物品的需求量的可能情况数（等于最大需求量加 1），n 表示物品数量。
 * 我们最多需要处理 m^n个状态，每个状态需要遍历 k 种大礼包的情况，每个大礼包需要遍历 n 种商品以检查大礼包是否可以购买。
 * 空间复杂度：O(n×m^n)，用于存储记忆化搜索中 m^n个状态的计算结果，每个状态需要存储 nn 个商品的需求量。
 * guowm
 * 2021-10-24 18:27:41
 */
class ShoppingOffers{
	public static void main(String[] args) {
		List<Integer> price = Arrays.asList(2, 5);
		List<List<Integer>> special = Arrays.asList(Arrays.asList(3,0,5), Arrays.asList(1,2,10));
		List<Integer> needs = Arrays.asList(3, 2);

		Solution solution = new ShoppingOffers().new Solution();
		int ans = solution.shoppingOffers(price, special, needs);
		System.out.println(ans);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
		Map<List<Integer>, Integer> memo = new HashMap<>();
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
		//n 件商品
    	int n = price.size();
		// 过滤不需要计算的大礼包，只保留需要计算的大礼包
    	List<List<Integer>> filterSpecial = new ArrayList<>();
		for (List<Integer> sp : special) {
			int totalCount = 0, totalPrice = 0;
			for (int i = 0; i < n; i++) {
				totalCount += sp.get(i);
				totalPrice += sp.get(i) * price.get(i);
			}
			if (totalCount > 0 && totalPrice > sp.get(n)) {
				filterSpecial.add(sp);
			}
		}
		return dfs(price, special, needs, filterSpecial, n);
    }

	/**
	 * 记忆化搜索计算满足购物清单所需花费的最低价格
	 * @param price 物品及物品对应价格
	 * @param special 礼包
	 * @param curNeeds 当前购物清单
	 * @param filterSpecial 有效礼包
	 * @param n 物品数量
	 * @return
	 */
	private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> curNeeds, List<List<Integer>> filterSpecial, int n) {
		if (!memo.containsKey(curNeeds)) {
			int minPrice = 0;
			//不购买大礼包，原价购买大礼包中的所有物品
			for (int i = 0; i < n; i++) {
				minPrice += curNeeds.get(i) * price.get(i);
			}
			for (List<Integer> curSpecial : filterSpecial) {
				//大礼包的价格
				int specialPrice = curSpecial.get(n);
				//计算大礼包后新的购物清单
				List<Integer> nxtNeeds = new ArrayList<>();
				for (int i = 0; i < n; i++) {
					if (curSpecial.get(i) > curNeeds.get(i)) { //不能购买超出购物清单指定数量的物品
						break;
					}
					nxtNeeds.add(curNeeds.get(i) - curSpecial.get(i));
				}
				if (nxtNeeds.size() == n) { //大礼包可以购买（没有执行 break 语句）
					minPrice = Math.min(minPrice, dfs(price, special, nxtNeeds, filterSpecial, n) + specialPrice);
				}
			}
			memo.put(curNeeds, minPrice);
		}
		return memo.get(curNeeds);
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}