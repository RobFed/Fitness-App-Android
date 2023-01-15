package com.example.androidproject;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddExerciseActivity extends AppCompatActivity {
    private ExercisesDatabaseImplementation dbImplementation;
    EditText repsEditText;
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        // Initializing the EditText elements.
        repsEditText = findViewById(R.id.repsEditText);
        nameEditText = findViewById(R.id.nameEditText);

        // Initializing the database implementation object and opening the database.
        dbImplementation = new ExercisesDatabaseImplementation(this);
        dbImplementation.open();
    }

    public void addClicked(View view){
        // Getting the values for reps and exercise name that the user entered.
        String reps = repsEditText.getText().toString();
        String name = nameEditText.getText().toString();

        // Creating a new exercise object.
        Exercise exercise = new Exercise(reps, name);

        // Adding the exercise to the database.
        dbImplementation.addExercise(exercise);

        // Clearing the editTexts.
        repsEditText.setText("");
        nameEditText.setText("");

        // Displaying toast telling the user the exercise was added successfully to the database.
        Toast toast = Toast.makeText(getApplicationContext(), R.string.exerciseAddedToast, Toast.LENGTH_SHORT);
        toast.show();
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