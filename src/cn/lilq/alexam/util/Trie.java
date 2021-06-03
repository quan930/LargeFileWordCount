package cn.lilq.alexam.util;

/**
 * @auther: Li Liangquan
 * @date: 2021/6/3 14:16
 */
public class Trie {
    private int count=0;
    private Trie[] tries = new Trie[26];

    public Trie(int count, Trie[] tries) {
        this.count = count;
        this.tries = tries;
    }

    public Trie() {

    }

    /**
     * 增加单词并返回单词的数量
     * @param word
     * @return
     */
    public synchronized int addWord(String word){
        Trie trieRoot = this;
        for (int i = 0; i < word.length(); i++) {
            if (trieRoot.tries[word.charAt(i)-97]==null){
                trieRoot.tries[word.charAt(i)-97] = new Trie();
            }
            trieRoot = trieRoot.tries[word.charAt(i)-97];
            if (i==word.length()-1){
                trieRoot.count += 1;
            }
        }
        return trieRoot.count;
    }

    public void show(){
        backTrack(this,new StringBuilder());
    }

    private void backTrack(Trie trie,StringBuilder stringBuilder){
        if (trie==null)
            return;
        if (trie.count!=0){
            System.out.println("word:"+stringBuilder.toString()+"\tcount:"+trie.count);
        }
        for (int i = 0; i < trie.tries.length; i++) {
            stringBuilder.append((char) (i+97));
            backTrack(trie.tries[i], stringBuilder);
            stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
        }
    }

}
