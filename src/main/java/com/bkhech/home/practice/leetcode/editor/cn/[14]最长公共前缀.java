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
        return longestCommonPrefixV1(strs);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 方法四：二分查找法
     * Time: O(mnlogm)
     * Space: O(1)
     * @param strs
     * @return
     */
    public String longestCommonPrefixV4(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        //字符串数组中的最短字符串的长度
        int minLength = strs[0].length();
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }

        //在 [0,minLength] 的范围内通过二分查找得到最长公共前缀的长度
        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    private boolean isCommonPrefix(String[] strs, int length) {
        final String str0 = strs[0].substring(0, length);
        int count = strs.length;
        //从第二个位置开始
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 方法三：分治法
     * Time: O(mn)
     * Space: O(mlogn)
     * @param strs
     * @return
     */
    public String longestCommonPrefixV3(String[] strs) {
        // 方法三：分治法
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = start + (end - start) / 2;
            String lcpLeft = longestCommonPrefix(strs, start, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, end);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    private String commonPrefix(String lcpLeft, String lcpRight) {
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);
    }

    /**
     * 方法二：纵向扫描
     * 最优解，符合正常思维模型，易于想到
     * Time: O(mn)
     * Space: O(1)
     * @param strs
     * @return
     */
    public String longestCommonPrefixV2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 0; j < count; j++) {
                //i == strs[j].length() 条件成立时：表示数组中存在一个元素的最大字符长度等于当前公共子串（在 i 位置处）
                //strs[j].charAt(i) != c 条件成立时：表示数组中存在一个元素在索引 j 处的字符不相等
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 方法一：横向扫描
     * 最优解
     * Time: O(mn)
     * Space: O(1)
     * @param strs
     * @return
     */
    public String longestCommonPrefixV1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    private String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index ++;
        }
        return str1.substring(0, index);
    }

    /**
     * my version. 本质上是方法二 纵向扫描
     * @param strs
     * @return
     */
    public String longestCommonPrefixV0(String[] strs) {
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