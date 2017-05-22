package Word;

import org.apdplat.word.WordFrequencyStatistics;
import org.apdplat.word.segmentation.SegmentationAlgorithm;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static Utils.Utils.GetDate;

public class WordCount {
    public static void main(String[] args) {
        try {
            //词频统计设置
            WordFrequencyStatistics wordFrequencyStatistics = new WordFrequencyStatistics();
            wordFrequencyStatistics.setRemoveStopWord(false);
            wordFrequencyStatistics.setResultPath("d://temp/word-frequency-statistics.txt");
            wordFrequencyStatistics.setSegmentationAlgorithm(SegmentationAlgorithm.MaxNgramScore);
//            //开始分词
//            wordFrequencyStatistics.seg("这是一段测试数据，用来测试分词效果");
//            //输出词频统计结果
//            wordFrequencyStatistics.dump();

//            //准备文件
//            Files.write(Paths.get("text-to-seg.txt"), Arrays.asList("word分词是一个Java实现的分布式中文分词组件，提供了多种基于词典的分词算法，并利用ngram模型来消除歧义。"));
//            //清除之前的统计结果
//            wordFrequencyStatistics.reset();
            //对文件进行分词
            String input = "d:/temp/" + GetDate()+".txt";
            String output = "d:/temp/seg" + GetDate()+".txt";
            String result = "d:/temp/dump" + GetDate()+".txt";
            wordFrequencyStatistics.seg(new File(input), new File(output));
            //输出词频统计结果
            wordFrequencyStatistics.dump(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
