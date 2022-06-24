package com.bkhech.home.practice.javacore.java8.tailrecursion;

import java.math.BigDecimal;
/**
 * 尾调用测试
 * @author guowm
 * @date 2021/4/22
 */
public class Main {

    public static void main(String[] args) {

        TestDto testDto = new TestDto();
        testDto.setBegin(1L);
        //不报错
//        testDto.setEnd(3L);
        testDto.setEnd(9000L);
        testDto.setTotal(BigDecimal.ONE);

//        BigDecimal rt = factorialRecursion(testDto);
//        System.out.println("[factorialRecursion]计算 9k 的阶乘，结果为" + rt);


        BigDecimal result = factorialTailRecursion(testDto).invoke();
        System.out.println("[factorialTailRecursion]计算 9k 的阶乘，结果为" + result);
    }

    /**
     * 阶乘计算
     *  递归实现
     * @param begin
     * @param end
     * @param total
     * @return
     */
    public static String factorialRecursion(long begin, long end, BigDecimal total) {
        // begin 每次计算时都会递增，当 begin 和 end 相等时，计算结束，返回最终值
        if (begin == end) {
            return total.toString();
        }
        // recursion 第三个参数表示当前阶乘的结果
        return factorialRecursion(++begin, end, total.multiply(new BigDecimal(begin)));
    }

    /**
     * 阶乘计算
     *  递归实现
     * @param testDto
     * @return
     */
    public static BigDecimal factorialRecursion(TestDto testDto) {
        // begin 每次计算时都会递增，当 begin 和 end 相等时，结束递归，返回 testDTO.getTotal() 最终值
        if (testDto.getBegin().equals(testDto.getEnd())) {
            return testDto.getTotal();
        }
        testDto.setBegin(1+testDto.getBegin());
        // 计算本次递归的值
        testDto.setTotal(testDto.getTotal().multiply(new BigDecimal(testDto.getBegin())));
        return factorialRecursion(testDto);
    }

    /**
     * 阶乘计算
     *  尾递归实现
     * @param testDto
     * @return
     */
    public static TailRecursion<BigDecimal> factorialTailRecursion(TestDto testDto) {
        // 如果已经递归到最后一个数字了，结束递归，返回 testDTO.getTotal() 值
        if (testDto.getBegin().equals(testDto.getEnd())) {
            return TailRecursionInvoke.done(testDto.getTotal());
        }
        testDto.setBegin(1+testDto.getBegin());
        // 计算本次递归的值
        testDto.setTotal(testDto.getTotal().multiply(new BigDecimal(testDto.getBegin())));
        // 这里是最大的不同，这里每次调用递归方法时，使用的是 Lambda 的方式，这样只是初始化了一个 Lambda 变量而已，
        // factorialRecursion 方法的内存是不会分配的
        return TailRecursionInvoke.call(()-> factorialTailRecursion(testDto));
    }
}
