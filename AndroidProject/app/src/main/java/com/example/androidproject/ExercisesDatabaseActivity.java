package com.example.androidproject;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ExercisesDatabaseActivity extends AppCompatActivity {
    private ExercisesDatabaseImplementation dbImplementation;
    private ArrayAdapter<Exercise> adapter;
    private ArrayList <Exercise> exercisesList;
    ListView exercisesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_database);

        // Initializing the database implementation object and opening the database.
        dbImplementation = new ExercisesDatabaseImplementation(this);
        dbImplementation.open();

        // Initializing the list view object.
        exercisesListView = findViewById(R.id.exercisesListView);

    }

    public void addExerciseClicked(View view){
        // Starting the AddExerciseActivity.
        Intent intent = new Intent(this, AddExerciseActivity.class);
        startActivity(intent);
    }

    public void removeExerciseClicked(View view){
        // Starting the RemoveExerciseActivity.
        Intent intent = new Intent(this, RemoveExerciseActivity.class);
        startActivity(intent);
    }

    public void restoreDatabaseClicked(View view){
        // Displaying dialog to ask the user for confirmation.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.restoreOriginalDatabaseConfirmationTitle)
                .setMessage(R.string.restoreOriginalDatabaseConfirmationMessage)
                .setPositiveButton(R.string.yesOption, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Deleting all current exercises in the database.
                        dbImplementation.deleteAllExercises();

                        // Adding the original exercise list to the database.
                        dbImplementation.addOriginalExerciseList();

                        // Updating the list of exercises displayed in the list view.
                        adapter.clear();
                        exercisesList = dbImplementation.getAllExercisesAsList();
                        for (int i=0; i<exercisesList.size(); i++){
                            adapter.add(exercisesList.get(i));
                        }

                        // Displaying toast telling the user the original exercise list has been restored.
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.originalExerciseListRestoredToast, Toast.LENGTH_SHORT);
                        toast.show();
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

    @Override
    protected void onResume(){
        super.onResume();
        dbImplementation.open();

        // Getting the list of exercises in the database.
        exercisesList = dbImplementation.getAllExercisesAsList();

        // Creating an array adapter.
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exercisesList);

        // Assigning the adapter to the list view.
        exercisesListView.setAdapter(adapter);
    }

    @Override
    protected void onPause(){
        super.onPause();
        dbImplementation.close();
    }
}