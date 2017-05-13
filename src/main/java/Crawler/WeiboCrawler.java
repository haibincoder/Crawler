package Crawler;

import Model.WeiboContent;
import Model.WeiboUserList;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import static DAO.WeiboDAO.InsertWeibo;
import static Parser.ConvertEmoji.filterEmoji;

/**
 * 利用WebCollector和获取的cookie爬取新浪微博并抽取数据
 */
public class WeiboCrawler extends BreadthCrawler {

    String cookie;

    public WeiboCrawler(String crawlPath, boolean autoParse) throws Exception {
        super(crawlPath, autoParse);
        /*获取新浪微博的cookie，账号密码以明文形式传输，请使用小号*/
        //2017.4.26 weibo.cn更改登录方式，暂时指定cookie登陆。
        //cookie = WeiboCN.getSinaCookie("17628286818", "haibinde");
        cookie = "AkKyaHLZu3dpi145vSNY5OM73T9bRbcSfwSoFsWNNWMkHSMnH6Q8GHNj7IwgUuAS2_-6RU8aVvXW2gQJJoQoGVI.";
        System.out.println(cookie);
    }

    @Override
    public HttpResponse getResponse(CrawlDatum crawlDatum) throws Exception {
        HttpRequest request = new HttpRequest(crawlDatum);
        request.setCookie(cookie);
        return request.getResponse();
    }

    public void visit(Page page, CrawlDatums next) {
        int pageNum = Integer.valueOf(page.meta("pageNum"));
        /*抽取微博*/
        Elements weibos = page.select("div.c");

        ArrayList<WeiboContent> arrayList = new ArrayList<WeiboContent>();

        //System.out.println("-------------测试数据：" + weibos.size() + "-------------");

        for (Element weibo : weibos) {
            Elements content = weibo.select("span.ctt");

            System.out.println("第" + pageNum + "页\t" + weibo.text());
            WeiboContent weiboContent = new WeiboContent();

            //将emoji表情转换为*
            if(content.text().length() > 1) {
                weiboContent.setContent(filterEmoji(content.text()));

                //System.out.println("-------------href：" + weibo.select("a").eq(2).attr("href") + "-------------");
                weiboContent.setLink(weibo.select("a").eq(2).attr("href"));
                System.out.println("--------content：" + content.text());

                arrayList.add(weiboContent);
            }
        }

        if(InsertWeibo(arrayList)){
            System.out.println("------------- 保存成功 -------------");
        }
    }

    public static void main(String[] args) throws Exception {
        WeiboCrawler crawler = new WeiboCrawler("weibo_crawler", false);
        crawler.setThreads(2);
//        /*对某人微博第1页进行爬取*/
//        for (int i = 1; i <= 2; i++) {
//            crawler.addSeed(new CrawlDatum("http://weibo.cn/2665749913?vt=4&page=" + i)
//                    .meta("pageNum", i + ""));
//            System.out.println("---------------finish -> 2665749913--------------------");
//            Thread.sleep(3);
//        }
        WeiboUserList weiboUserList = new WeiboUserList();
        weiboUserList.setArrayList();
        ArrayList<String> arrayList = weiboUserList.getArrayList();
        for(String s : arrayList){
            crawler.addSeed(new CrawlDatum("http://weibo.cn/" + s + "?vt=4&page=" + 1)
                   .meta("pageNum", 1 + ""));
            System.out.println("---------------finish -> " + s +"--------------------");
            Thread.sleep(3);
        }
        crawler.start(1);
    }
}