//给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的
//种类数。 
//
// 示例 1: 
//
// 
//输入: candies = [1,1,2,2,3,3]
//输出: 3
//解析: 一共有三种种类的糖果，每一种都有两个。
//     最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
// 
//
// 示例 2 : 
//
// 
//输入: candies = [1,1,2,3]
//输出: 2
//解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。
// 
//
// 注意: 
//
// 
// 数组的长度为[2, 10,000]，并且确定为偶数。 
// 数组中数字的大小在范围[-100,000, 100,000]内。
// 
// 
// 
// 
// Related Topics 数组 哈希表 
// 👍 149 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * 分糖果
 * guowm
 * 2021-11-01 12:13:24
 */
class DistributeCandies{
     public static void main(String[] args) {
         final Solution solution = new DistributeCandies().new Solution();
         int[] candyType = new int[]{1,1,2,2,3,3};
         final int ans = solution.distributeCandies(candyType);
         System.out.println(ans);
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * Time: O(n), 其中 n 是数组的长度。
     * Space: O(n)，哈希表需要 O(n) 的空间。
     * @param candyType
     * @return
     */
    public int distributeCandies(int[] candyType) {
        Set<Integer> categorySet = new HashSet<>();
        for (int type : candyType) {
            categorySet.add(type);
        }
        int maxCategorySize = candyType.length / 2;
        return Math.min(maxCategorySize,categorySet.size());
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}