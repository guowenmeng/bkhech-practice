//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100
// 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25
// 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -231 <= n <= 231-1 
// -104 <= xn <= 104 
// 
// Related Topics 递归 数学 
// 👍 808 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

/**
 * x 的 N 次幂
 * guowm
 * 2021-12-05 20:52:32
 */
class PowxN {
    public static void main(String[] args) {
        Solution solution = new PowxN().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 快速幂 + 迭代
         *  注意：二进制思维
         * Time：O(logn)，即为对 n 进行二进制拆分的时间复杂度。
         * Space：O(1)。
         * @param x
         * @param n
         * @return
         */
		public double myPow(double x, int n) {
			// 特殊情况
			if (n == 0 || x == 1) {
				return 1;
			}
			// 最小的负数的绝对值 比 最大的正数 更大，所以用long存储n
            long ln = n;
            return n < 0 ? 1 / quickPowIteration(x, -ln) : quickPowIteration(x, ln);
		}
		private double quickPowIteration(double x, long n) {
            double ans = 1.0;
            // 贡献的初始值为 x
            double xContribute = x;
            // 在对 n 进行二进制拆分的同时计算答案
            while (n > 0) {
                if (n % 2 == 1) {
                    // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                    ans *= xContribute;
                }
                // 舍弃 n 二进制表示的最低位，这样我们每次只要判断最低位即可
                // n /= 2;
                n = n >> 1;
                // 将贡献不断地平方
                xContribute *= xContribute;
            }
            return ans;
        }

	}
//leetcode submit region end(Prohibit modification and deletion)

	/**
	 * 快速幂 + 递归：
	 * [快速幂算法]的本质是分治算法,
	 * 由于每次递归都会使得指数减少一半，因此递归的层数为 O(logn)，算法可以在很快的时间内得到结果
	 * Time: O(logn)，即为递归的层数
	 * Space: O(logn)，即为递归的层数，递归的函数调用会使用栈空间
	 *  注意：
	 *   1. n 为负数时，结果可转化为 1/结果
	 *   2. 负数转化为正数时可能会发生数值溢出的问题，解决： 将正数转化为长整数
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPowV2(double x, int n) {
		// 特殊情况
		if (n == 0 || x == 1) {
			return 1;
		}
		// 最小的负数的绝对值 比 最大的正数 更大，所以用long存储n
        long ln = n;
		return n < 0 ? 1 / quickPow(x, -ln) : quickPow(x, ln);
	}

	private double quickPow(double x, long n) {
		//递归的边界
		if (n == 1) {
			return x;
		}
		if (n%2 == 0) { // 偶数
			double half = quickPow(x, n / 2);
			return half * half;
		} else { // 奇数
			double half = quickPow(x, n / 2);
			return half * half * x;
		}
	}

	/**
	 * 暴力：
	 * Time: O(n)，会发生TLE，因为 n 很大
	 * Space: O(1)
	 *
	 * n 个 x 相乘结果即为 x 的 n 次幂
	 *  注意：
	 *   1. n 为负数时，结果可转化为 (1/x)^(-n)
	 *   2. 负数转化为正数时可能会发生数值溢出的问题，解决： 将正数转化为长整数
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow(double x, int n) {
		// 特殊情况
		if (x == 0 || n == 1) {
			return x;
		}
		if (n == 0 || x == 1) {
			return 1;
		}
		long ln = n;
		boolean negative = false;
		if (n < 0) {
			ln = -ln;
			negative = true;
		}
		double ans = 1;
		for (int i = 0; i < ln; i++) { // ln 个 x 相乘结果即为 x 的 n 次幂
			ans *= x;
		}
		return negative ? 1 / ans : ans;
	}
}