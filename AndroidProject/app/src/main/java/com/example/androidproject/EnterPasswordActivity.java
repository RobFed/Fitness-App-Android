package com.example.androidproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterPasswordActivity extends AppCompatActivity {

    EditText email;
    EditText password;

    String email_text;
    String password_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);

        email = findViewById(R.id.editText_email_final);
        password = findViewById(R.id.editText_password_final);

        email_text = getIntent().getStringExtra("EMAIL_ID");
        password_text = getIntent().getStringExtra("PASSWORD_ID");

        email.setText(email_text);

        Button btn_go_back = (Button) findViewById(R.id.button_go_back_final);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(EnterPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button btn_login = (Button) findViewById(R.id.button_login_final);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password_text.matches(password.getText().toString())){
                    Toast.makeText(EnterPasswordActivity.this, "Successfully logged in", Toast.LENGTH_LONG).show();
                    final Intent intent = new Intent(EnterPasswordActivity.this, HomePageActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(EnterPasswordActivity.this, "Incorrect Password", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}