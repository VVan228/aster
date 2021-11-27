package com.example.aster.intarface.loginActivities;

import android.content.Intent;
import android.widget.Toast;

public class ResetPasswordActivity implements InterfaceResetPasswordView{

    @Override
    public void showMessage(String message) {
        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        return;
    }
}
