package com.example.aster.models;

import static com.example.aster.events.Event.eventType.postLoaded;
import static com.example.aster.events.Event.eventType.userLoaded;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.aster.entities.Comment;
import com.example.aster.entities.Post;
import com.example.aster.entities.PostSearch;
import com.example.aster.entities.Theme;
import com.example.aster.entities.User;
import com.example.aster.events.Event;
import com.example.aster.events.EventsBus;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Data {
    private final DatabaseReference ref;
    private String uid;


    Post loadedPost;
    User loadedUser;

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
        return ref.child("posts");
    }
    private DatabaseReference getThemes(){
        return ref.child("themes");
    }
    private DatabaseReference getSubscribers(){
        return ref.child("subscribers");
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
        addSearchPost(new PostSearch(
                query.getKey(),
                post.getAuthor(),
                post.getViews(),
                post.getTime(),
                post.getCategory())
        );
    }
    public void addTheme(Theme theme){
        DatabaseReference query = getThemes().push();
        query.keepSynced(true);
        query.setValue(theme);
    }
    public void addSubscriber(String toWhom, String who){
        DatabaseReference query = getSubscribers().child(toWhom).push();
        query.keepSynced(true);
        query.setValue(who);
    }
    public void addSearchPost(PostSearch post){
        DatabaseReference r = ref.child("search").child("posts");
        DatabaseReference q = r.push();
        q.keepSynced(true);
        q.setValue(post);
    }



    public void loadPost(String key){
        Query q = getPosts().child(key);

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Post post = snapshot.getValue(Post.class);
                if(post != null){
                    loadedPost = post;
                    postEvent(postLoaded);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public Post getPost(){
        return loadedPost;
    }

    public void loadUser(String key){
        Query q = getUsers().child(key);

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user != null){
                    loadedUser = user;
                    postEvent(userLoaded);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public User getUser(){
        return loadedUser;
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

    void postEvent(Event.eventType type, String message){
        EventsBus.post(new Event(type, message));
    }
    void postEvent(Event.eventType type){
        EventsBus.post(new Event(type));
    }

}
