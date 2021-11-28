package com.example.aster.intarface.postCreation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aster.R;

public class PostCreationActivity extends AppCompatActivity implements InterfacePostCreationView {

    EditText title;
    EditText text;
    Button submit;

    PostCreationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_creation);

        presenter = new PostCreationPresenter(this);

        title = findViewById(R.id.post_creation_title);
        text = findViewById(R.id.post_creation_text);
        submit = findViewById(R.id.post_creation_submit);

        submit.setOnClickListener(t->{
            presenter.onSubmit(title.getText().toString(),
                    text.getText().toString());
        });
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }


}