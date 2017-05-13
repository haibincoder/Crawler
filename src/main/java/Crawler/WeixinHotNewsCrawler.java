package Crawler;

import Model.HotNews;
import Model.RecommendNews;
import Parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static DAO.WeixinDAO.InsertHotNews;
import static DAO.WeixinDAO.InsertRecommendNews;

public class WeixinHotNewsCrawler {
    public static void main(String []args){
        try {
            //抓取搜狗微信搜索
            Document document = Jsoup.connect("http://weixin.sogou.com/").get();

            //提取热点新闻
            Elements hotNewsData = document.select("ol.hot-news li ");

            ArrayList<HotNews> hotNewsList = new ArrayList<HotNews>();

            for(Element item : hotNewsData){
                HotNews hotNews = new HotNews();
                hotNews.setNews(item.select("a").attr("title"));
                hotNews.setLink(item.select("a").attr("href"));

                System.out.println(hotNews.getNews());
                System.out.println(hotNews.getLink());

                hotNewsList.add(hotNews);
            }

            //保存到数据库
            if(InsertHotNews(hotNewsList)){
                System.out.println("添加到数据库！");
            }
            //遍历热点新闻
            for (HotNews item : hotNewsList){
                //System.out.println(item.getNews() + " " + item.getLink());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
