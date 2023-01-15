package com.example.androidproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Button btn_go_back = (Button) findViewById(R.id.button_go_back_1);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        Button btn_go_next = (Button) findViewById(R.id.button_next_1);
        btn_go_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText password_1 = (EditText) findViewById(R.id.editText_password);
                EditText password_2 = (EditText) findViewById(R.id.editText_password_2);
                EditText name = (EditText) findViewById(R.id.editText_name);
                EditText email = (EditText) findViewById(R.id.editText_email);

                String password_1_text = password_1.getText().toString();
                String password_2_text = password_2.getText().toString();

                String name_text = name.getText().toString();
                String email_text = email.getText().toString();

                if (name_text.matches("") || email_text.matches("") || password_1_text.matches("") || password_2_text.matches("")){
                    Toast.makeText(CreateAccountActivity.this, "All Information is Required", Toast.LENGTH_SHORT).show();
                } else if (!password_1_text.matches(password_2_text)){
                    Toast.makeText(CreateAccountActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    final Intent intent = new Intent(CreateAccountActivity.this, EnterGenderActivity.class);
                    intent.putExtra("NAME_ID", name_text);
                    intent.putExtra("EMAIL_ID", email_text);
                    intent.putExtra("PASSWORD_ID", password_1_text);
                    startActivity(intent);
                }
            }
        });
    }
}