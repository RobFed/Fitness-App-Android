package com.example.androidproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterAgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_age);
        Log.i("EnterAgeActivity", "In OnCreate()");

        // Past variables that are important to creating the account!
        final String name = getIntent().getStringExtra("NAME_ID");
        final String email = getIntent().getStringExtra("EMAIL_ID");
        final String password = getIntent().getStringExtra("PASSWORD_ID");
        final String gender = getIntent().getStringExtra("GENDER_ID");

        Button btn_go_back = (Button) findViewById(R.id.button_go_back_3);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(EnterAgeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button btn_go_next = (Button) findViewById(R.id.button_go_next_3);
        btn_go_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText age = (EditText) findViewById(R.id.editText_age);
                EditText weight = (EditText) findViewById(R.id.editText_weight);
                EditText targetWeight = (EditText) findViewById(R.id.editText_targetWeight);

                String age_text = age.getText().toString();
                String weight_text = weight.getText().toString();
                String targetWeight_text = targetWeight.getText().toString();

                if (age_text.matches("") || weight_text.matches("") || targetWeight_text.matches("")){
                    Toast.makeText(EnterAgeActivity.this, "All Information is Required", Toast.LENGTH_SHORT).show();
                } else {
                    final Intent intent = new Intent(EnterAgeActivity.this, ReviewInfoActivity.class);
                    intent.putExtra("NAME_ID", name);
                    intent.putExtra("EMAIL_ID", email);
                    intent.putExtra("PASSWORD_ID", password);
                    intent.putExtra("GENDER_ID", gender);
                    intent.putExtra("AGE_ID", age_text);
                    intent.putExtra("WEIGHT_ID", weight_text);
                    intent.putExtra("TARGET_WEIGHT_ID", targetWeight_text);
                    startActivity(intent);
                }

            }
        });
    }
}