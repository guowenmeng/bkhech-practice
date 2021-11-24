//给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "owoztneoer"
//输出："012"
// 
//
// 示例 2： 
//
// 
//输入：s = "fviefuro"
//输出："45"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 105 
// s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一 
// s 保证是一个符合题目要求的字符串 
// 
// Related Topics 哈希表 数学 字符串 
// 👍 122 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.*;

/**
 * 从英文中重建数字
 * guowm
 * 2021-11-24 12:27:50
 */
class ReconstructOriginalDigitsFromEnglish{
     public static void main(String[] args) {
         final Solution solution = new ReconstructOriginalDigitsFromEnglish().new Solution();
         String s = "owoztneoer";
         final String ans = solution.originalDigits(s);
         System.out.println(ans);

         //字符测试
         char a = '0';//48
         int b = a - 40;
         System.out.println(b);//8
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * Time：O(n)，其中 ∣n 是字符串 n 的长度。
     * Space：O(Σ)，其中 Σ 表示字符集大小，当前 Σ 为所有在 0∼9 中出现的英文字母
     * @param s
     * @return
     */
    public String originalDigits(String s) {
        Map<Character, Integer> c = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            c.put(ch, c.getOrDefault(ch, 0) + 1);
        }

        int[] cnt = new int[10];
        cnt[0] = c.getOrDefault('z', 0);
        cnt[2] = c.getOrDefault('w', 0);
        cnt[4] = c.getOrDefault('u', 0);
        cnt[6] = c.getOrDefault('x', 0);
        cnt[8] = c.getOrDefault('g', 0);

        cnt[3] = c.getOrDefault('h', 0) - cnt[8];
        cnt[5] = c.getOrDefault('f', 0) - cnt[4];
        cnt[7] = c.getOrDefault('s', 0) - cnt[6];

        cnt[1] = c.getOrDefault('o', 0) - cnt[0] - cnt[2] - cnt[4];
        cnt[9] = c.getOrDefault('i', 0) - cnt[5] - cnt[6] - cnt[8];

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < cnt.length; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                // i + '0' 转化成字符
                ans.append((char)(i + '0'));
            }
        }
        return ans.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}