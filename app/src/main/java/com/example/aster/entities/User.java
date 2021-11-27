package com.example.aster.entities;

public class User {

    String name;
    String surname;
    String photo;
    String background;
    String email;

    public User(String name, String surname, String photo, String background, String email) {
        this.name = name;
        this.surname = surname;
        this.photo = photo;
        this.background = background;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

