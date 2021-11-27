package com.example.aster.intarface.loginActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aster.R;

public class LoginActivity extends AppCompatActivity implements LoginInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


}