package com.example.androidproject;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CompletedChallengeInformationActivity extends AppCompatActivity {
    private String[] data;
    private HistoryDatabaseImplementation dbImplementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_challenge_information);

        // Getting the index from the previous activity.
        Intent intent = getIntent();
        if (intent!=null){
            if(intent.hasExtra("challengeInformation")){
                data = intent.getStringArrayExtra("challengeInformation");
            }
        }

        // Initializing the database implementation object.
        dbImplementation = new HistoryDatabaseImplementation(CompletedChallengeInformationActivity.this);
        dbImplementation.open();

        // Initializing the TextViews.
        TextView challengeDate = findViewById(R.id.challengeDate);
        TextView challengeName = findViewById(R.id.challengeName);
        TextView challengeReps = findViewById(R.id.challengeReps);
        TextView countChallengesOnDate = findViewById(R.id.countChallengesOnDate);
        TextView countExerciseCompleted = findViewById(R.id.countExerciseCompleted);

        // Getting the number of activities completed on the specific date.
        long countDate = dbImplementation.countItemsOnDate(data[0]);

        // Getting the number of times the specific exercise was completed.
        long countName = dbImplementation.countExerciseCompleted(data[1]);

        // Setting the text in the TextViews.
        challengeDate.setText(getString(R.string.challengeDateText) + " " + data[0]);
        challengeName.setText(getString(R.string.challengeNameText) + " " + data[1]);
        challengeReps.setText(getString(R.string.challengeRepsText) + " " + data[2]);
        countChallengesOnDate.setText(getString(R.string.countChallengesOnDateText) + " " + String.valueOf(countDate));
        countExerciseCompleted.setText(getString(R.string.countExerciseCompletedText) + " " + String.valueOf(countName));
    }

    public void deleteCompletedChallengeClicked(View view){
        // Displaying dialog to ask user for confirmation.
        AlertDialog.Builder builder = new AlertDialog.Builder(CompletedChallengeInformationActivity.this);
        builder.setMessage(R.string.removeHistoryItemConfirmationMessage)
                .setTitle(R.string.removeExerciseConfirmationTitle)
                .setPositiveButton(R.string.yesOption, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Deleting the challenge from the completed challenges database.
                        int index = Integer.parseInt(data[3]);
                        dbImplementation.deleteHistoryItem(index);
                        dbImplementation.close();

                        // Returning to the previous activity.
                        finish();
                    }
                })
                .setNegativeButton(R.string.noOption, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Nothing happens
                    }
                })
                .show();
    }
}