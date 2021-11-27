package com.example.aster.models;

import android.util.Log;

import com.example.aster.entities.User;
import com.example.aster.events.EventsBus;
import com.example.aster.events.LoginEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Authorization {

    private final FirebaseAuth auth;
    public FirebaseUser user;
    private FirebaseAuth.AuthStateListener authListener;

    Authorization(){
        auth = FirebaseAuth.getInstance();
        authListener = this::authListener;
    }

    void authListener(FirebaseAuth firebaseAuth){
        FirebaseUser user1 = firebaseAuth.getCurrentUser();
        if (user1 == null) {
            //TODO: log out event
        }
    }

    public boolean isAuthorized(){
        return auth.getCurrentUser() != null;
    }

    public void signOut(){
        auth.signOut();
    }


    void signIn(String email, String password){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        //TODO: signIn success event
                    } else {
                        //TODO: signIn failure event
                    }
                });
    }

    void signUp(String email, String password, User userData){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference query = db.child("").push();
                        query.keepSynced(true);
                        query.setValue(userData);
                        //TODO: signUp success
                    } else {
                        //TODO: signUp failure
                    }
                });
    }


    void resetPassword(String email){
        auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //TODO: resetPassword success event
            } else {
                //TODO: resetPassword failure event
            }
        });
    }

    void updateEmail(String email){
        user.updateEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //TODO: updateEmail success
            } else {
                //TODO: updateEmail failure
            }
        });
    }

    void updatePassword(String password){
        user.updatePassword(password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        //TODO: updatePassword success event
                    } else {
                        //TODO: updatePassword failure event
                    }
                });
    }



    void deleteUser(){
        user.delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //TODO: delete success
            } else {
                //TODO: delete failure
            }
        });
    }


    //используется в onStart
    void addAuthStateListener(){
        auth.addAuthStateListener(authListener);
    }

    //используется в onStop
    void removeAuthStateListener(){
        if(authListener != null){
            auth.removeAuthStateListener(authListener);
        }
    }


    void postEvent(LoginEvent.eventType type, String message){
        EventsBus.post(new LoginEvent(type, message));
    }
    void postEvent(LoginEvent.eventType type){
        EventsBus.post(new LoginEvent(type));
    }


}
