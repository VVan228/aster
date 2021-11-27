package com.example.aster.intarface.loginActivities.login;

import com.example.aster.events.Event;
import com.example.aster.events.EventsBus;
import com.example.aster.events.Observer;
import com.example.aster.models.Authorization;

public class LoginPresenter implements Observer {

    InterfaceLoginView view;
    Authorization model;

    LoginPresenter(InterfaceLoginView view){
        this.view = view;
        model = new Authorization();
        EventsBus.register(this);
    }

    // get answer
    public void which_error(Event event) {

        if (event.message == null){
            view.openMainActivity();
        }
        else {
            view.showMessage(event.message);
        }
    }

    @Override
    public void onEvent(Event event) {
        which_error(event);
    }

    public void onDestroy(){
        view = null;
        EventsBus.remove(this);
    }

    public void onSubmit(String email, String password){
        if(!validateEmail(email)){
            view.showInvalidEmail();
            return;
        }
        if(!validatePassword(password)){
            view.showInvalidPassword();
            return;
        }
        model.signIn(email, password);
    }

    private boolean validateEmail(String email){
        return true;
    }

    private boolean validatePassword(String password){
        return password.length()>=6;
    }
}