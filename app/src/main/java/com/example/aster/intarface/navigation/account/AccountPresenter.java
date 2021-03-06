package com.example.aster.intarface.navigation.account;

import android.util.Log;

import com.example.aster.entities.Post;
import com.example.aster.entities.PostSearch;
import com.example.aster.entities.User;
import com.example.aster.events.Event;
import com.example.aster.events.EventsBus;
import com.example.aster.events.Observer;
import com.example.aster.models.Authorization;
import com.example.aster.models.Data;
import com.example.aster.models.Search;

import java.util.ArrayList;

public class AccountPresenter implements Observer {
    InterfaceAccountView view;
    Authorization authModel;
    Search searchModel;
    Data dataModel;

    boolean nameLoaded;


    Post p;
    User u;


    AccountPresenter(InterfaceAccountView view){
        nameLoaded = false;

        this.view = view;
        authModel = new Authorization();
        searchModel = new Search();
        dataModel = new Data();
        EventsBus.register(this);
        Log.d("tag4me", authModel.getCurUserId()+" 0");
        searchModel.loadUsersPosts(authModel.getCurUserId(), 1);

        dataModel.loadUser(authModel.getCurUserId());
    }

    private void loadPosts(ArrayList<PostSearch> posts){
        for(PostSearch p: posts){
            Log.d("tag4me", p.toString());
            dataModel.loadPost(p.getLink());
            dataModel.loadUser(p.getAuthor());
        }
    }



    void check(){
        if(u!=null && p!=null){
            p.setAuthor(u.getName() + " " + u.getSurname());
            view.addPost(p);
            p = null;
            u = null;
        }
    }


    public void onFabClick(){
        view.openPostCreationActivity();
    }
    public void onDestroy(){
        view = null;
    }

    @Override
    public void onEvent(Event event) {
        switch (event.type){
            case postsUsersLoaded:
                loadPosts(searchModel.getUsersPosts());
                break;
            case postLoaded:
                //view.addPost(dataModel.getPost());
                p = dataModel.getPost();
                check();
                break;
            case userLoaded:
                if(!nameLoaded){
                    view.changeUsername(dataModel.getUser().getName() +
                            " " +
                            dataModel.getUser().getSurname());
                    nameLoaded = true;
                }else{
                    u = dataModel.getUser();
                    check();
                }

                break;
        }
    }
}
