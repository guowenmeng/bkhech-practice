# [208. 实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)
***
### 思路一：字典嵌套/适合Python
例如单词“apple”，按以下方式存储
```
{
  'a': {
      'p': {
          'p': {
              'l': {
                  'e': {
                      'end': True
                    }
                }
            }
        }
    }
}
```
也就是一个字典只有一对键值对，键key存储的是当前字母，值val存储的是另一个字典，用于存储接下来的字母
Java用哈希表嵌套存储比较慢，推荐使用思路二新建Trie树节点的方式。
### 代码
```Python3 []
class Trie:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.dic = {}


    def insert(self, word: str) -> None:
        """
        Inserts a word into the trie.
        """
        t = self.dic
        for w in word:
            if w not in t:
                t[w] = {}
            t = t[w]
        t['end'] = True


    def search(self, word: str) -> bool:
        """
        Returns if the word is in the trie.
        """
        t = self.dic
        for w in word:
            if w not in t:
                return False
            t = t[w]
        return 'end' in t


    def startsWith(self, prefix: str) -> bool:
        """
        Returns if there is any word in the trie that starts with the given prefix.
        """
        t = self.dic
        for w in prefix:
            if w not in t:
                return False
            t = t[w]
        return True
```

```Java []
class Trie {
    private Map<Character, Map> map, t;

    /** Initialize your data structure here. */
    public Trie() {
        map = new HashMap<>();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        t = map;
        for(char w: word.toCharArray()){
            if(!t.containsKey(w)){
                t.put(w, new HashMap<Character, Map>());
            }
            t = t.get(w);
        }
        t.put('#', null);
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        t = map;
        for(char w: word.toCharArray()){
            if(!t.containsKey(w)){
                return false;
            }
            t = t.get(w);
        }
        return t.containsKey('#');
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        t = map;
        for(char w: prefix.toCharArray()){
            if(!t.containsKey(w)){
                return false;
            }
            t = t.get(w);
        }
        return true;
    }
}
```

**复杂度分析**
- 时间复杂度：insert、search、startsWith：O(N)
- 空间复杂度：insert：O(N)；search、startsWith：O(1)
***
### 思路二：创建前缀树节点/适合Java
- 创建前缀树节点TrieNode
   1. 属性1 - boolean isEnd - 用于标记当前节点是否是字符串的结束节点
   2. 属性2 - TrieNode[] next - 用于存储下一个节点，数组长度为26，存储在哪个位置即是对应哪个字母。跟用哈希表存储的方式相同，该数组的26个位置中只会有1个里面存储下一个前缀树节点
   3. 方法1 - setIsEnd(boolean isEnd) - 用于设定当前节点的属性1

### 代码

```Java []
class Trie {

    private TrieNode root, node;
    private class TrieNode {
        boolean isEnd = false;
        TrieNode[] next = new TrieNode[26];

        public void setIsEnd (boolean isEnd) {
            this.isEnd = isEnd;
        }

    }

    /** Initialize your data structure here. */
    public Trie()  {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] chs = word.toCharArray();
        node = root;
        for (int i = 0; i < chs.length; i++) {
            int w = chs[i] - 'a';
            if (node.next[w] == null) 
                node.next[w] = new TrieNode();
            node = node.next[w];
        }
        node.setIsEnd(true);
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] chs = word.toCharArray();
        node = root;
        for (int i = 0; i < chs.length; i++) {
            int w = chs[i] - 'a';
            if (node.next[w] == null) 
                return false;
            node = node.next[w];
        }
        return node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {

        char[] chs = prefix.toCharArray();
        node = root;
        for (int i = 0; i < chs.length; i++) {
            int w = chs[i] - 'a';
            if (node.next[w] == null) 
                return false;
            node = node.next[w];
        }
        return true;
    }
}
```

**复杂度分析**
- 时间复杂度：insert、search、startsWith：O(N)
- 空间复杂度：insert：O(N)；search、startsWith：O(1)
***
### 前缀树专题
|  题目   | 我的题解  |
|  ----  | ----  |
| [208. 实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)  | [题解 Java + Python实现](https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/208-shi-xian-trie-qian-zhui-shu-zi-dian-j6rpu/) |
| [677. 键值映射](https://leetcode-cn.com/problems/map-sum-pairs/)  | [题解 Java + Python实现](https://leetcode-cn.com/problems/map-sum-pairs/solution/677-jian-zhi-ying-she-python-java-by-ede-r5ak/) |
| [面试题 17.17. 多次搜索](https://leetcode-cn.com/problems/multi-search-lcci/)  | [题解 Java + Python实现](https://leetcode-cn.com/problems/multi-search-lcci/solution/mian-shi-ti-1717-duo-ci-sou-suo-python-j-61is/) |