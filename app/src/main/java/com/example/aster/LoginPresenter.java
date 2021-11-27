package com.example.aster;

import com.example.aster.events.Event;
import com.example.aster.events.Observer;

public class LoginPresenter implements Observer {

    public void which_error(Event event) {
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
    public void onEvent(Event event) {
        which_error(event);
    }
}
