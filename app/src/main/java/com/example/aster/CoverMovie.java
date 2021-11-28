package com.example.aster;

public class CoverMovie {

    private String Name, ImageURL;

    public CoverMovie(String name, String imageURL) {
        Name = name;
        ImageURL = imageURL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}
