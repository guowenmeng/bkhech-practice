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
 * TODO
 * guowm
 * 2021-11-24 12:27:50
 */
class ReconstructOriginalDigitsFromEnglish{
     public static void main(String[] args) {
         final Solution solution = new ReconstructOriginalDigitsFromEnglish().new Solution();
         String s = "zerozero";
         final String ans = solution.originalDigits(s);
         System.out.println(ans);
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String originalDigits(String s) {
        int[] c = new int[26];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - 'a']++;
        }

        StringBuilder ans = new StringBuilder();
        Map<String, Integer> map = new LinkedHashMap<String, Integer>(10){
            {
                put("zero", 0);
                put("one", 1);
                put("two", 2);
                put("three", 3);
                put("four", 4);
                put("five", 5);
                put("six", 6);
                put("seven", 7);
                put("eight", 8);
                put("nine", 9);
            }
        };
        List<Integer> back = new ArrayList<>();
        for (String d : map.keySet()) {
            boolean flag = true;
            while (flag) {
                for (int i = 0; i < d.length(); i++) {
                    if (c[d.charAt(i) - 'a'] != 0) {
                        c[d.charAt(i) - 'a']--;
                        back.add(d.charAt(i) - 'a');
                    } else {
                        break;
                    }
                }
                if (back.size() != d.length()) {
                    for (Integer b : back) {
                        c[b]++;
                    }
                    flag = false;
                } else {
                    ans.append(map.get(d));
                }
                back.clear();
            }
        }
        return ans.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}