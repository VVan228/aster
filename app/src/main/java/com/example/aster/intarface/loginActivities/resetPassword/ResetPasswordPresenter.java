package com.example.aster.intarface.loginActivities.resetPassword;

import com.example.aster.events.Event;
import com.example.aster.events.Observer;

public class ResetPasswordPresenter implements Observer {

    ResetPasswordActivity view;

    ResetPasswordPresenter(ResetPasswordActivity view){
        this.view = view;
    }

    // get answer
    public void which_error(Event event) {

        view.showMessage(event.message);
    }

    @Override
    public void onEvent(Event event) {
        which_error(event);
    }
}