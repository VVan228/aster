package com.example.aster.models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aster.entities.Comment;
import com.example.aster.entities.Post;
import com.example.aster.entities.Theme;
import com.example.aster.entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;

public class Data {
    private final DatabaseReference ref;
    private String uid;

    public Data() {
        this.ref = FirebaseDatabase.getInstance().getReference();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            this.uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

    }

    private DatabaseReference getUsers(){
        return ref.child("users");
    }
    private DatabaseReference getComments(){
        return ref.child("comments");
    }
    private DatabaseReference getPosts(){
        return ref.child("comments");
    }
    private DatabaseReference getThemes(){
        return ref.child("comments");
    }
    private DatabaseReference getSubscribers(){
        return ref.child("subscribers");
    }


    public void addUser(User userData){
        DatabaseReference query = getUsers().push();
        query.keepSynced(true);
        query.setValue(userData);
    }
    public void addComment(Comment comment, String postKey){
        DatabaseReference query = getComments().child(postKey).push();
        query.keepSynced(true);
        query.setValue(comment);
    }
    public void addPost(Post post){
        DatabaseReference query = getPosts().push();
        query.keepSynced(true);
        query.setValue(post);
    }
    public void addTheme(Theme theme){
        DatabaseReference query = getThemes().push();
        query.keepSynced(true);
        query.setValue(theme);
    }
    public void addSubscriber(String key){
        DatabaseReference query = getSubscribers().child(uid).push();
        query.keepSynced(true);
        query.setValue(key);
    }


    private void removeValue(DatabaseReference ref){
        ref.removeValue();
    }
    public void removeUser(){
        getUsers().child(uid).removeValue();
    }
    public void removeTheme(String key){
        getThemes().child(key).removeValue();
    }
    public void removePost(String key){
        getPosts().child(key).removeValue();
    }
    public void removeComment(String commentKey, String postKey){
        getComments().child(postKey).child(commentKey).removeValue();
    }
    public void removeSubscriber(String key){
        getSubscribers().child(uid).child(key).removeValue();
    }


}
