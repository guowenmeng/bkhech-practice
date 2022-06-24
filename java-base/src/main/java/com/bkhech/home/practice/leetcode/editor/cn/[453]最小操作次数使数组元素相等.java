//给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：3
//解释：
//只需要3次操作（注意每次操作会增加两个元素的值）：
//[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,1,1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// 答案保证符合 32-bit 整数 
// 
// Related Topics 数组 数学 
// 👍 349 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.Arrays;

/**
 * 最小操作次数使数组元素相等
 *  逆向思维：每次操作使 n - 1 个元素增加 1，可以理解使 1 个元素减少 1。
 * guowm
 * 2021-10-20 19:06:08
 */
class MinimumMovesToEqualArrayElements{
     public static void main(String[] args) {
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minMoves(int[] nums) {
        int min = Arrays.stream(nums).min().getAsInt();
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += nums[i] - min;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}