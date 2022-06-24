//我们定义，在以下情况时，单词的大写用法是正确的： 
//
// 
// 全部字母都是大写，比如 "USA" 。 
// 单词中所有字母都不是大写，比如 "leetcode" 。 
// 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。 
// 
//
// 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：word = "USA"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：word = "FlaG"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= word.length <= 100 
// word 由小写和大写英文字母组成 
// 
// Related Topics 字符串 
// 👍 176 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

/**
 * 检测大写字母
 * guowm
 * 2021-11-13 19:42:27
 */
class DetectCapital{
	public static void main(String[] args) {
		Solution solution = new DetectCapital().new Solution();
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	/**
	 * Time: O(n), 其中 n 为单词长度。我们需要遍历字符串中的每个字符
	 * Space：O(1)
	 * @param word
	 * @return
	 */
    public boolean detectCapitalUse(String word) {
    	if (word.length() == 1) {
    		return true;
		}
    	boolean firstLetterIsUpper = Character.isUpperCase(word.charAt(0));
		boolean secondLetterIsUpper = Character.isUpperCase(word.charAt(1));
		for (int i = 2; i < word.length(); i++) {
			if (Character.isUpperCase(word.charAt(i)) != secondLetterIsUpper) {
				return false;
			}
		}
		/**
		 * firstLetterIsUpper == secondLetterIsUpper
		 * 全部字母都是大写，比如 "USA" 。
		 * 单词中所有字母都不是大写，比如 "leetcode"
		 *
		 * firstLetterIsUpper
		 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
		 */
		return firstLetterIsUpper == secondLetterIsUpper || firstLetterIsUpper;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}