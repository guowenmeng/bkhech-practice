# BinarySearch
## 基本思想
二分查找的主要意思是每次查找之前，都找到中间值，
然后拿我们要比较的值和中间值比较，根据结果修改比较的上限或者下限，
通过循环最终找到相等的位置索引。

## Q&A
1. 每次寻找索引的中间值，防止索引溢出问题
```java
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
```