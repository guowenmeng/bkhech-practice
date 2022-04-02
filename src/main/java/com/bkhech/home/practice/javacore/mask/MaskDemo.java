package com.bkhech.home.practice.javacore.mask;

/**
 * 掩码解释示例
 *  掩码的二进制各个位的值都是 1
 *
 * @author guowm
 * @date 2021/12/15
 */
public class MaskDemo {


    /**
     * 我们知道0，1值与1相与的结果只与自身有关，与一个全1的数相与，可以完全体现这个数的特性。
     * 如果我们不选择16或者2的n次方作为table的大小。举个例子：
     * 假设一table大小为15，15-1=14。14的二进制表示为1110，我们可以看出不管一个数的最后一位为什么，相与之后都为0，
     * 这样就相当于忽略了这一位的作用，而1111就不同了，它能够完全显示数本身的特性。
     * 扩容时乘2操作能够继续保持这种特性。
     */
    static int indexFor(int h,int length){
        return h&(length-1);
    }

}
