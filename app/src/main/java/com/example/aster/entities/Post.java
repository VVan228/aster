package com.example.aster.entities;

public class Post {

    String title;
    String link;
    String article;
    int likes;
    int dislikes;
    int views;
    String commentsLink;
    long time;

    public Post(String title, String link, String article, int likes, int dislikes, int views, String commentsLink, long time) {
        this.title = title;
        this.link = link;
        this.article = article;
        this.likes = likes;
        this.dislikes = dislikes;
        this.views = views;
        this.commentsLink = commentsLink;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getCommentsLink() {
        return commentsLink;
    }

    public void setCommentsLink(String commentsLink) {
        this.commentsLink = commentsLink;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
