//对整数的二进制表示取反（0 变 1 ，1 变 0）后，再转换为十进制表示，可以得到这个整数的补数。 
//
// 
// 例如，整数 5 的二进制表示是 "101" ，取反后得到 "010" ，再转回十进制表示得到补数 2 。 
// 
//
// 给你一个整数 num ，输出它的补数。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：num = 5
//输出：2
//解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
// 
//
// 示例 2： 
//
// 
//输入：num = 1
//输出：0
//解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= num < 231 
// 
//
// 
//
// 注意：本题与 1009 https://leetcode-cn.com/problems/complement-of-base-10-integer/ 相
//同 
// Related Topics 位运算 
// 👍 250 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

/**
 * 数字的补数
 *  首先找出 num 二进制表示最高位 index，然后，我们就可以遍历 num 的第 0∼i 个二进制位，将它们依次进行取反。
 *  我们也可以用更高效的方式，构造掩码 mask = 2^(i+1) - 1，它是一个 i+1i+1 位的二进制数，
 *  并且每一位都是 1。我们将 num 与 mask 进行异或运算，即可得到答案。
 *
 *  注意：整数溢出问题，java中没问题
 * guowm
 * 2021-10-18 11:28:41
 */
class NumberComplement{
     public static void main(String[] args) {
         final Solution solution = new NumberComplement().new Solution();
         final int complement = solution.findComplement(11);
         System.out.println(complement);
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * Time: O(logN).找出 num 二进制表示最高位的 1 需要的时间为 O(logN)
     * Space: O(1)
     * @param num
     * @return
     */
    public int findComplement(int num) {
        // num 二进制表示最高位 index
        int highbit = 0;
        for (int i = 1; i <= 30; i++) {
            if (num >= (1 << i)) {
                highbit = i;
            } else {
                break;
            }
        }
//        int mask = highbit == 30 ? 0x7fffffff : (1 << (highbit + 1)) - 1;
        int mask = (1 << (highbit + 1)) - 1;
        return num ^ mask;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * my shit version
     * @param num
     * @return
     */
    public int findComplementV1(int num) {
        String binaryString = Integer.toBinaryString(num);
        char[] chars = binaryString.toCharArray();
        StringBuilder complement = new StringBuilder();
        for (int i = 0; i < chars.length; i ++) {
            complement.append(chars[i] == '0' ? '1' : '0');
        }
        return Integer.parseInt(complement.toString(), 2);
    }
}