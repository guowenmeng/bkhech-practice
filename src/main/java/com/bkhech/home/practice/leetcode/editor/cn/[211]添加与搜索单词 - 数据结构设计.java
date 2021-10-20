//请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。 
//
// 实现词典类 WordDictionary ： 
//
// 
// WordDictionary() 初始化词典对象 
// void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配 
// bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回 false 。word 中可能包含一些 '
//.' ，每个 . 都可以表示任何一个字母。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["WordDictionary","addWord","addWord","addWord","search","search","search","se
//arch"]
//[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
//输出：
//[null,null,null,null,false,true,true,true]
//
//解释：
//WordDictionary wordDictionary = new WordDictionary();
//wordDictionary.addWord("bad");
//wordDictionary.addWord("dad");
//wordDictionary.addWord("mad");
//wordDictionary.search("pad"); // return False
//wordDictionary.search("bad"); // return True
//wordDictionary.search(".ad"); // return True
//wordDictionary.search("b.."); // return True
// 
//
// 
//
// 提示： 
//
// 
// 1 <= word.length <= 500 
// addWord 中的 word 由小写英文字母组成 
// search 中的 word 由 '.' 或小写英文字母组成 
// 最多调用 50000 次 addWord 和 search 
// 
// Related Topics 深度优先搜索 设计 字典树 字符串 
// 👍 349 👎 0

package com.bkhech.home.practice.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 添加搜索词 - 数据结构和设计
 * guowm
 * 2021-10-19 22:21:24
 */
class DesignAddAndSearchWordsDataStructure{
	public static void main(String[] args) {
        WordDictionary wordDictionary = new DesignAddAndSearchWordsDataStructure().new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));
    }
	
//leetcode submit region begin(Prohibit modification and deletion)
class WordDictionary {
	    //字典树解法

    public WordDictionary() {
    }
    
    public void addWord(String word) {
    }
    
    public boolean search(String word) {
        return false;
    }

}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * addWord
     *  Time: O(1)
     * search
     *  Time:(N * M)。M 为单词长度，N 为 M 处单词集长度
     */
    class WordDictionaryV1 {
    List<Set<String>> dictionary;

    public WordDictionaryV1() {
        dictionary = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            dictionary.add(new HashSet<>());
        }
    }

    public void addWord(String word) {
        int index = word.length() - 1;
        //把相同长度的单词放到数组的相同位置
        dictionary.get(index).add(word);
    }

    public boolean search(String word) {
        int index = word.length() - 1;
        Set<String> wordSet = dictionary.get(index);
        //是否包含"."，不包含直接在set中查找
        if (!word.contains(".")) {
            return wordSet.contains(word);
        }
        //包含"."，则遍历Set比较查找
        for (String element : wordSet) {
            if (equals(word, element, index)) {
                return true;
            }
        }
        return false;
    }

    //比较两个元素是否相等
    private boolean equals(String word, String element, int index) {
        for (int i = 0; i <= index; i++) {
            if (element.charAt(i) != word.charAt(i) && word.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }
}
}