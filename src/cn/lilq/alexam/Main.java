package cn.lilq.alexam;

import cn.lilq.alexam.pojo.WordCounter;
import cn.lilq.alexam.util.AnalysisUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 在/home/admin/article.txt为10G的文件，文件内容是一部英文小说。
 * 小说中只包括英文单词，单词只会被空格、西文逗号和西文句号分割，段落之间被换行符分割，段落前有3个空格缩进。
 * 计算机JVM的最大堆内存有1G，请使用多线程方式统计出这部小说中出现次数最多的前10个单词，并写入/home/admin/top10words.txt
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileInputStream("/Users/daquan/IdeaProjects/alexam/article.txt"));
        ExecutorService executorService = Executors.newCachedThreadPool();
        ConcurrentHashMap<String, WordCounter> map = new ConcurrentHashMap<>();
        PriorityBlockingQueue<WordCounter> priorityQueue = new PriorityBlockingQueue<>(10000, (o1, o2) -> o2.getCount()- o1.getCount());
        String line;
        while (true){
            try {
                line = scanner.nextLine();
                String finalLine = line;
                executorService.submit(() -> {
                    //todo 线程方法
                    List<String> words = AnalysisUtil.analysis(finalLine);
                    for (int i = 0; i < words.size(); i++) {
                        WordCounter counter = map.get(words.get(i));
                        if (counter==null){
                            WordCounter counterNew = new WordCounter(words.get(i),1 );
                            map.put(words.get(i),counterNew);
                            priorityQueue.offer(counterNew);
                        }else {
                            priorityQueue.remove(counter);
                            WordCounter counterNew = new WordCounter(words.get(i),counter.getCount()+1 );
                            map.put(words.get(i),counterNew);
                            priorityQueue.offer(counterNew);
                        }
                    }
                });
            }catch (NoSuchElementException e){
                break;
            }
        }
        scanner.close();
        executorService.shutdown();


        //todo 写入
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/daquan/IdeaProjects/alexam/top10.txt");
        for (int i = 0; i < 10; i++) {
            fileOutputStream.write(Objects.requireNonNull(priorityQueue.poll()).getWord().getBytes());
            fileOutputStream.write(" ".getBytes());
        }
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
