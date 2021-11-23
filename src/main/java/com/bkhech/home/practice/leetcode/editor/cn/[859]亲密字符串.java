//给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。 
//
// 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。 
//
// 
// 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ab", goal = "ba"
//输出：true
//解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。 
//
// 示例 2： 
//
// 
//输入：s = "ab", goal = "ab"
//输出：false
//解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。 
//
// 示例 3： 
//
// 
//输入：s = "aa", goal = "aa"
//输出：true
//解释：你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。
// 
//
// 示例 4： 
//
// 
//输入：s = "aaaaaaabc", goal = "aaaaaaacb"
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, goal.length <= 2 * 104 
// s 和 goal 由小写英文字母组成 
// 
// Related Topics 哈希表 字符串 
// 👍 203 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

/**
 * 亲密字符串
 * guowm
 * 2021-11-23 11:48:57
 */
class BuddyStrings {
    public static void main(String[] args) {
        Solution solution = new BuddyStrings().new Solution();
        boolean ans = solution.buddyStrings("abccccc", "abccccc");
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 枚举
         * Time: O(n)，其中 n 为字符串的长度。
         * Space: O(c)，其中 c = 26，需要常数个空间保存字符串的字符统计次数，我们统计所有小写字母的个数，因此 c = 26
         *  和 buddyStringsV2 效果相同
         * @param s
         * @param goal
         * @return
         */
        public boolean buddyStrings(String s, String goal) {
            if (s.length() != goal.length()) {
                return false;
            }

            if (s.equals(goal)) {
                int[] count = new int[26];
                for (int i = 0; i < s.length(); i++) {
                    int j = ++count[s.charAt(i) - 'a'];
                    if (j > 1) {
                        return true;
                    }
                }
                return false;
            } else {
                int first = -1, second = -1;
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) != goal.charAt(j)) {
                        if (first == -1) {
                            first = j;
                        } else if (second == -1){
                            second = j;
                        } else {
                            return false;
                        }
                    }
                }
                return second != -1 && s.charAt(first) == goal.charAt(second) && s.charAt(second) == goal.charAt(first);
            }
        }

    }

//leetcode submit region end(Prohibit modification and deletion)

    /**
     * Time: O(n)，其中 n 为字符串的长度。
     * Space: O(c)，其中 c = 26，需要常数个空间保存字符串的字符统计次数，我们统计所有小写字母的个数，因此 c = 26
     * @param s
     * @param goal
     * @return
     */
    public boolean buddyStringsV2(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        if (s.equals(goal)) {
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                int j = ++count[s.charAt(i) - 'a'];
                if (j > 1) {
                    return true;
                }
            }
        } else {
            int i = -1;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) != goal.charAt(j)) {
                    if (i == -1) {
                        i = j;
                    } else {
                        char[] chars = s.toCharArray();
                        swap(i, j, chars);
                        return String.valueOf(chars).equals(goal);
                    }
                }
            }
        }

        return false;
    }

    private void swap(int i, int j, char[] chars) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    /**
     * Time: O(n^2)，TLE occurred
     * Space：O(1)
     */
    public boolean buddyStringsV1(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        if (s.length() == 1) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                char[] chars = s.toCharArray();
                swap(chars, i, j);
                if (String.valueOf(chars).equals(goal)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}