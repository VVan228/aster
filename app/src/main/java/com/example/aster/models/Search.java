package com.example.aster.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aster.entities.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Search {

    private final DatabaseReference ref;
    private final String uid;
    int timeCounterPost;
    long lastTimePost;
    ArrayList<Post> postRes;

    Search(){
        this.ref = FirebaseDatabase.getInstance().getReference();
        this.uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        lastTimePost = 0;

    }

    private DatabaseReference getMySubscriptions(){
        return ref.child("subscribers").child(uid);
    }
    private DatabaseReference getPostsSearch(){
        return ref.child("posts");
    }


    public ArrayList<Post> getPosts(){
        ArrayList<Post> res = postRes;
        postRes = null;
        return res;
    }

    public void updatePost(){
        lastTimePost = 0;
    }
    public void loadNewestPosts(int n){
        Query query;
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
}
