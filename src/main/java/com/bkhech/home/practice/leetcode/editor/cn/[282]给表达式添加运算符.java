//给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target ，在 num 的数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回所
//有能够得到目标值的表达式。 
//
// 
//
// 示例 1: 
//
// 
//输入: num = "123", target = 6
//输出: ["1+2+3", "1*2*3"] 
// 
//
// 示例 2: 
//
// 
//输入: num = "232", target = 8
//输出: ["2*3+2", "2+3*2"] 
//
// 示例 3: 
//
// 
//输入: num = "105", target = 5
//输出: ["1*0+5","10-5"] 
//
// 示例 4: 
//
// 
//输入: num = "00", target = 0
//输出: ["0+0", "0-0", "0*0"]
// 
//
// 示例 5: 
//
// 
//输入: num = "3456237490", target = 9191
//输出: [] 
//
// 
//
// 提示： 
//
// 
// 1 <= num.length <= 10 
// num 仅含数字 
// -231 <= target <= 231 - 1 
// 
// Related Topics 数学 字符串 回溯 
// 👍 302 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 给表达式添加运算符
 *   what the fuck???
 * guowm
 * 2021-10-16 16:29:29
 */
class ExpressionAddOperators{
	public static void main(String[] args) {
		Solution solution = new ExpressionAddOperators().new Solution();
		List<String> answers = solution.addOperators("105", 5);
		System.out.println(answers);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	int n;
	String num;
	int target;
	List<String> ans;
	public List<String> addOperators(String num, int target) {
		this.n = num.length();
		this.num = num;
		this.target = target;
		this.ans = new ArrayList<>();
		StringBuffer expr = new StringBuffer();
		backtrack(expr, 0, 0L, 0L);
		return ans;
	}

	/**
	 * 回溯
	 * @param expr 当前构建出的表达式
	 * @param i 表示当前的枚举到了 num 的第 i 个数字
	 * @param res 当前表达式的计算结果
	 * @param mul 表达式最后一个连乘串的计算结果
	 */
	public void backtrack(StringBuffer expr, int i, long res, long mul) {
		if (i == n) {
			if (res == target) {
				ans.add(expr.toString());
			}
			return;
		}
		int signIndex = expr.length();
		if (i > 0) {
			expr.append(0);// 占位，下面填充符号
		}
		long val = 0;
		// 枚举截取的数字长度（取多少位），注意：数字可以是单个 0 但不能有前导 0
		for (int j = i; j < n && (j == i || num.charAt(i) != '0'); j++) {
			val = val * 10 + num.charAt(j) - '0';
			expr.append(num.charAt(j));
			if (i == 0) { // 表达式开头不能添加符号
				backtrack(expr, j + 1, val, val);
			} else { // 枚举符号
				expr.setCharAt(signIndex, '+');
				backtrack(expr, j + 1, res + val, val);
				expr.setCharAt(signIndex, '-');
				backtrack(expr, j + 1, res - val, -val);
				expr.setCharAt(signIndex, '*');
				backtrack(expr, j + 1, res - mul + mul * val, mul * val);
			}
		}
		expr.setLength(signIndex);
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}