package cn.lilq.alexam;

import cn.lilq.alexam.pojo.WordCounter;
import cn.lilq.alexam.util.Trie;
import cn.lilq.alexam.util.AnalysisUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @auther: Li Liangquan
 * @date: 2021/6/3 14:55
 * 版本2
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileInputStream("/Users/daquan/IdeaProjects/alexam/article.txt"));
        ExecutorService executorService = Executors.newCachedThreadPool();
        PriorityBlockingQueue<WordCounter> priorityBlockingQueue = new PriorityBlockingQueue<>(10, Comparator.comparingInt(WordCounter::getCount));
        Trie trie = new Trie();
        String line;
        while (scanner.hasNext()){
            line = scanner.nextLine();
            String finalLine = line;
            executorService.submit(() -> {
                //todo 线程方法
                List<String> words = AnalysisUtil.analysis(finalLine);
                int count;
                for (int i = 0; i < words.size(); i++) {
                    count = trie.addWord(words.get(i));
                    int finalI = i;
                    priorityBlockingQueue.removeIf(wordCounter -> wordCounter.getWord().equals(words.get(finalI)));
                    priorityBlockingQueue.offer(new WordCounter(words.get(i),count));
                    if (priorityBlockingQueue.size()>10)
                        priorityBlockingQueue.poll();
                }
            });
        }
        scanner.close();
        executorService.shutdown();


        System.out.println(priorityBlockingQueue);
        //todo 写入
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/daquan/IdeaProjects/alexam/top10.txt");
        for (int i = 0; i < 10; i++) {
            fileOutputStream.write(Objects.requireNonNull(priorityBlockingQueue.poll()).getWord().getBytes());
            fileOutputStream.write(" ".getBytes());
        }
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
