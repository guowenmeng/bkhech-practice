//给你一个整数 n ，找出从 1 到 n 各个整数的 Fizz Buzz 表示，并用字符串数组 answer（下标从 1 开始）返回结果，其中： 
//
// 
// answer[i] == "FizzBuzz" 如果 i 同时是 3 和 5 的倍数。 
// answer[i] == "Fizz" 如果 i 是 3 的倍数。 
// answer[i] == "Buzz" 如果 i 是 5 的倍数。 
// answer[i] == i 如果上述条件全不满足。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["1","2","Fizz"]
// 
//
// 示例 2： 
//
// 
//输入：n = 5
//输出：["1","2","Fizz","4","Buzz"]
// 
//
// 示例 3： 
//
// 
//输入：n = 15
//输出：["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","1
//4","FizzBuzz"] 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 104 
// 
// Related Topics 数学 字符串 模拟 
// 👍 131 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 嘶嘶嗡嗡
 * guowm
 * 2021-10-13 13:35:39
 */
class FizzBuzz{
     public static void main(String[] args) {
         final List<String> fizzBuzz = new FizzBuzz().new Solution().fizzBuzz(3);
         System.out.println(fizzBuzz);
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> fizzBuzz(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        List<String> answers = new ArrayList<>(n);
        for (int i = 1; i < n + 1; i++) {
            String ans = String.valueOf(i);
            if (i%3 == 0 && i%5 == 0) {
                ans = "FizzBuzz";
            } else if (i%3 == 0) {
                ans = "Fizz";
            } else if (i%5 == 0) {
                ans = "Buzz";
            }
            answers.add(ans);
        }

        return answers;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}