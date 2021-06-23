//Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼
//写检查。 
//
// 请你实现 Trie 类： 
//
// 
// Trie() 初始化前缀树对象。 
// void insert(String word) 向前缀树中插入字符串 word 。 
// boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false
// 。 
// boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否
//则，返回 false 。 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//输出
//[null, null, true, false, true, null, true]
//
//解释
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // 返回 True
//trie.search("app");     // 返回 False
//trie.startsWith("app"); // 返回 True
//trie.insert("app");
//trie.search("app");     // 返回 True
// 
//
// 
//
// 提示： 
//
// 
// 1 <= word.length, prefix.length <= 2000 
// word 和 prefix 仅由小写英文字母组成 
// insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次 
// 
// Related Topics 设计 字典树 
// 👍 798 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 实现前缀树 Trie
 * guowm
 * 2021-06-23 11:26:47
 */
class ImplementTriePrefixTree{

     public static void main(String[] args) {
         //	测试用例:["Trie","insert","search","search","startsWith","insert","search"]
         //			[[],["apple"],["apple"],["app"],["app"],["app"],["app"]]
         final Trie trie = new ImplementTriePrefixTree().new Trie();
         trie.insert("apple");
         boolean param_2 = trie.search("apple");
         boolean param_3 = trie.search("app");
         boolean param_4 = trie.startsWith("app");
         trie.insert("app");
         boolean param_5 = trie.search("app");

         System.out.println(Arrays.asList(null, null, param_2, param_3, param_4, null, param_5));
     }
 
//leetcode submit region begin(Prohibit modification and deletion)
class Trie {

    private class TrieNode {
        int value;
        HashMap<Character, TrieNode> next = new HashMap<>();
        boolean isEnd;

        public TrieNode(){}
        public TrieNode(int value) {
            this.value = value;
        }
    }

    TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode trieNode = root;
        final char[] wordChars = word.toCharArray();
        for (int i = 0; i < wordChars.length; i++) {
            final HashMap<Character, TrieNode> next = trieNode.next;
            // 已经存在当前字符 TrieNode
            if (next.containsKey(wordChars[i])) {
                trieNode = next.get(wordChars[i]);
            } else {// 不存在
                final TrieNode newTrieNode = new TrieNode(wordChars[i]);
                next.put(wordChars[i], newTrieNode);
                trieNode = newTrieNode;
            }
        }
        // word 的最后一个字符是单词结尾
        trieNode.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode trieNode = root;
        final char[] wordChars = word.toCharArray();
        for (int i = 0; i < wordChars.length; i++) {
            final HashMap<Character, TrieNode> next = trieNode.next;
            if (next.containsKey(wordChars[i])) {
                trieNode = next.get(wordChars[i]);
            } else {
                return false;
            }
        }
        // 整个 word 遍历完之后，看最后一个 TrieNode 是否被标记为 isEnd = true;
        return trieNode.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode trieNode = root;
        final char[] prefixChars = prefix.toCharArray();
        for (int i = 0; i < prefixChars.length; i++) {
            final HashMap<Character, TrieNode> next = trieNode.next;
            if (next.containsKey(prefixChars[i])) {
                trieNode = next.get(prefixChars[i]);
            } else {
                return false;
            }
        }
        // 如果整个 prefix 遍历完，说明这个 prefix 存在;
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}