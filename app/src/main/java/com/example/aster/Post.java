package com.example.aster;

public class Post {
    private String autor; // Имя автора
    private int likes;  // Колличество лайков
    private String views; // Колличество просмотров
    private String number; // Индивидуальный номер поста

    public Post(String autor, int likes, String views, String number){

        this.autor = autor;
        this.likes = likes;
        this.views = views;
        this.number = number;
    }

    public String getAutor() {
        return this.autor;
    }

    public int getLikes() {
        return this.likes;
    }

    public String getNumber() {
        return this.number;
    }

    public String getViews() {
        return this.views;
    }

}
