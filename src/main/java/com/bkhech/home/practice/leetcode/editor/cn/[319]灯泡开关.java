//初始时有 n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭一个。 
//
// 第三轮，你每三个灯泡就切换一个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，你每 i 个灯泡就切换一个灯泡的开关。直到第 n 轮，你只需要切换最后
//一个灯泡的开关。 
//
// 找出并返回 n 轮后有多少个亮着的灯泡。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：n = 3
//输出：1 
//解释：
//初始时, 灯泡状态 [关闭, 关闭, 关闭].
//第一轮后, 灯泡状态 [开启, 开启, 开启].
//第二轮后, 灯泡状态 [开启, 关闭, 开启].
//第三轮后, 灯泡状态 [开启, 关闭, 关闭]. 
//
//你应该返回 1，因为只有一个灯泡还亮着。
// 
//
// 示例 2： 
//
// 
//输入：n = 0
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 109 
// 
// Related Topics 脑筋急转弯 数学 
// 👍 236 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Stack;

/**
 * 灯泡开关
 *  先写了一个推的过程。因为 0 <= n <= 1e9，很大，要么 TLE, 要么 堆栈溢出。
 *
 *  所以，然后直接输出个100次，打表根据结果，观察，发现规律。
 *  问题最终转换为：在 [1,n] 中完全平方数的个数为多少。
 *  根据数论推论，[1,n] 中完全平方数的个数为 ⌊根号n⌋，即最后亮着的灯泡数量为 ⌊根号n⌋。
 *  直接 (int)Math.sqrt(n)
 *
 * guowm
 * 2021-11-15 12:13:13
 */
class BulbSwitcher{
     public static void main(String[] args) {
         final Solution solution = new BulbSwitcher().new Solution();
         final int ans = solution.bulbSwitchV1(5);
         System.out.println(ans);
//         for (int i = 0; i < 101; i++) {
//             System.out.printf(i + ":"+ ans + "\t");
//         }
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

    public int bulbSwitchV1(int n) {
        if (n < 2) {
            return n;
        }
        // interesting？ 初始化长度为 n + 1
        int[] nums = new int[n + 1];
        Arrays.fill(nums, 1);
        for (int i = 2; i <= n; i++) {
            // 第 i 轮改变所有编号为 i 的倍数的灯泡的状态
//            bulbSwitchRecursive(nums, i, i);
            Stack<Integer> stack = new Stack<>();
            bulbSwitchIteration(nums, i, stack);
        }
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            }
        }
        // interesting？ 结果为 count - 1
        return count - 1;
    }

    /**
     * 迭代 运行失败: Time Limit Exceeded 测试用例:9999999 stdout:
     * @param nums
     * @param i
     * @param stack
     */
    private void bulbSwitchIteration(int[] nums, int i, Stack<Integer> stack) {
        int step = i;
        int curIndex = i;
        int len = nums.length;
        while (curIndex < len) {
            stack.push(curIndex);
            curIndex += step;
        }
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            nums[idx] ^= 1;
        }
    }

    /**
     * 递归 运行失败: java.lang.StackOverflowError at
     * @param nums
     * @param index
     * @param step
     */
    private void bulbSwitchRecursive(int[] nums, int index, int step) {
        if (index == nums.length - 1) {
            nums[nums.length - 1] ^= 1;
            return;
        }
        if (index >= nums.length) {
            return;
        }
        nums[index] ^= 1;
        bulbSwitchRecursive(nums, index + step, step);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}