package com.example.aster.intarface.loginActivities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aster.R;
import com.example.aster.intarface.MainActivity;

public class ActivityLoginView extends AppCompatActivity implements InterfaceLoginView {
    EditText email;
    EditText password;
    Button submit;
    TextView resetPassword;
    Button signUp;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        submit = findViewById(R.id.login_submit);
        resetPassword = findViewById(R.id.login_reset_password);
        signUp = findViewById(R.id.login_signup);

        submit.setOnClickListener(v -> {
            presenter.onSubmit(email.getText().toString(),
                    password.getText().toString());
        });
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(ActivityLoginView.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        return;
    }

    @Override
    public void showInvalidEmail() {
        Toast.makeText(getApplicationContext(), "некорректная почта", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInvalidPassword() {
        Toast.makeText(getApplicationContext(), "пароль должен быть больше 6 символов", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}