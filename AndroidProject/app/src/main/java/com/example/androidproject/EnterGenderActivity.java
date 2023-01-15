package com.example.androidproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EnterGenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_gender);

        // Past variables that are important to creating the account!
        final String name = getIntent().getStringExtra("NAME_ID");
        final String email = getIntent().getStringExtra("EMAIL_ID");
        final String password = getIntent().getStringExtra("PASSWORD_ID");


        final RadioGroup radio_group = (RadioGroup) findViewById(R.id.radioGroup);
        final RadioButton radio_button_1 = (RadioButton) findViewById(R.id.radioButton1);
        final RadioButton radio_button_2 = (RadioButton) findViewById(R.id.radioButton2);
        final RadioButton radio_button_3 = (RadioButton) findViewById(R.id.radioButton3);
        final EditText edit_text_other = (EditText) findViewById(R.id.editTextOther);

        View.OnClickListener radio_button_1_listener = new View.OnClickListener(){
            public void onClick(View v){
                edit_text_other.setVisibility(View.INVISIBLE);
            }
        };

        View.OnClickListener radio_button_2_listener = new View.OnClickListener(){
            public void onClick(View v){
                edit_text_other.setVisibility(View.INVISIBLE);
            }
        };

        View.OnClickListener radio_button_3_listener = new View.OnClickListener(){
            public void onClick(View v){
                edit_text_other.setVisibility(View.VISIBLE);
            }
        };

        radio_button_1.setOnClickListener(radio_button_1_listener);
        radio_button_2.setOnClickListener(radio_button_2_listener);
        radio_button_3.setOnClickListener(radio_button_3_listener);


        Button btn_go_back = (Button) findViewById(R.id.button_go_back_2);
        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(EnterGenderActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button btn_go_next = (Button) findViewById(R.id.button_go_next_2);
        btn_go_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender_id = "";
                if (radio_group.getCheckedRadioButtonId() == -1){
                    Toast.makeText(EnterGenderActivity.this, "Please select a gender", Toast.LENGTH_SHORT).show();
                } else if (radio_button_3.isChecked() && edit_text_other.getText().toString().matches("")){
                    Toast.makeText(EnterGenderActivity.this, "Please enter preferred gender in Other", Toast.LENGTH_SHORT).show();
                } else {
                    if (radio_button_1.isChecked()) {
                        gender_id = radio_button_1.getText().toString();
                    } else if (radio_button_2.isChecked()) {
                        gender_id = radio_button_2.getText().toString();
                    } else {
                        gender_id = edit_text_other.getText().toString();
                    }
                    final Intent intent = new Intent(EnterGenderActivity.this, EnterAgeActivity.class);
                    intent.putExtra("NAME_ID", name);
                    intent.putExtra("EMAIL_ID", email);
                    intent.putExtra("PASSWORD_ID", password);
                    intent.putExtra("GENDER_ID", gender_id);
                    startActivity(intent);
                }
            }
        });
    }

}