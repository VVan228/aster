package com.example.aster;

import android.content.Intent;
import android.widget.Toast;

import com.example.aster.events.Event;
import com.example.aster.events.Observer;
import com.example.aster.intarface.MainActivity;
import com.example.aster.intarface.loginActivities.LoginActivity;

public class LoginPresenter implements Observer {

    // get answer
    public void which_error(Event event) {
        switch (event.type){
            case signIn:
                if (event.message == null){
                    openIntent();
                }
                else {
                    showMessage(event.message);
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
