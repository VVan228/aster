package com.example.aster.events;


import java.util.ArrayList;

public class EventsBus {
    public static ArrayList<Observer> observers = new ArrayList<>();

    public static void register(Observer observer){
        observers.add(observer);
    }

    public static void remove(Observer observer){
        observers.remove(observer);
    }

    public static void post(LoginEvent event){
        for(Observer i: observers){
            i.onEvent(event);
        }
    }
}