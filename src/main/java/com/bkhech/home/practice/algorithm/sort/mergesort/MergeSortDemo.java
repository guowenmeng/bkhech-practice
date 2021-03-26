package com.bkhech.home.practice.algorithm.sort.mergesort;

import java.util.Arrays;

/**
 * 归并排序
 * https://zhuanlan.zhihu.com/p/124356219
 *
 * @author guowm
 * @date 2021/3/26
 */
public class MergeSortDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 6, 9, 2, 5, 3, 7, 4, 8};
        mergeSort(arr);
    }

    public static void mergeSort(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];

//        mergeSortRecursive(arr, result, 0, len - 1);

        mergeSortIteration(arr, result, len);

        Arrays.stream(arr).forEach(item -> System.out.print(item + " "));
    }

    /**
     * 迭代法
     * @param arr 待排序数组
     * @param result
     * @param len
     */
    static void mergeSortIteration(int[] arr, int[] result, int len) {
        int block, start;

        /**
         * block 表示分块，控制每块的元素的数量，依次为 1，2，4，8，16
         */
        for (block = 1; block < len * 2; block *= 2) {
            //依次对数组，按照指定块大小，进行排序并合并
            for (start = 0; start < len; start += 2 * block) {
                int low = start;
                int mid = (start + block) < len ? (start + block) : len;
                int high = (start + 2 * block) < len ? (start + 2 * block) : len;

                //两个块的起始下标及结束下标
                int start1 = low, end1 = mid;
                int start2 = mid, end2 = high;

                //开始对两个block进行归并排序
                while (start1 < end1 && start2 < end2) {
                    result[low++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
                }
                while (start1 < end1) {
                    result[low++] = arr[start1++];
                }
                while (start2 < end2) {
                    result[low++] = arr[start2++];
                }
            }

            int[] temp = arr;
            arr = result;
            result = temp;
        }
    }

    /**
     * 递归法
     * @param arr 待排序数组
     * @param result 申请额外归并数组
     * @param start 开始下标
     * @param end 结束下标
     */
    static void mergeSortRecursive(int[] arr, int[] result, int start, int end) {
        if (start >= end) {
            return;
        }

        int len = end - start;
        //注意：运算符优先级
        // 先算术运算，后移位运算，最后位运算。请特别注意：1 << 3 + 2 & 7等价于 (1 << (3 + 2)) & 7.
        //https://www.jianshu.com/p/a65c54c26380
        int mid = (len >> 1) + start;

        int low1 = start, high1 = mid;
        int low2 = mid + 1, high2 = end;

        mergeSortRecursive(arr, result, low1, high1);
        mergeSortRecursive(arr, result, low2, high2);

        int k = start;

        //只要其中任意一个数组遍历完，就结束
        while (low1 <= high1 && low2 <= high2) {
//            result[k++] = arr[low1] < arr[low2] ? arr[low1++] : arr[low2++];
            if (arr[low1] < arr[low2]) {
                result[k] = arr[low1];
                low1++;
                k++;
            } else {
                result[k] = arr[low2];
                low2++;
                k++;
            }
        }

        // 把未遍历完的数组，剩余部分放置result中
        while (low1 <= high1) {
//            result[k++] = arr[low1++];
            result[k] = arr[low1];
            low1++;
            k++;
        }

        // 把未遍历完的数组，剩余部分放置result中
        while (low2 <= high2) {
//            result[k++] = arr[low2++];
            result[k] = arr[low2];
            low2++;
            k++;
        }

        for (k = start; k <= end; k++) {
            arr[k] = result[k];
        }
    }

}
