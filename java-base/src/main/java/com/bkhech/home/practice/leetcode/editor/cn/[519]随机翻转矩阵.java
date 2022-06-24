//给你一个 m x n 的二元矩阵 matrix ，且所有值被初始化为 0 。请你设计一个算法，随机选取一个满足 matrix[i][j] == 0 的下标 
//(i, j) ，并将它的值变为 1 。所有满足 matrix[i][j] == 0 的下标 (i, j) 被选取的概率应当均等。 
//
// 尽量最少调用内置的随机函数，并且优化时间和空间复杂度。 
//
// 实现 Solution 类： 
//
// 
// Solution(int m, int n) 使用二元矩阵的大小 m 和 n 初始化该对象 
// int[] flip() 返回一个满足 matrix[i][j] == 0 的随机下标 [i, j] ，并将其对应格子中的值变为 1 
// void reset() 将矩阵中所有的值重置为 0 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["Solution", "flip", "flip", "flip", "reset", "flip"]
//[[3, 1], [], [], [], [], []]
//输出
//[null, [1, 0], [2, 0], [0, 0], null, [2, 0]]
//
//解释
//Solution solution = new Solution(3, 1);
//solution.flip();  // 返回 [1, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
//solution.flip();  // 返回 [2, 0]，因为 [1,0] 已经返回过了，此时返回 [2,0] 和 [0,0] 的概率应当相同
//solution.flip();  // 返回 [0, 0]，根据前面已经返回过的下标，此时只能返回 [0,0]
//solution.reset(); // 所有值都重置为 0 ，并可以再次选择下标返回
//solution.flip();  // 返回 [2, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 104 
// 每次调用flip 时，矩阵中至少存在一个值为 0 的格子。 
// 最多调用 1000 次 flip 和 reset 方法。 
// 
// Related Topics 水塘抽样 哈希表 数学 随机化 
// 👍 128 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.*;

/**
 * 随机翻转矩阵
 * guowm
 * 2021-11-29 20:58:07
 */
class RandomFlipMatrix {
    public static void main(String[] args) {
        Solution solution = new RandomFlipMatrix().new Solution(3, 2);
        for (int i = 0; i < 4; i++) {
            int[] ans = solution.flip();
            System.out.println(Arrays.toString(ans));
        }
        solution.reset();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 降维 + hash表 + 交换
     * 二维的坐标能够唯一对应出编号（idx = row * n + col），可以将问题转换为一维问题，即降维公式： idx = row * n + col
     * 矩阵和下标关系： idx / n, idx % n
     * Time: 令最大调用次数为 C = 1000, 即矩阵中最多有 C 个位置被翻转。flip 操作的复杂度为 O(1)，reset 操作的复杂度为 O(C)。
     * Space：O(C)。
     */
    class Solution {
        Map<Integer, Integer> map = new HashMap<>();
        int m, n;
        // total 为剩余数个数，同时 total - 1 为区间右端点位置
        int total;
        Random random;

        public Solution(int m, int n) {
            this.random = new Random();
            this.m = m;
            this.n = n;
            this.total = m * n;
        }

        public int[] flip() {
            int x = random.nextInt(total);
            total--;
            //交换
//            当随机到某个位置 idx 时，进行分情况讨论：
//            1、该位置未被哈希表真实记录（未被翻转）：说明 idx 可被直接使用，使用 idx 作为本次随机点。
//                  同时将右端点（未被使用的）位置的映射值放到该位置，将右端点左移。确保下次再随机到 idx，
//                  仍能直接使用 idx 的映射值，同时维护了随机区间的连续性；
//            2、该位置已被哈希表真实记录（已被翻转）：此时直接使用 idx 存储的映射值（上一次交换时的右端点映射值）即可，
//                  然后用新的右端点映射值将其进行覆盖，更新右端点。确保下次再随机到 idx，仍能直接使用 idx 的映射值，
//                  同时维护了随机区间的连续性。
            int idx = map.getOrDefault(x, x);
            map.put(x, map.getOrDefault(total, total));
            return new int[]{idx / n, idx % n};
        }

        public void reset() {
            this.total = m * n;
            map.clear();
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(m, n);
 * int[] param_1 = obj.flip();
 * obj.reset();
 */
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * Memory Limit Exceeded
     * why???
     */
    class SolutionV1 {
    List<int[]> matrix = new ArrayList<>();
    int m, n;
    Random random;
    int position;

    public SolutionV1(int m, int n) {
        this.random = new Random();
        this.m = m;
        this.n = n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix.add(new int[]{i, j});
            }
        }
        this.position = matrix.size();
    }

    public int[] flip() {
        int index = random.nextInt(position);
        int[] ele = matrix.get(index);
        swap(index, ele);
        return ele;
    }

    private void swap(int index, int[] ele) {
        matrix.set(index, matrix.get(position - 1));
        matrix.set(position - 1, ele);
        position--;
    }

    public void reset() {
        this.position = matrix.size();
    }
}

}