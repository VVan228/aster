package com.example.aster.intarface.loginActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aster.R;

public class ResetPasswordActivity extends AppCompatActivity implements InterfaceResetPasswordView{

    private EditText reset_email;

    private Button reset_btn_back;
    private Button btn_reset_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        reset_email = findViewById(R.id.reset_email);
        reset_btn_back = findViewById(R.id.reset_btn_back);
        btn_reset_password = findViewById(R.id.btn_reset_password);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        return;
    }
}
