//给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。 
//
// 美式键盘 中： 
//
// 
// 第一行由字符 "qwertyuiop" 组成。 
// 第二行由字符 "asdfghjkl" 组成。 
// 第三行由字符 "zxcvbnm" 组成。 
// 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：words = ["Hello","Alaska","Dad","Peace"]
//输出：["Alaska","Dad"]
// 
//
// 示例 2： 
//
// 
//输入：words = ["omk"]
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：words = ["adsdf","sfd"]
//输出：["adsdf","sfd"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 20 
// 1 <= words[i].length <= 100 
// words[i] 由英文字母（小写和大写字母）组成 
// 
// Related Topics 数组 哈希表 字符串 
// 👍 186 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.*;

/**
 * 键盘行
 * guowm
 * 2021-11-01 12:21:30
 */
class KeyboardRow{
     public static void main(String[] args) {
         final Solution solution = new KeyboardRow().new Solution();
         String[] words = new String[]{"Hello","Alaska","Dad","Peace"};
         final String[] ans = solution.findWords(words);
         System.out.println(Arrays.toString(ans));
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 遍历
     * 我们为每一个英文字母标记其对应键盘上的行号，然后检测字符串中所有字符对应的行号是否相同。
     * - 我们可以预处理计算出每个字符对应的行号。
     * - 遍历字符串时，统一将大写字母转化为小写字母方便计算。
     *
     * Time: O(M)，其中 M 表示words中所有字符串的长度之和。
     * Space: O(N)，C 表示英文字母的个数，在本题中英文字母的个数为 26。注意返回值不计入空间复杂度。
     * @param words
     * @return
     */
    public String[] findWords(String[] words) {
        List<String> candidates = new ArrayList<>(words.length);
        //26 个英文字母对应所在的行数
        String rowIdx = "12210111011122000010020202";
        for (String word : words) {
            boolean isValid = true;
            //word 中第一个字符所对应所在的行数
            char idx = rowIdx.charAt(Character.toLowerCase(word.charAt(0)) - 'a');
            for (int i = 1; i < word.length(); i++) {
                if (rowIdx.charAt(Character.toLowerCase(word.charAt(i)) - 'a') != idx) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                candidates.add(word);
            }
        }
        return candidates.toArray(new String[0]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    Map<Character, Integer> dic = new HashMap();

    /**
     * Time: O(M)，其中 M 表示words中所有字符串的长度之和。
     * Space: O(N)，C 表示英文字母的个数，在本题中英文字母的个数为 26。注意返回值不计入空间复杂度。
     * @param words
     * @return
     */
    public String[] findWordsV1(String[] words) {
        init();

        List<String> candidates = new ArrayList<>(words.length);
        candidates.addAll(Arrays.asList(words));
        Iterator<String> iterator = candidates.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next();
            if (word == null) {
                iterator.remove();
                continue;
            }

            char[] chars =  word.toLowerCase().toCharArray();
            if (chars.length > 0) {
                int raw = dic.get(chars[0]);
                for (int i = 1; i < chars.length; i++) {
                    if (raw != dic.get(chars[i])) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }
        return candidates.toArray(new String[0]);
    }

    private void init() {
        String[] keyboardRows = new String[]{"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        if (dic.isEmpty()) {
            for (int i = 0; i < keyboardRows.length; i++) {
                final char[] chars = keyboardRows[i].toCharArray();
                for (char ch : chars) {
                    dic.put(ch, i);
                }
            }
        }
    }
}