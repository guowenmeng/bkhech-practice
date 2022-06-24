# QuickSort
## 基本思想
通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。

## Document

## Q&A
1. 当前快速排序的实现(QuickSortDemo)，还有很大的优化空间
  1.1 基准元素的选择
    TODO
  1.2 当序列较短时，使用插入排序更高效
    Arrays#sort中, 这个值为47。详细看：DualPivotQuicksort.java
    ```java
       /**
        * If the length of an array to be sorted is less than this
        * constant, insertion sort is used in preference to Quicksort.
        */
       private static final int INSERTION_SORT_THRESHOLD = 47;
    ```