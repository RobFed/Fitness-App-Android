package com.example.androidproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ExercisesDatabaseImplementation dbImplementation;
    private Exercise currentChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing the database implementation object and opening the database.
        dbImplementation = new ExercisesDatabaseImplementation(this);
        dbImplementation.open();

        // Checking to see if the database is empty
        long count = dbImplementation.count();
        if (count==0){
            // Adding the original list of exercises if the database is empty.
            // This condition is met when the database is first created or if the user deletes all the exercises.
            dbImplementation.addOriginalExerciseList();
        }

        // Displaying a random challenge.
        currentChallenge = generateRandomChallenge();
    }

    public void randomChallengeClicked(View view){
        // Displaying a new random challenge.
        currentChallenge = generateRandomChallenge();
    }

    public void challengeCompletedClicked(View view){
        // Getting the current date and formatting it.
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String stringDate = dateFormat.format(date);

        // Getting information about the completed challenge.
        String reps = currentChallenge.getReps();
        String name = currentChallenge.getExerciseName();

        // Creating a HistoryItem object.
        HistoryItem item = new HistoryItem(stringDate, reps, name);

        // Creating a history database implementation object to add the completed challenge to the database.
        HistoryDatabaseImplementation dbImplementationHistory = new HistoryDatabaseImplementation(this);
        dbImplementationHistory.open();

        // Adding the completed challenge to the database.
        dbImplementationHistory.addHistoryItem(item);

        // Closing the database implementation object.
        dbImplementationHistory.close();

        // Displaying a new random challenge.
        currentChallenge = generateRandomChallenge();

        // Displaying snackbar to congratulate the user for completing the challenge.
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.challengeCompletedSnackbar, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public void challengesHistoryClicked(View view){
        // Launching the activity showing the history of completed challenges.
        Intent intent = new Intent(this, HistoryDatabaseActivity.class);
        startActivity(intent);
    }

    public void editChallengesDatabaseClicked(View view){
        // Launching the activity that allows the user to edit the database of exercises.
        Intent intent = new Intent(this, ExercisesDatabaseActivity.class);
        startActivity(intent);
    }

    public Exercise generateRandomChallenge(){
        // Generating and displaying a random challenge.
        int index = getRandomIndex();
        Exercise exercise = dbImplementation.getExerciseAtIndex(index);
        displayChallenge(exercise);

        return exercise;
    }


    public int getRandomIndex(){
        // Generating a random index.
        Random random = new Random();
        int bound = (int) dbImplementation.count();
        int index = random.nextInt(bound);

        return index;
    }

    public void displayChallenge(Exercise exercise){
        // Getting the reps and exercise name from the exercise object.
        String reps = exercise.getReps();
        String name = exercise.getExerciseName();

        // Initializing the text view objects.
        TextView repsTextView = findViewById(R.id.repsTextView);
        TextView exerciseTextView = findViewById(R.id.exerciseTextView);

        // Setting the reps and exercise text.
        repsTextView.setText(reps);
        exerciseTextView.setText(name);
    }

    @Override
    protected void onResume(){
        super.onResume();
        dbImplementation.open();
    }

    @Override
    protected void onPause(){
        super.onPause();
        dbImplementation.close();
    }
}