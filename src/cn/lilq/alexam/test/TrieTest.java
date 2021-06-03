package cn.lilq.alexam.test;

import cn.lilq.alexam.util.Trie;

/**
 * @auther: Li Liangquan
 * @date: 2021/6/3 19:16
 */
public class TrieTest {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.addWord("abc");
        trie.addWord("abc");
        trie.addWord("abb");
        trie.addWord("zlq");
        trie.addWord("hello");
        trie.addWord("world");
        trie.show();
    }
}
