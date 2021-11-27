package com.example.aster.models;

import com.example.aster.entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Authorization {

    private final FirebaseAuth auth;
    public FirebaseUser user;
    private FirebaseAuth.AuthStateListener authListener;

    Authorization(){
        auth = FirebaseAuth.getInstance();
        authListener = firebaseAuth -> {
            FirebaseUser user1 = firebaseAuth.getCurrentUser();
            if (user1 == null) {
                //TODO: log out event
            }
        };
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

    void changePassword(String email){
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        //TODO: changePassword siccess
                    } else {
                        //TODO: changePassword failure
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



}
