package com.example.aster.intarface.loginActivities.login;

import com.example.aster.events.Event;
import com.example.aster.events.Observer;

public class LoginPresenter implements Observer {

    InterfaceLoginView view;

    LoginPresenter(InterfaceLoginView view){
        this.view = view;
    }

    // get answer
    public void which_error(Event event) {

        if (event.message == null){
            view.MainActivityOpen();
        }
        else {
            view.showMessage(event.message);
        }
    }

    @Override
    public void onEvent(Event event) {
        which_error(event);
    }
}
