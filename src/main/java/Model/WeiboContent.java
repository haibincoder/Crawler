package Model;

public class WeiboContent {
    private String user;
    private String content;
    private String link;
    private int comment;
    private int upvote;
    private int Forward;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getUpvote() {
        return upvote;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }

    public int getForward() {
        return Forward;
    }

    public void setForward(int forward) {
        Forward = forward;
    }
}
