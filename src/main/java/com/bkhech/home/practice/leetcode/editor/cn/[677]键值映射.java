//实现一个 MapSum 类，支持两个方法，insert 和 sum： 
//
// 
// MapSum() 初始化 MapSum 对象 
// void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 ke
//y 已经存在，那么原来的键值对将被替代成新的键值对。 
// int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["MapSum", "insert", "sum", "insert", "sum"]
//[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
//输出：
//[null, null, 3, null, 5]
//
//解释：
//MapSum mapSum = new MapSum();
//mapSum.insert("apple", 3);  
//mapSum.sum("ap");           // return 3 (apple = 3)
//mapSum.insert("app", 2);    
//mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
// 
//
// 
//
// 提示： 
//
// 
// 1 <= key.length, prefix.length <= 50 
// key 和 prefix 仅由小写英文字母组成 
// 1 <= val <= 1000 
// 最多调用 50 次 insert 和 sum 
// 
// Related Topics 设计 字典树 哈希表 字符串 
// 👍 170 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 键值映射
 * guowm
 * 2021-11-14 22:35:48
 */
class MapSumPairs{
	public static void main(String[] args) {
        MapSum solution = new MapSumPairs().new MapSum();
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class MapSum {
    /**
     * insert: Time: O(1)
     * sum: Time(n)，其中 n 为 当前字典的大小
     */
    final HashMap<String, Integer> dic;
    public MapSum() {
        dic = new HashMap<>();
    }
    
    public void insert(String key, int val) {
        dic.put(key, val);
    }
    
    public int sum(String prefix) {
        int sum = 0;
        for (Map.Entry<String, Integer> entry : dic.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                sum += entry.getValue();
            }
        }
        return sum;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}