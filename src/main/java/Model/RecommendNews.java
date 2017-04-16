package Model;

import java.text.SimpleDateFormat;

public class RecommendNews {
    private String news;
    private SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd");
    private String contents;
    private String linik;
    private SimpleDateFormat publishTime = new SimpleDateFormat("yyyy-MM-dd");

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
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

    public String getLinik() {
        return linik;
    }

    public void setLinik(String linik) {
        this.linik = linik;
    }

    public SimpleDateFormat getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(SimpleDateFormat publishTime) {
        this.publishTime = publishTime;
    }
}
