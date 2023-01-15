package com.example.androidproject;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RemoveExerciseActivity extends AppCompatActivity {
    private ExercisesDatabaseImplementation dbImplementation;
    private ArrayAdapter<Exercise> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_exercise);

        // Initializing the database implementation object and opening the database.
        dbImplementation = new ExercisesDatabaseImplementation(this);
        dbImplementation.open();

        // Initializing the list view object.
        ListView removeExerciseListView = findViewById(R.id.removeExerciseListView);

        // Getting the list of exercises in the database.
        ArrayList <Exercise> exercisesList = dbImplementation.getAllExercisesAsList();

        // Creating an array adapter.
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exercisesList);

        // Assigning the adapter to the list view.
        removeExerciseListView.setAdapter(adapter);

        // Creating an onItemClickListener for when the user selects an exercise to remove.
        removeExerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Displaying dialog to ask user for confirmation.
                AlertDialog.Builder builder = new AlertDialog.Builder(RemoveExerciseActivity.this);
                builder.setMessage(R.string.removeExerciseConfirmationMessage)
                        .setTitle(R.string.removeExerciseConfirmationTitle)
                        .setPositiveButton(R.string.yesOption, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Removing the selected item from the database.
                                Exercise exercise = adapter.getItem(position);
                                int exerciseId = exercise.getId();
                                dbImplementation.deleteExercise(exerciseId);

                                // Removing the exercise from the exercises list.
                                adapter.remove(exercise);

                                // Displaying toast that tells the user the exercise was deleted.
                                Toast toast = Toast.makeText(getApplicationContext(), R.string.exerciseDeletedToast, Toast.LENGTH_SHORT);
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
        });
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