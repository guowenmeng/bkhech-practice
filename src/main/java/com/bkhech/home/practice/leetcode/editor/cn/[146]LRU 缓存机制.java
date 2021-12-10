//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。 
//
// 
// 
// 实现 LRUCache 类： 
//
// 
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
// 
//
// 
// 
// 
//
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 3000 
// 0 <= value <= 104 
// 最多调用 3 * 104 次 get 和 put 
// 
// Related Topics 设计 哈希表 链表 双向链表 
// 👍 1476 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 最近最少被访问缓存，超出容量（capacity）则删除
 * guowm
 * 2021-07-05 10:38:09
 */
class LruCache{
     public static void main(String[] args) {
         final LRUCache lruCache = new LruCache().new LRUCache(2);
         lruCache.put(1, 1); // 缓存是 {1=1}
         lruCache.put(2, 2); // 缓存是 {1=1, 2=2}
         lruCache.put(1, 111); // 缓存是 {1=1, 2=2}
         lruCache.get(1);    // 返回 1
         lruCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
         lruCache.get(2);    // 返回 -1 (未找到)
         lruCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
         lruCache.get(1);    // 返回 -1 (未找到)
         lruCache.get(3);    // 返回 3
         lruCache.get(4);    // 返回 4
     }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 时间复杂度：对于 put 和 get 都是 O(1)。
     * 空间复杂度：O(capacity)，因为哈希表和双向链表最多存储 capacity+1 个元素。
     */
    class LRUCache {
    class DoubleLinkedNode {
        int key, val;
        DoubleLinkedNode prev, next;

        public DoubleLinkedNode() {
        }

        public DoubleLinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private final Map<Integer, DoubleLinkedNode> cache = new HashMap<>();
    private int size;
    private final int capacity;
    private final DoubleLinkedNode head;
        private final DoubleLinkedNode tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        //创建伪头部和伪尾部节点
        this.head = new DoubleLinkedNode();
        this.tail = new DoubleLinkedNode();
        //构建头部和尾部之间关系
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 如果key存在，先通过hash表定位，再移到头部
     * @param key
     * @return
     */
    public int get(int key) {
        //hash表定位
        DoubleLinkedNode node = cache.get(key);
        //判断key是否存在
        if (node == null) {
            return -1;
        }
        //移到头部
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        //先通过hash表定位
        final DoubleLinkedNode node = cache.get(key);
        if (node == null) {
            //如果key不存在，创建一个新的节点
            DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
            //添加进hash表
            cache.put(key, newNode);
            //添加到双向链表头部
            addToHead(newNode);
            //容量加一
            ++size;
            if (size > capacity) {
                //如果超出容量，删除双向链表的尾部节点
                DoubleLinkedNode tailNode = removeTail();
                //删除hash表中对应项
                cache.remove(tailNode.key);
                //容量减一
                --size;
            }
        } else {
            //如果key存在，修改value值，并移动此节点到头部
            node.val = value;
            moveToHead(node);
        }
    }

    private DoubleLinkedNode removeTail() {
        DoubleLinkedNode ret = tail.prev;
        removeNode(ret);
        return ret;
    }

    private void addToHead(DoubleLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void moveToHead(DoubleLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(DoubleLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 基于Java原有数据结构LinkedHashMap实现
     */
    class LRUCacheV1 extends LinkedHashMap<Integer, Integer> {

    private final int capacity;

    public LRUCacheV1(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {

        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}

}