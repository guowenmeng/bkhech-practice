//给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位数字。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：n = 11
//输出：0
//解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 231 - 1 
// 
// Related Topics 数学 二分查找 
// 👍 228 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

/**
 * 第 N 位数字
 * 解题思路:
 * 首先我们很容易明白如下规律:
 *
 * # 1位数 9个 ===> 1 * 9
 * # 2位数 90个 ===> 2 * 90
 * # 3位数 900个 ===> 3 * 900
 * # ...
 *
 * 我们要知道第n位是什么，其实就是要找它属于几位数，它在那位数里是第多少个数，以及最终要找是该位数的第几位。
 * 我们依次排去一位数（9个），两位数（180个），三位数（2700）个，
 * 假如n是200，那么我们就知道它一定是三位数，且它是三位数里的第200-9-180=11位，转换成从0开始的坐标就是11-1=10，
 * 三位数是每三位一个数，那么它就是三位数里的10/3=3，也就是103了。而我们要找该数里的10%3=1位也就是0。
 * 另外Java等语言中注意一下溢出处理。
 * guowm
 * 2021-11-30 11:28:28
 */
class NthDigit{
     public static void main(String[] args) {
         Solution solution = new NthDigit().new Solution();
         int ans = solution.findNthDigit(30);
         System.out.println(ans);
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findNthDigit(int n) {
        int cur = 1, base = 9;
        while (n > cur * base) {
            n -= cur * base;
            cur++;
            base *= 10;
            //防止溢出
            if (Integer.MAX_VALUE / base < cur) {
                break;
            }
        }
        n--;
        int num = (int)Math.pow(10, cur - 1) + n / cur;
        int idx = n % cur;
        return num / (int)Math.pow(10, cur - 1 - idx) % 10;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}