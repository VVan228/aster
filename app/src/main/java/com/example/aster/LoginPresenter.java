package com.example.aster;

import android.content.Intent;
import android.widget.Toast;

import com.example.aster.events.Event;
import com.example.aster.events.Observer;
import com.example.aster.intarface.MainActivity;
import com.example.aster.intarface.loginActivities.LoginActivity;
import com.example.aster.intarface.loginActivities.LoginInterface;

public class LoginPresenter implements Observer {

    LoginInterface view;

    LoginPresenter(LoginInterface view){
        this.view = view;
    }

    // get answer
    public void which_error(Event event) {
        switch (event.type){
            case signIn:
                if (event.message == null){
                    view.openIntent();
                }
                else {
                    view.showMessage(event.message);
                }
                break;
            case signUp:

                break;
            case resetPassword:

                break;
        }


    }

    @Override
    public void onEvent(Event event) {
        which_error(event);
    }
}
