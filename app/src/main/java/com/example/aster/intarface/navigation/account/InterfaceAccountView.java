package com.example.aster.intarface.navigation.account;

import com.example.aster.entities.Post;

public interface InterfaceAccountView {
    public void openPostCreationActivity();
    public void addPost(Post post);
    public void changeUsername(String title);
}
