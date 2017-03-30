package Worker;

import Parser.Parser;
import Utils.Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class WeixinSpider {
    public static void main(String []args){
        try {
            //抓取搜狗微信搜索
            Document document = Jsoup.connect("http://weixin.sogou.com/").get();

            //提取热点新闻
            Elements hotNews = document.getElementsByAttributeValue("id","topwords");

            //分割空格
            ArrayList<String> arrayList = Parser.ParserBlank(hotNews.text());

            //保存到数据库
            if(Utils.InsertHotNews(arrayList)){
                System.out.println("添加到数据库！");
            }
            //遍历热点新闻
            for (String string : arrayList){
                System.out.println(string);
            }

            //提取推荐新闻
            Elements newsLinks = document.getElementsByClass("news-list");
            String news;

            //遍历推荐新闻
            for (Element element : newsLinks){
                news = element.select("h3").text();
                ArrayList<String> recommandNews = Parser.ParserBlank(news);

                //添加到数据库
                Utils.InsertRecommendNews(recommandNews);

                for(String string : recommandNews){
                    System.out.println(string);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
