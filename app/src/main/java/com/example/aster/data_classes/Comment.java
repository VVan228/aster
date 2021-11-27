package com.example.aster.data_classes;

public class Comment {

    String author;
    String text;
    String sender;
    long time;

    public Comment(String author, String text, String sender, long time) {
        this.author = author;
        this.text = text;
        this.sender = sender;
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
