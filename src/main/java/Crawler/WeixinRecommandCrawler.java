package Crawler;

import Model.RecommendNews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import static DAO.WeixinDAO.InsertRecommendNews;

/**
 * Created by bin on 2017/5/13.
 */
public class WeixinRecommandCrawler {
    public static void main(String[] args) {
        try{
            //抓取搜狗微信搜索
            Document document = Jsoup.connect("http://weixin.sogou.com/").get();

            //提取推荐新闻
            //Elements newsLinks = document.getElementsByClass("news-list");
            Elements newsLinks = document.select("ul.news-list li");

            ArrayList<RecommendNews> recommendNewsList = new ArrayList<RecommendNews>();

            //遍历推荐新闻
            for (Element element : newsLinks){
                RecommendNews newsElement = new RecommendNews();
                newsElement.setNews(element.select("h3 a").text());
                newsElement.setLink(element.select("h3 a").attr("href"));
                newsElement.setContents(element.select("p").text());
                newsElement.setSource(element.select("div.s-p a").attr("href"));
                recommendNewsList.add(newsElement);
            }

            //添加到数据库
            if(InsertRecommendNews(recommendNewsList)){
                System.out.println("添加到数据库成功！");
            }

            for(RecommendNews item : recommendNewsList){
                System.out.println(item.getNews() + " " + item.getLink());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
