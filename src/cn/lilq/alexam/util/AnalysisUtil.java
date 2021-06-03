package cn.lilq.alexam.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: Li Liangquan
 * @date: 2021/6/2 14:28
 * 字符串解析 解析一行数据
 */
public class AnalysisUtil {
    public static List<String> analysis(String line){
        String stringNew = line.trim();
        List<String> strings = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringNew.length(); i++) {
            if (stringNew.charAt(i)==',' || stringNew.charAt(i)=='.' || stringNew.charAt(i)==' '){
                strings.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }else {
                stringBuilder.append(stringNew.charAt(i));
            }
        }
        if (stringBuilder.length()>0){
            strings.add(stringBuilder.toString());
        }
        return strings;
    }
}
