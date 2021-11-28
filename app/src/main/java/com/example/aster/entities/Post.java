package com.example.aster.entities;

public class Post {

    String title;
    String text;
    int likes;
    int dislikes;
    int views;
    String commentsLink;
    long time;
    String category;
    String author;

    public Post(String title, String text, int likes, int dislikes, int views, String commentsLink, long time, String category, String author) {
        this.title = title;
        this.text = text;
        this.likes = likes;
        this.dislikes = dislikes;
        this.views = views;
        this.commentsLink = commentsLink;
        this.time = time;
        this.category = category;
        this.author = author;
    }

    public Post() {}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
