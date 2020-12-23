package com.bkhech.home.practice.algorithm.binarysearch;

import java.util.Arrays;

/**
 * @author guowm
 * @date 2020/12/23
 * @description https://blog.csdn.net/keep12moving/article/details/101178725
 */
public class BinarySearchTemplate {

    public static void main(String[] args) {
        int[] num = {1, 4, 6, 8, 13, 17, 19, 21, 32, 37, 42, 50};
        System.out.println(new BinarySearchTemplate().getIndex(num, 50));
    }

    /**
     * 循环二分查找，返回第一次出现该值的位置
     * @param num
     * @param target
     * @return 二分查找法 O(log n)
     */
    public int getIndex(int[] num, int target) {
        if (num == null || num.length == 0) {
            return -1;
        }

        Arrays.sort(num);

        int start = 0;
        int end = num.length - 1;
        int mid;
        //start <= end
        while (start + 1 < end) {
            // int mid = (start + end) / 2;
            // (start + end)存在着溢出的风险，进而得到错误的mid结果，导致程序错误

            // 中间位置
            // start + (end - start) / 2 = start + end/2 - start/2 = start/2 + end/2 = (start + end) / 2
            mid = start + (end - start) / 2;

            // 中值
            int midVal = num[mid];

            if (midVal == target) {
                return mid;
            } else if (midVal > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (num[start] == target) {
            return start;
        }
        if (num[end] == target) {
            return end;
        }

        return -1;
    }

}
