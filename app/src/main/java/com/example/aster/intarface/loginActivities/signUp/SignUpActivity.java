package com.example.aster.intarface.loginActivities.signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aster.R;
import com.example.aster.intarface.MainActivity;

public class SignUpActivity extends AppCompatActivity implements InterfaceSignUpView {

    private ImageView sign_photo;

    private Button sign_bth_add_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sign_photo = findViewById(R.id.sign_photo);
        sign_bth_add_photo = findViewById(R.id.sign_bth_add_photo);
    }

    @Override
    public void MainActivityOpen() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        return;
    }
}