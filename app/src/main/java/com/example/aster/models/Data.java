package com.example.aster.models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aster.entities.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;

public class Data {
    private final DatabaseReference ref;

    public Data() {
        this.ref = FirebaseDatabase.getInstance().getReference();
    }

    private DatabaseReference getUsers(){
        return ref.child("users");
    }
    private void removeValue(DatabaseReference ref){
        ref.removeValue();
    }

    public void addUser(User userData){
        DatabaseReference query = getUsers().push();
        query.keepSynced(true);
        query.setValue(userData);
    }

    public void removeUser(String email){
        Query query = getUsers().orderByChild("email").equalTo(email);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                removeValue(getUsers().child(Objects.requireNonNull(snapshot.getKey())));
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
