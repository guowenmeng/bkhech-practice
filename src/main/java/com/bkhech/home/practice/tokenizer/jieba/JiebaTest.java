package com.bkhech.home.practice.tokenizer.jieba;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.qianxinyao.analysis.jieba.keyword.Keyword;
import com.qianxinyao.analysis.jieba.keyword.TFIDFAnalyzer;

import java.util.List;

/**
 * @author guowm
 * @date 2020/7/7
 * @description
 * Jieba分词
 */
public class JiebaTest {

    public static void main(String[] args) {
        JiebaTest test = new JiebaTest();

//        test.testDemo();
        test.tfidf();
    }

    public void testDemo() {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        String[] sentences =
                new String[] {"这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。",
                        "我不喜欢日本和服。", "雷猴回归人间。", "我觉得Jieba还不错！我是王婆！"};
        for (String sentence : sentences) {
            System.out.println(segmenter.process(sentence, JiebaSegmenter.SegMode.INDEX).toString());
        }
    }

    /**
     * tfidf算法提取关键词
     */
    public void tfidf() {
//        String content="孩子上了幼儿园 安全防拐教育要做好";
        String content="我觉得Jieba还不错！我是王婆！";
        int topN=5;
        TFIDFAnalyzer tfidfAnalyzer=new TFIDFAnalyzer();
        List<Keyword> list=tfidfAnalyzer.analyze(content,topN);
        for(Keyword word:list) {
            System.out.println(word.getName()+":"+word.getTfidfvalue()+",");
        }
        // 防拐:0.1992,幼儿园:0.1434,做好:0.1065,教育:0.0946,安全:0.0924
    }

}
