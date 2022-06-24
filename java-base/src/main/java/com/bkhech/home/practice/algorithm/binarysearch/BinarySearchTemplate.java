package com.bkhech.home.practice.algorithm.binarysearch;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author guowm
 * @date 2020/12/23
 * @description https://blog.csdn.net/keep12moving/article/details/101178725
 */
public class BinarySearchTemplate {

    public static void main(String[] args) {
        int[] num = {1, 4, 6, 8, 13, 17, 19, 21, 32, 37, 42, 50};
        System.out.println(new BinarySearchTemplate().getIndex(num, 50));

        getMidValueTest();
    }

    /**
     * 循环二分查找，返回第一次出现该值的位置
     *
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
        // 开始位置小于结束位置，就会一直循环搜索
        //start <= end
        //start + 1 < end 相当于最后一次不进行 while循环，直接通过后面的if判断查找结果。比start <= end效率高一点点
        while (start + 1 < end) {
            // int mid = (start + end) / 2;
            // (start + end)存在着溢出的风险，进而得到错误的mid结果，导致程序错误

            // 中间位置
            // start + (end - start) / 2 = start + end/2 - start/2 = start/2 + end/2 = (start + end) / 2
            // 或者 (start + end) >>> 1
            /**
             * (start + end) >>> 1
             * @see Arrays#binarySearch(Object[], int, int, Object, Comparator)
             */
            // 假设 start =0，end =10，那么 mid 就是 5，所以说二分的意思主要在这里，每次都是计算索引的中间值
            mid = start + (end - start) / 2;

            // 中值
            int midVal = num[mid];
            // 比较数组中间值和给定的值的大小关系
            // 如果数组中间值小于给定的值，说明我们要找的值在中间值的右边
            if (midVal < target) {
                start = mid;
                // 如果数组中间值大于给定的值，说明我们要找的值在中间值的左边
            } else if (midVal > target) {
                end = mid;
            } else {
                // 找到了
                return mid;
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


    public static void getMidValueTest() {
        int start = 210000000;
        int end = 2120000000;
        //wrong
        int mid = (start + end) / 2;
        System.out.println("mid = " + mid);

        //right
        int mid2 = start + (end - start) / 2;
        System.out.println("mid2 = " + mid2);

        //right
        int mid3 = (start + end) >>> 1;
        System.out.println("mid3 = " + mid3);

        System.out.println(-4 >> 1);
        System.out.println(-2 >> 1);
        System.out.println(-2 >>> 1);
        System.out.println(-1 >>> 1);

        // 右移31位，相当于比较符号位是否相同。进行异或运算（相同为0，不同为1）
        System.out.println((start >> 31) ^ (end >> 31));

    }

}
