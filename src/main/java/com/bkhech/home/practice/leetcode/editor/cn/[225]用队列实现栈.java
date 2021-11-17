//请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。 
//
// 实现 MyStack 类： 
//
// 
// void push(int x) 将元素 x 压入栈顶。 
// int pop() 移除并返回栈顶元素。 
// int top() 返回栈顶元素。 
// boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。 
// 
//
// 
//
// 注意： 
//
// 
// 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。 
// 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["MyStack", "push", "push", "top", "pop", "empty"]
//[[], [1], [2], [], [], []]
//输出：
//[null, null, null, 2, 2, false]
//
//解释：
//MyStack myStack = new MyStack();
//myStack.push(1);
//myStack.push(2);
//myStack.top(); // 返回 2
//myStack.pop(); // 返回 2
//myStack.empty(); // 返回 False
// 
//
// 
//
// 提示： 
//
// 
// 1 <= x <= 9 
// 最多调用100 次 push、pop、top 和 empty 
// 每次调用 pop 和 top 都保证栈不为空 
// 
//
// 
//
// 进阶：你能否实现每种操作的均摊时间复杂度为 O(1) 的栈？换句话说，执行 n 个操作的总时间复杂度 O(n) ，尽管其中某个操作可能需要比其他操作更长的
//时间。你可以使用两个以上的队列。 
// Related Topics 栈 设计 队列 
// 👍 401 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 * guowm
 * 2021-11-17 12:04:43
 */
class ImplementStackUsingQueues{
     public static void main(String[] args) {
         final MyStack myStack = new ImplementStackUsingQueues().new MyStack();
         myStack.push(1);
         myStack.push(2);
         final int top = myStack.top();
         System.out.println(top);
         final int pop = myStack.pop();
         System.out.println(pop);
         final boolean empty = myStack.empty();
         System.out.println(empty);
     }
 
//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 栈的特性：后入先出
     * Time:
     *  init：O(1)
     *  push: O(n)，其中 n 是栈内元素的个数
     *  pop: O(1)
     *  top: O(1)
     *  empty: O(1)
     * Space: O(n)，其中 n 是栈内元素的个数。需要用两个队列存储栈内的元素
     */
    class MyStack {
    // 辅助入栈操作的辅助队列
    Queue<Integer> tmpQ1;
    // 存储栈内的元素
    Queue<Integer> q2;

    public MyStack() {
        tmpQ1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    public void push(int x) {
//        while (!q2.isEmpty()) {
//            tmpQ1.offer(q2.poll());
//        }
//        q2.offer(x);
//        while (!tmpQ1.isEmpty()) {
//            q2.offer(tmpQ1.poll());
//        }

        tmpQ1.offer(x);
        while (!q2.isEmpty()) {
            tmpQ1.offer(q2.poll());
        }
        // 交换
        swap(tmpQ1, q2);
    }

    private void swap(Queue<Integer> q1, Queue<Integer> q2) {
        Queue<Integer> temp = q1;
        this.tmpQ1 = q2;
        this.q2 = temp;
    }

    public int pop() {
        if (!q2.isEmpty()) {
            return q2.poll();
        }
        return -1;
    }
    
    public int top() {
        if (!q2.isEmpty()) {
            return q2.peek();
        }
        return -1;
    }
    
    public boolean empty() {
        return q2.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)

}