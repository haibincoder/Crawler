package Word;

import org.apdplat.word.WordSegmenter;

import java.io.File;

import static Utils.Utils.GetDate;

/**
 * Created by bin on 2017/5/11.
 */
public class WordParser {
    public static void main(String[] args) {
        String input = "d:/temp/" + GetDate()+".txt";
        String output = "d:/temp/wordseg" + GetDate()+ ".txt";

        System.out.println(input);
        try {
            WordSegmenter.seg(new File(input), new File(output));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
