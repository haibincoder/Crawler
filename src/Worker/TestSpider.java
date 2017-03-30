package Worker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class TestSpider {
    public static void main(String []args){

        //等待数据加载的时间
        //为了防止服务器封锁，这里的时间要模拟人的行为，随机且不能太短
        long waitLoadBaseTime = 10000;
        int waitLoadRandomTime = 3000;
        Random random = new Random(System.currentTimeMillis());

        // 设置 chrome 的路径
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

        // 创建一个 ChromeDriver 的接口，用于连接 Chrome
        @SuppressWarnings("deprecation")
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(
                        new File(
                                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe"))
                .usingAnyFreePort().build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建一个 Chrome 的浏览器实例
        WebDriver driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());

        // 让浏览器访问微博主页
        driver.get("http://weibo.com/3313908757");
        //等待页面动态加载完毕
        try {
            Thread.sleep(waitLoadBaseTime+random.nextInt(waitLoadRandomTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //选择每条微博的整体子模块
        List<WebElement> elements = driver.findElements(By.cssSelector("div[action-type=feed_list_item]"));
        //选择每条微博的文本内容模块
        List<WebElement> elements2 = driver.findElements(By.cssSelector("div[node-type=feed_list_reason],div[node-type=feed_list_content]"));
        System.out.println(elements.size());
        for (int i = 0; i < elements.size(); i++) {
            //展开评论
            elements.get(i).findElement(By.cssSelector("a[action-type=fl_comment]")).click();;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //评论列表
        List<WebElement> elements3 = driver.findElements(By.cssSelector("div[node-type=feed_list_commentList]"));
        System.out.println(elements3.size());
        int a = 0;
        for (int i =0;i<elements2.size()&&a<elements3.size();i++) {
            //抓取内容
            String content = elements2.get(i).getText();
            if (!content.contains("转发微博")) {
                System.out.println("content:"+content);
                //抓取评论
                if (elements3.get(a).getText().isEmpty()) {
                    System.out.println("comment:no comment");
                }else{
                    System.out.println("comment:"+elements3.get(a).getText());
                }
                a++;
            }
        }
        driver.quit();
        // 关闭 ChromeDriver 接口
        service.stop();
    }
}
