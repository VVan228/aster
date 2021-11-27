package com.example.aster.events;

public class LoginEvent {


    String message;
    eventType type;
    public enum eventType{
        logOut,
        signIn,
        signUp,
        resetPassword,
        updateEmail,
        updatePassword,
        delete,
    }

    public LoginEvent(eventType type, String message) {
        this.message = message;
        this.type = type;
    }

    public LoginEvent(eventType type) {
        this.message = null;
        this.type = type;
    }


}
