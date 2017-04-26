package Crawler;

import Model.WeiboContent;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import static Parser.ChangeEmoji.filterEmoji;
import static Utils.Utils.InsertWeibo;

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
        cookie = "Aq5sxSUSDZRcyy2HcFhSUkdjKwsbpZ4ZFa6s_V29NzWY5hCPaZ_MrESuqWv2BHrec0WkifPMNKibuUjTkYpBimM";
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
        crawler.setThreads(3);
        /*对某人微博前2页进行爬取*/
        for (int i = 1; i <= 2; i++) {
//            crawler.addSeed(new CrawlDatum("http://weibo.cn/2665749913?vt=4&page=" + i)
//                    .meta("pageNum", i + ""));
//            System.out.println("---------------finish -> 2665749913--------------------");
//
//            crawler.addSeed(new CrawlDatum("http://weibo.cn/perry28pp?vt=4&page=" + i)
//                    .meta("pageNum", i + ""));
//            System.out.println("---------------finish -> perry28pp--------------------");
//
//            crawler.addSeed(new CrawlDatum("http://weibo.cn/maboyong?vt=4&page=" + i)
//                    .meta("pageNum", i + ""));
//            System.out.println("---------------finish -> maboyong--------------------");
//
//            crawler.addSeed(new CrawlDatum("http://weibo.cn/1618051664?vt=4&page=" + i)
//                    .meta("pageNum", i + ""));
//            System.out.println("---------------finish -> 1618051664--------------------");
//
//            crawler.addSeed(new CrawlDatum("http://weibo.cn/1314608344?vt=4&page=" + i)
//                    .meta("pageNum", i + ""));
//            System.out.println("---------------finish -> 1314608344--------------------");
//
//            crawler.addSeed(new CrawlDatum("http://weibo.cn/1314608344?vt=4&page=" + i)
//                    .meta("pageNum", i + ""));
//            System.out.println("---------------finish -> 1314608344--------------------");
//
//            crawler.addSeed(new CrawlDatum("http://weibo.cn/sinapapers?vt=4&page=" + i)
//                    .meta("pageNum", i + ""));
//            System.out.println("---------------finish -> sinapapers--------------------");
            crawler.addSeed(new CrawlDatum("http://weibo.cn/breakingnews?vt=4&page=" + i)
                   .meta("pageNum", i + ""));
        }
        crawler.start(1);
    }

}