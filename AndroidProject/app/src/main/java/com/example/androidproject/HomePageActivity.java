package com.example.androidproject;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button btn_challenge = (Button) findViewById(R.id.challengeButton);
        btn_challenge.setOnClickListener(v -> {
            final Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Button btn_tracker = (Button) findViewById(R.id.trackerButton);
        btn_tracker.setOnClickListener(v -> {
            final Intent intent = new Intent(HomePageActivity.this, FitnessTrackerActivity.class);
            startActivity(intent);
        });


        Button btn_log_out = (Button) findViewById(R.id.logoutButton);
        btn_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button btn_credits = (Button) findViewById(R.id.creditButton);
        btn_credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomePageActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Credits");
                builder.setMessage("Stevie Gallow 170405240\nRob Fedorowicz 170783690\nCristian Dragan 140721700\nNavjot Singh Dhaliwal 176803790");
                builder.show();

            }
        });
    }
}