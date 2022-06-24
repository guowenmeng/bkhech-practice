//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 104 
// s 和 p 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 724 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找到字符串中所有字母异位词
 * guowm
 * 2021-12-01 16:57:33
 */
class FindAllAnagramsInAString {
    public static void main(String[] args) {
        final Solution solution = new FindAllAnagramsInAString().new Solution();
        String s = "cbaebabacd", p = "abc";
        List<Integer> ans = solution.findAnagrams(s, p);
        System.out.println(ans.toString());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            int sLen = s.length(), pLen = p.length();
            // 当 s 的长度小于 p 的长度，一定不存在异位词
            if (sLen < pLen) {
                return new ArrayList<>();
            }

            List<Integer> ans = new ArrayList<>();
            int[] sCnt = new int[26];
            int[] pCnt = new int[26];
            for(int i = 0; i < pLen; i++) {
                sCnt[s.charAt(i) - 'a']++;
                pCnt[p.charAt(i) - 'a']++;
            }
            if (Arrays.equals(sCnt, pCnt)) {
                ans.add(0);
            }

            for (int i = 0; i < sLen - pLen; i++) {
                sCnt[s.charAt(i) - 'a']--;
                sCnt[s.charAt(i + pLen) - 'a']++;
                if (Arrays.equals(sCnt, pCnt)) {
                    ans.add(i + 1);
                }
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}