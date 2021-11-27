package com.example.aster.intarface.loginActivities.signUp;

import com.example.aster.entities.User;
import com.example.aster.events.Event;
import com.example.aster.events.EventsBus;
import com.example.aster.events.Observer;
import com.example.aster.models.Authorization;
import com.example.aster.models.Data;

public class SignUpPresenter implements Observer {

    SignUpActivity view;
    Authorization model;
    Data dataModel;

    SignUpPresenter(SignUpActivity view){
        this.view = view;
        this.model = new Authorization();
        this.dataModel = new Data();
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



    public void onSubmit(String email, String password, User user) {
        model.signUp(email, password);
        dataModel.addUser(user);
    }

    public void onDestroy() {
        view = null;
        EventsBus.remove(this);
    }
}
