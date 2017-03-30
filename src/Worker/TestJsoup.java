package Worker;

import Parser.Parser;
import Utils.Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class TestJsoup {
    public static void main(String []args) {
        try {
            //抓取搜狗微信搜索
            Document document = Jsoup.connect("http://weixin.sogou.com/").get();

            Elements hotNews = document.select("li");

            Elements test = hotNews.select("a");

            for (Element element : test) {
                System.out.printf(element.attr("href"));
            }
            //System.out.printf(test.first().attr("href"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
