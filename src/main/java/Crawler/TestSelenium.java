package Crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSelenium {
    public static void main(String[] args) {
        System.getProperties().setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\ChromeDriver\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("http://weibo.com/");
            Thread.sleep(3000);
            WebElement webElement = webDriver.findElement(By.xpath("/html"));
            System.out.println(webElement.getAttribute("outerHTML"));
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            webDriver.close();
        }
    }
}
