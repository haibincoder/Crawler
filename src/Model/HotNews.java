package Model;

import java.text.SimpleDateFormat;

/**
 * Created by bin on 2017/3/29.
 */
public class HotNews {
    private String news;
    private SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd");
    private String contents;
    private String link;
    private SimpleDateFormat publishDateTime = new SimpleDateFormat("yyyy-MM-dd");

    public String getNews(){
        return news;
    }
    public void setNews(String news){
        this.news = news;
    }

    public SimpleDateFormat getDateTime() {
        return dateTime;
    }

    public void setDateTime(SimpleDateFormat dateTime) {
        this.dateTime = dateTime;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public SimpleDateFormat getPublishDateTime() {
        return publishDateTime;
    }

    public void setPublishDateTime(SimpleDateFormat publishDateTime) {
        this.publishDateTime = publishDateTime;
    }
}
