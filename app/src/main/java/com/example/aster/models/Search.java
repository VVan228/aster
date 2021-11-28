package com.example.aster.models;

import static com.example.aster.events.Event.eventType.postsUsersLoaded;
import static com.example.aster.events.Event.eventType.postsViaTimeLoaded;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aster.entities.Post;
import com.example.aster.entities.PostSearch;
import com.example.aster.events.Event;
import com.example.aster.events.EventsBus;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Search {

    private final DatabaseReference ref;
    private final String uid;
    int timeCounterPost;
    long lastTimePost;
    ArrayList<PostSearch> postRes;
    ArrayList<PostSearch> postUserRes;

    public Search(){
        this.ref = FirebaseDatabase.getInstance().getReference();
        this.uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        lastTimePost = 0;

    }

    private DatabaseReference getMySubscriptions(){
        return ref.child("subscribers").child(uid);
    }
    private DatabaseReference getPostsSearch(){
        return ref.child("search").child("posts");
    }


    public ArrayList<PostSearch> getLatestPosts(){
        ArrayList<PostSearch> res = postRes;
        postRes = null;
        return res;
    }
    public ArrayList<PostSearch> getUsersPosts(){
        ArrayList<PostSearch> res = postUserRes;
        postUserRes = null;
        return res;
    }

    public void updatePost(){
        lastTimePost = 0;
    }
    public void loadNewestPosts(int n){
        Query query;
        postRes = new ArrayList<>();
        if(lastTimePost == 0){
            query = getPostsSearch().orderByChild("time").limitToLast(n);
        }else{
            query = getPostsSearch().orderByChild("time").startAt(lastTimePost).limitToLast(n);
        }
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Post post = snapshot.getValue(Post.class);
                if(postRes.size()==n && post != null){
                    lastTimePost = post.getTime();
                    postEvent(postsViaTimeLoaded);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void loadUsersPosts(String key, int n){
        Query query = getPostsSearch().orderByChild("author").equalTo(key).limitToFirst(n);
        postUserRes = new ArrayList<>();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s: snapshot.getChildren()){
                    PostSearch post = s.getValue(PostSearch.class);
                    if(post != null){
                        Log.d("tag4me", snapshot.getChildrenCount() + " num");
                        postUserRes.add(post);
                        if(postUserRes.size() >= n){
                            postEvent(postsUsersLoaded);
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    void postEvent(Event.eventType type, String message){
        EventsBus.post(new Event(type, message));
    }
    void postEvent(Event.eventType type){
        EventsBus.post(new Event(type));
    }

}
