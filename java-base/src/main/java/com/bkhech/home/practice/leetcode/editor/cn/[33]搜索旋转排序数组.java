//整数数组 nums 按升序排列，数组中的值 互不相同 。 
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[
//k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2
//,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。 
//
// 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：nums = [1], target = 0
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -10^4 <= nums[i] <= 10^4 
// nums 中的每个值都 独一无二 
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转 
// -10^4 <= target <= 10^4 
// 
//
// 
//
// 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？ 
// Related Topics 数组 二分查找 
// 👍 1433 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

/**
 * 搜索旋转排序数组
 * guowm
 * 2021-07-08 18:57:09
 */
class SearchInRotatedSortedArray{
     public static void main(String[] args) {
         final Solution solution = new SearchInRotatedSortedArray().new Solution();
         int[] nums = new int[]{4,5,6,7,0,1,2};
         int target = 0;
         int index = solution.search(nums, target);
         System.out.println(index);
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 二分查找法
     *  对于有序数组，可以使用二分查找的方法查找元素。需要注意的是，二分的写法有很多种，
     *  所以在判断 target 大小与有序部分关系的时候可能会出现细节上的差别
     * Time: O(logn)
     * Space: O(1)
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int n = nums.length;
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //左半部分有序，一定是<=
            if (nums[left] <= nums[mid]) {
                //target 值在左半边
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            //右半部分有序
            } else {
                //target 值在右半边
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 暴力搜索
     * Time: O(n)
     * Space: O(1)
     * @param nums
     * @param target
     * @return
     */
    public int searchV1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}