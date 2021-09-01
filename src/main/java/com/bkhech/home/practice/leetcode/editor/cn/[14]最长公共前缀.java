//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 1757 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

/**
 * 最长公共前缀
 * guowm
 * 2021-09-01 13:29:24
 */
class LongestCommonPrefix{
     public static void main(String[] args) {
         String[] strs = new String[] {"flower","flow","flight"};
         final String ans = new LongestCommonPrefix().new Solution().longestCommonPrefix(strs);
         System.out.println(ans);
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 找到最短的字符串
        String shortestLenStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < shortestLenStr.length()) {
                shortestLenStr = strs[i];
            }
        }

        // 以最短字符串为基础寻找最长公共前缀
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < shortestLenStr.length(); i++) {
            //在指定位置的字符
            char charAt = shortestLenStr.charAt(i);
            //所有字符串在指定位置处字符是否全部相同
            boolean same = true;
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i) != charAt) {
                    same = false;
                    //存在不相同字符，则结束对比
                    break;
                }
            }
            //如果不全相同，则结束对比
            if (!same) {
                break;
            }
            //增加公共前缀
            ans.append(charAt);
        }

        return ans.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}