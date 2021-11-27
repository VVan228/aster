package com.example.aster.models;

import android.util.Log;

import static com.example.aster.events.LoginEvent.eventType;
import static com.example.aster.events.LoginEvent.eventType.delete;
import static com.example.aster.events.LoginEvent.eventType.logOut;
import static com.example.aster.events.LoginEvent.eventType.resetPassword;
import static com.example.aster.events.LoginEvent.eventType.signIn;
import static com.example.aster.events.LoginEvent.eventType.signUp;
import static com.example.aster.events.LoginEvent.eventType.updateEmail;
import static com.example.aster.events.LoginEvent.eventType.updatePassword;

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

        if(auth.getCurrentUser() != null){
            user = auth.getCurrentUser();
        }

        authListener = this::authListener;
    }

    void authListener(FirebaseAuth firebaseAuth){
        FirebaseUser user1 = firebaseAuth.getCurrentUser();
        if (user1 == null) {
            //log out event
            postEvent(logOut);
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
                        //signIn success event
                        postEvent(signIn);
                        user = auth.getCurrentUser();
                    } else {
                        //signIn failure event
                        postEvent(signIn, "wrong login and/or password");
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
                        //signUp success
                        postEvent(signUp);
                    } else {
                        //signUp failure
                        postEvent(signUp, "error");
                    }
                });
    }


    void resetPassword(String email){
        auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //resetPassword success event
                postEvent(resetPassword);
            } else {
                //resetPassword failure event
                postEvent(resetPassword, "wrong email");
            }
        });
    }

    void updateEmail(String email){
        user.updateEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //updateEmail success
                postEvent(updateEmail);
            } else {
                //updateEmail failure
                postEvent(updateEmail, "wrong email");
            }
        });
    }

    void updatePassword(String password){
        user.updatePassword(password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        //updatePassword success event
                        postEvent(updatePassword);
                    } else {
                        //updatePassword failure event
                        postEvent(updatePassword, "wrong password");
                    }
                });
    }



    void deleteUser(){
        user.delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //delete success
                postEvent(delete);
            } else {
                //delete failure
                postEvent(delete, "can't delete");
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


    void postEvent(eventType type, String message){
        EventsBus.post(new LoginEvent(type, message));
    }
    void postEvent(LoginEvent.eventType type){
        EventsBus.post(new LoginEvent(type));
    }


}
