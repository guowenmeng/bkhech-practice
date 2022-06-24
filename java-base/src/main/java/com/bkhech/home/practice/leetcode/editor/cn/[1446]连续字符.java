//给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。 
//
// 请你返回字符串的能量。 
//
// 
//
// 示例 1： 
//
// 输入：s = "leetcode"
//输出：2
//解释：子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
// 
//
// 示例 2： 
//
// 输入：s = "abbcccddddeeeeedcba"
//输出：5
//解释：子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。
// 
//
// 示例 3： 
//
// 输入：s = "triplepillooooow"
//输出：5
// 
//
// 示例 4： 
//
// 输入：s = "hooraaaaaaaaaaay"
//输出：11
// 
//
// 示例 5： 
//
// 输入：s = "tourist"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 500 
// s 只包含小写英文字母。 
// 
// Related Topics 字符串 
// 👍 62 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

/**
 * 连续字符串
 * guowm
 * 2021-12-01 14:57:01
 */
class ConsecutiveCharacters{
     public static void main(String[] args) {
         final Solution solution = new ConsecutiveCharacters().new Solution();
         int ans = solution.maxPower("cc");
         System.out.println(ans);
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 双指针。 稍微优雅了点
     * Time: O(n)， 其中 n 为字符串的长度。需要遍历一次 s.
     * Space: O(1)，只需要常数的空间保存若干变量
     * @param s
     * @return
     */
    public int maxPower(String s) {
        int n = s.length(), ans = 1;
        int l = 0, h = 0;
        while (l < n) {
            while (h < n && s.charAt(l) == s.charAt(h)) {
                h++;
            }
            ans = Math.max(ans, h - l);
            l = h;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
    /**
     * 双指针。 不够优雅呢？
     * Time: O(n)， 其中 n 为字符串的长度。需要遍历一次 s.
     * Space: O(1)，只需要常数的空间保存若干变量
     * @param s
     * @return
     */
    public int maxPowerV1(String s) {
        int ans = 1;
        int l = 0, h = 0;
        char ch = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (ch == s.charAt(i)) {
                h++;
            } else {
                ans = Math.max(ans, h - l + 1);
                l = i;
                h = i;
                ch = s.charAt(i);
            }
        }
        if (l != h) {
            ans = Math.max(ans, h - l + 1);
        }
        return ans;
    }
}