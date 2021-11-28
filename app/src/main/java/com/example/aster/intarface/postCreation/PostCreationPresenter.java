package com.example.aster.intarface.postCreation;

import com.example.aster.entities.Post;
import com.example.aster.events.Event;
import com.example.aster.events.Observer;
import com.example.aster.models.Authorization;
import com.example.aster.models.Data;

public class PostCreationPresenter {
    InterfacePostCreationView view;
    Data dataModel;
    Authorization authModel;

    PostCreationPresenter(InterfacePostCreationView view){
        this.view = view;
        dataModel = new Data();
        authModel = new Authorization();
    }

    public void onSubmit(String title, String text){
        long unixTime = System.currentTimeMillis() / 1000L;
        if(title.length()<=2 || text.length()<=2){
            view.showError("слишком короткий теккст");
            return;
        }

        dataModel.addPost(new Post(title,
                text,
                0,
                0,
                0,
                "",
                unixTime,
                "",
                authModel.getCurUserId()));
        view.finishActivity();
    }
}
