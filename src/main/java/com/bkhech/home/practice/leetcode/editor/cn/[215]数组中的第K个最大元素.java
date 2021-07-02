//在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 示例 1: 
//
// 输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 说明: 
//
// 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。 
// Related Topics 堆 分治算法 
// 👍 1144 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素
 * guowm
 * 2021-06-23 15:55:14
 * {@inheritDoc doc/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode-.md}
 */
class KthLargestElementInAnArray{
     public static void main(String[] args) {
         final Solution solution = new KthLargestElementInAnArray().new Solution();
         int[] nums = {3,2,1,5,6,4};
         int k = 2;
         final int kthLargest = solution.findKthLargest(nums, k);
         System.out.println(kthLargest);
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length ==0 || nums.length < k) {
            return -1;
        }

        // 最大堆
        PriorityQueue<Integer> numsQueue = new PriorityQueue<>(nums.length, (o1, o2) -> o2 - o1);
        // 将数组重组成最大堆
        Arrays.stream(nums).forEach(numsQueue::offer);

        // 轮询出前 K-1 大
        for (int i = 0; i < k - 1; i++) {
            numsQueue.poll();
        }
        // 第 K 大
        return numsQueue.poll();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}