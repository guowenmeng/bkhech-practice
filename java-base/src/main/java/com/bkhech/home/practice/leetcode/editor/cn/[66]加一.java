//给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = [1,2,3]
//输出：[1,2,4]
//解释：输入数组表示数字 123。
// 
//
// 示例 2： 
//
// 
//输入：digits = [4,3,2,1]
//输出：[4,3,2,2]
//解释：输入数组表示数字 4321。
// 
//
// 示例 3： 
//
// 
//输入：digits = [0]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= digits.length <= 100 
// 0 <= digits[i] <= 9 
// 
// Related Topics 数组 数学 
// 👍 789 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.Arrays;

/**
 * 加一
 * guowm
 * 2021-10-21 09:38:32
 */
class PlusOne{
     public static void main(String[] args) {
         final Solution solution = new PlusOne().new Solution();
         int[] digits = new int[]{1, 2, 3};
         final int[] answers = solution.plusOne(digits);
         System.out.println(Arrays.toString(answers));
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        //进位
        int carry = 1;
        for (int i = n - 1; i >= 0; i--) {
            int value = digits[i] + carry;
            carry = value/10;
            digits[i] = value%10;
        }

        if (carry == 0) {
            return digits;
        }

        int[] d = new int[n + 1];
        d[0] = carry;
        System.arraycopy(digits, 0, d, 1, n);
        return d;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}