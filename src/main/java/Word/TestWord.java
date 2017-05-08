package Word;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;

import java.io.File;
import java.util.List;

/**
 * Created by bin on 2017/5/4.
 */

public class TestWord {
    public static void main(String[] args) {
        List<Word> words = WordSegmenter.seg("这是用来测试分词效果的代码，移除保留字");
        //List<Word> words = WordSegmenter.segWithStopWords("这是用来测试分词效果的代码，不移除保留字");
        System.out.println(words);

        try {
            WordSegmenter.seg(new File("d:/temp/weibo.txt"),new File("d:/temp/output.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
