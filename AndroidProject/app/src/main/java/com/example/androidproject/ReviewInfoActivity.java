package com.example.androidproject;


import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReviewInfoActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button btn_finish;
    TextView name_text;
    TextView email_text;
    TextView gender_text;
    TextView age_text;
    TextView weight_text;
    TextView targetWeight_text;
    TextView password_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("ReviewInfoActivity", "In OnCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_info);

        db = new DatabaseHelper(this);

        final String name = getIntent().getStringExtra("NAME_ID");
        final String email = getIntent().getStringExtra("EMAIL_ID");
        final String password = getIntent().getStringExtra("PASSWORD_ID");
        final String gender = getIntent().getStringExtra("GENDER_ID");
        final String age = getIntent().getStringExtra("AGE_ID");
        final String weight = getIntent().getStringExtra("WEIGHT_ID");
        final String targetWeight = getIntent().getStringExtra("TARGET_WEIGHT_ID");

        name_text = (TextView) findViewById(R.id.textView_name_edit);
        email_text = (TextView) findViewById(R.id.textView_email_edit);
        gender_text = (TextView) findViewById(R.id.textView_gender_edit);
        age_text = (TextView) findViewById(R.id.textView_age_edit);
        weight_text = (TextView) findViewById(R.id.textView_weight_edit);
        targetWeight_text = (TextView) findViewById(R.id.textView_targetWeight_edit);
        password_text = (TextView) findViewById(R.id.textView_password_edit);
        password_text.setVisibility(View.GONE);

        name_text.setText(name);
        email_text.setText(email);
        gender_text.setText(gender);
        age_text.setText(age);
        weight_text.setText(weight);
        targetWeight_text.setText(targetWeight);
        password_text.setText(password);

        Button btn_go_back = (Button) findViewById(R.id.button_go_back_4);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(ReviewInfoActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        btn_finish = (Button) findViewById(R.id.button_finish);
        AddData();

    }

    public void AddData() {
        btn_finish.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = db.insertData(name_text.getText().toString(), email_text.getText().toString(), gender_text.getText().toString(), age_text.getText().toString(), weight_text.getText().toString(), targetWeight_text.getText().toString(), password_text.getText().toString());
                        if (isInserted) {
                            Toast.makeText(ReviewInfoActivity.this, "Successfully Created Account", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ReviewInfoActivity.this, "Error: Account not created", Toast.LENGTH_LONG).show();
                        }
                        final Intent intent = new Intent(ReviewInfoActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

}