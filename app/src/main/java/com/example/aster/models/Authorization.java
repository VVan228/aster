package com.example.aster.models;

import static com.example.aster.events.Event.eventType;
import static com.example.aster.events.Event.eventType.delete;
import static com.example.aster.events.Event.eventType.logOut;
import static com.example.aster.events.Event.eventType.resetPassword;
import static com.example.aster.events.Event.eventType.signIn;
import static com.example.aster.events.Event.eventType.signUp;
import static com.example.aster.events.Event.eventType.updateEmail;
import static com.example.aster.events.Event.eventType.updatePassword;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.aster.entities.User;
import com.example.aster.events.EventsBus;
import com.example.aster.events.Event;
import com.example.aster.intarface.loginActivities.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Authorization {

    private final FirebaseAuth auth;
    private final DatabaseReference ref;
    public FirebaseUser user;
    private final FirebaseAuth.AuthStateListener authListener;

    public Authorization(){
        auth = FirebaseAuth.getInstance();
        FirebaseDatabase dbase = FirebaseDatabase.getInstance();
        ref = dbase.getReference();

        if(auth.getCurrentUser() != null){
            user = auth.getCurrentUser();
        }

        authListener = this::authListener;
    }

    public void authListener(FirebaseAuth firebaseAuth){
        FirebaseUser user1 = firebaseAuth.getCurrentUser();
        if (user1 == null) {
            //log out event
            postEvent(logOut);
        }
    }

    public String getEmail(){
        return user.getEmail();
    }

    public boolean isAuthorized(){
        return auth.getCurrentUser() != null;
    }

    public void signOut(){
        auth.signOut();
    }


    public void signIn(String email, String password){
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

    public void signUp(String email, String password, User user){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        //signUp success
                        postEvent(signUp);
                    } else {
                        //signUp failure
                        postEvent(signUp, "error " + task.getException().getMessage());
                    }
                }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                DatabaseReference query = FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("users")
                        .child(getCurUserId());
                query.keepSynced(true);
                query.setValue(user);
            }
        });
    }


    public void resetPassword(String email){
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

    public void updateEmail(String email){
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

    public void updatePassword(String password){
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



    public void deleteUser(){
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
    public void addAuthStateListener(){
        auth.addAuthStateListener(authListener);
    }

    //используется в onStop
    public void removeAuthStateListener(){
        if(authListener != null){
            auth.removeAuthStateListener(authListener);
        }
    }

    public String getCurUserId(){
        return Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
    }


    void postEvent(eventType type, String message){
        EventsBus.post(new Event(type, message));
    }
    void postEvent(Event.eventType type){
        EventsBus.post(new Event(type));
    }


}
