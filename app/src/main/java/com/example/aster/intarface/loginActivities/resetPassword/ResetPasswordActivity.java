package com.example.aster.intarface.loginActivities.resetPassword;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aster.intarface.loginActivities.resetPassword.InterfaceResetPasswordView;

public class ResetPasswordActivity extends AppCompatActivity implements InterfaceResetPasswordView {

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        return;
    }
}
