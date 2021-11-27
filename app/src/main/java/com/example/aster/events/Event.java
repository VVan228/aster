package com.example.aster.events;

public class Event {


    public String message;
    public eventType type;
    public enum eventType{
        logOut,
        signIn,
        signUp,
        resetPassword,
        updateEmail,
        updatePassword,
        delete,
    }

    public Event(eventType type, String message) {
        this.message = message;
        this.type = type;
    }

    public Event(eventType type) {
        this.message = null;
        this.type = type;
    }

}
