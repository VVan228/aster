package com.example.aster.data_classes;

public class Theme {

    String title;
    String text;
    String category;
    int likes;
    int dislikes;
    int views;
    long time;

    public Theme(String title, String text, String category, int likes, int dislikes, int views, long time) {
        this.title = title;
        this.text = text;
        this.category = category;
        this.likes = likes;
        this.dislikes = dislikes;
        this.views = views;
        this.time = time;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
