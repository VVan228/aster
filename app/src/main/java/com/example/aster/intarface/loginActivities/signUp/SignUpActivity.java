package com.example.aster.intarface.loginActivities.signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aster.R;
import com.example.aster.entities.User;
import com.example.aster.intarface.MainActivity;

public class SignUpActivity extends AppCompatActivity implements InterfaceSignUpView {

    private ImageView sign_up_photo;
    private Button sign_up_add_photo;

    EditText email;
    EditText surname;
    EditText forename;
    EditText password;
    EditText password2;
    Button submit;

    SignUpPresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        presenter = new SignUpPresenter(this);

        sign_up_photo = findViewById(R.id.sign_photo);
        sign_up_add_photo = findViewById(R.id.sign_bth_add_photo);

        email = findViewById(R.id.sign_up_email);
        surname = findViewById(R.id.sign_up_surname);
        forename = findViewById(R.id.sign_up_forename);
        password = findViewById(R.id.sign_up_password);
        password2 = findViewById(R.id.sign_up_password_for_checking);
        submit = findViewById(R.id.sign_in_submit);

        submit.setOnClickListener(t->{
            if(!password.getText().toString().equals(password2.getText().toString())){
                showMessage("пароли должны совпадать");
            }
            presenter.onSubmit(
                    email.getText().toString(),
                    password.getText().toString(),
                    new User(
                        forename.getText().toString(),
                        surname.getText().toString(),
                        "","",
                        email.getText().toString()
                    )
            );
        });

    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        return;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}