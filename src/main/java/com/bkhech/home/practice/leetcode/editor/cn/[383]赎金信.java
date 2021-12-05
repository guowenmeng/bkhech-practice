//为了不在赎金信中暴露字迹，从杂志上搜索各个需要的字母，组成单词来表达意思。 
//
// 给你一个赎金信 (ransomNote) 字符串和一个杂志(magazine)字符串，判断 ransomNote 能不能由 magazines 里面的字符
//构成。 
//
// 如果可以构成，返回 true ；否则返回 false 。 
//
// magazine 中的每个字符只能在 ransomNote 中使用一次。 
//
// 
//
// 示例 1： 
//
// 
//输入：ransomNote = "a", magazine = "b"
//输出：false
// 
//
// 示例 2： 
//
// 
//输入：ransomNote = "aa", magazine = "ab"
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：ransomNote = "aa", magazine = "aab"
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= ransomNote.length, magazine.length <= 105 
// ransomNote 和 magazine 由小写英文字母组成 
// 
// Related Topics 哈希表 字符串 计数 
// 👍 240 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 赎金信
 * guowm
 * 2021-12-04 22:48:51
 */
class RansomNote{
	public static void main(String[] args) {
		Solution solution = new RansomNote().new Solution();
        boolean ans = solution.canConstruct("aa", "aab");
        System.out.println(ans);
    }
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * Time: O(n + m), 我们需要遍历两次字符串
     * Space:O(C), 因为全部为小写字母，所以 C = 26
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
	    if(ransomNote.length() > magazine.length()) {
	        return false;
        }

	    Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            map.put(ransomNote.charAt(i), map.getOrDefault(ransomNote.charAt(i), 0) + 1);
        }
        for (int i = 0; i < magazine.length(); i++) {
            if (map.isEmpty()) {
                return true;
            }
            if (map.containsKey(magazine.charAt(i))) {
                int cnt = map.get(magazine.charAt(i));
                if (cnt == 1) {
                    map.remove(magazine.charAt(i));
                } else {
                    map.put(magazine.charAt(i), cnt - 1);
                }
            }
        }
	    return map.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}