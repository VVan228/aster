package com.example.aster.intarface.loginActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aster.R;
import com.example.aster.intarface.MainActivity;

public class ActivityLoginView extends AppCompatActivity implements InterfaceLoginView {


    private EditText login_email;
    private EditText login_pssword;

    private Button first_btn_login;
    private TextView login_btn_reset_password;
    private Button login_btn_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_pssword = findViewById(R.id.login_password);

        first_btn_login = findViewById(R.id.first_btn_login);
        login_btn_reset_password = findViewById(R.id.login_btn_reset_password);
        login_btn_signup = findViewById(R.id.login_btn_signup);
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