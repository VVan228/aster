package com.example.aster;

import com.example.aster.events.Event;
import com.example.aster.events.Observer;
import com.example.aster.intarface.loginActivities.SignUpActivity;

public class SignUpPresenter implements Observer {

    SignUpActivity view;

    SignUpPresenter(SignUpActivity view){
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
