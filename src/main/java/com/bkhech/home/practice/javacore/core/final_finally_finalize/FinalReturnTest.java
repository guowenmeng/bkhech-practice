package com.bkhech.home.practice.javacore.core.final_finally_finalize;

/**
 * @author guowm
 * @date 2021/2/22
 * @description
 * 1. finally 在 return 之后时，先执行 finally 后，再执行该 return； eg: test()
 * 2. finally 内含有 return 时，直接执行其 return 后结束； eg: test1()
 * 3. finally 在 return 前，执行完 finally 后再执行 return。eg: test2()
 */
public class FinalReturnTest {

    public static void main(String[] args) {
        System.out.println("test() = " + test());
    }

    public static int test() {
        try {
            Thread.sleep(1);
            System.out.println("执行 return 1");
            return 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        } finally {
            System.out.println("执行 finally");
//            return 3;
        }
//        System.out.println("执行 return 2");
//        return 1;
    }

    public static int test2() {
        try {
            Thread.sleep(1);
            System.out.println("执行 return 1");
            return 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        } finally {
            System.out.println("执行 finally");
            return 3;
        }
//        System.out.println("执行 return 2");
//        return 1;
    }

    public static int test3() {
        try {
            Thread.sleep(1);
            System.out.println("执行 return 1");
//            return 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        } finally {
            System.out.println("执行 finally");
//            return 3;
        }
        System.out.println("执行 return 2");
        return 1;
    }
}
