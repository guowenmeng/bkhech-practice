package com.bkhech.home.practice.algorithm.greedy;

import java.util.Arrays;

/**
 * 贪心算法示例
 * @author guowm
 * @date 2021/6/17
 */
public class GreedySolutionDemo {

    public static void main(String[] args) {
        int[] g = {1,2,3};
        int[] s = {1,1};
        final int contentChildren = new GreedySolutionDemo().findContentChildren(g, s);
        System.out.println(contentChildren);
    }


    /**
     * 分发饼干
     * <a href="https://leetcode-cn.com/problems/assign-cookies/"></a>
     * @param g 孩子胃口值
     * @param s 饼干尺寸
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int child = 0, cookie = 0;
        while (child < g.length && cookie < s.length) {
            if (g[child] <= s[cookie]) {
                ++ child;
            }
            ++ cookie;
        }
        return child;
    }
}
