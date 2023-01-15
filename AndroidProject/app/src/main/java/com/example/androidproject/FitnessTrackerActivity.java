package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FitnessTrackerActivity extends AppCompatActivity  {
    ListView trackerListView;
    ProgressBar progressBar;
    EditText goal;
    TextView maxSteps;
    TextView calStatement;
    TextView currcalStat;
    TextView stepsTaken;
    int stepCount = 0;
    SensorManager sensorManager;
    Sensor sensor;
    private double previousMagnitude;
    private boolean running = false;
    float totalSteps = 0;
    float previousTotalSteps = 0;

    int goalSteps;
    Button goalButton;
    ArrayList<String> myList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_n);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        trackerListView = (ListView) findViewById(R.id.trackListView);
        goal = findViewById(R.id.goal);
        goalButton = findViewById(R.id.goalButton);
        maxSteps = findViewById(R.id.TotalSteps);
        stepsTaken = findViewById(R.id.stepsTaken);
        calStatement = findViewById(R.id.calStatement);
        calStatement.setText("5000 steps will burn 200 calories.");
        currcalStat = findViewById(R.id.currcalStatement);
        currcalStat.setText("Calories burnt so far: 0");

        myList = new ArrayList<>();
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        myList.add("  Sedentary = 5000 steps/day");
        myList.add("  Low Active = 5000 - 7,499 steps/day");
        myList.add("  Somewhat Active = 7,500 - 9,999 steps/day");
        myList.add("  Active = 10,000 steps/day");
        ArrayAdapter trackerAdapter = new ArrayAdapter(this, R.layout.tracker_list_row, myList);
        trackerListView.setAdapter(trackerAdapter);

        trackerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FitnessTrackerActivity.this,
                        "clicked item:" + position + " " +myList.get(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });


        goalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    progressBar.setMax(Integer.valueOf(goal.getText().toString()));
                    maxSteps.setText(goal.getText().toString());
                    //calSteps.setText(goal.getText().toString());
                    double cal = 0.04 * Integer.parseInt(goal.getText().toString());
                    calStatement.setText(maxSteps.getText() + " steps will burn " + cal + " calories.");
                    running = true;
                }catch (Exception e){
                    Toast toast = Toast.makeText(getApplicationContext() , "Cannot be a string", Toast.LENGTH_LONG);
                    toast.show();
                    e.printStackTrace();
                }
            }
        });


        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event != null & running) {
                    Toast toast = Toast.makeText(getApplicationContext(), " Movinf", Toast.LENGTH_LONG);

                    float x_acc = event.values[0];
                    float y_acc = event.values[1];
                    float z_acc = event.values[2];

                    double magnitude = Math.sqrt(Math.pow(x_acc, 2) + Math.pow(y_acc, 2) + Math.pow(z_acc, 2));
                    double deltaMagnitude = magnitude - previousMagnitude;
                    previousMagnitude = magnitude;

                    if (deltaMagnitude > 1) {
                        stepCount++;
                    }

                    stepsTaken.setText(stepCount+"");
                    progressBar.setProgress(stepCount);

                    double cal = 0.04 * stepCount;
                    currcalStat.setText("Calories burnt so far: " + cal);
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }






    @Override
    protected void onResume(){
        super.onResume();

    }
}
