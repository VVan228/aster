package com.example.aster.intarface.loginActivities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.aster.R;
import com.example.aster.intarface.MainActivity;

public class ActivityLoginView extends AppCompatActivity implements InterfaceLoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public void MainActivityOpen() {
        Intent intent = new Intent(ActivityLoginView.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        return;
    }
}