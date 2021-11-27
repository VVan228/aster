package com.example.aster;

import static com.example.aster.events.LoginEvent.eventType;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.aster.events.LoginEvent;
import com.example.aster.events.Observer;
import com.example.aster.intarface.MainActivity;

public class LoginPresenter implements Observer {

    public void which_error(LoginEvent event) {
        switch (event.type){
            case signIn:

                break;
            case signUp:

                break;
            case resetPassword:

                break;
        }


    }

    @Override
    public void onEvent(LoginEvent event) {
        which_error(event);
    }
}
