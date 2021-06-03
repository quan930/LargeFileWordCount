package cn.lilq.alexam.pojo;

import java.util.Objects;

/**
 * @auther: Li Liangquan
 * @date: 2021/6/2 14:40
 * 单词计数器
 */
public class WordCounter {
    private String word;
    private Integer count;


    public WordCounter() {
    }

    public WordCounter(String word, Integer count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordCounter that = (WordCounter) o;
        return Objects.equals(word, that.word) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, count);
    }

    @Override
    public String toString() {
        return "WordCounter{" +
                "word='" + word + '\'' +
                ", count=" + count +
                '}';
    }
}
