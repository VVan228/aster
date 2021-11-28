package com.example.aster.entities;

public class PostSearch {

    String link;
    String author;
    int views;
    long date;
    String category;

    public PostSearch(String link, String author, int views, long date, String category) {
        this.link = link;
        this.author = author;
        this.views = views;
        this.date = date;
        this.category = category;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
