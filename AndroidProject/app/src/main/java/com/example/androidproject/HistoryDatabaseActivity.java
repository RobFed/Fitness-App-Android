package com.example.androidproject;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryDatabaseActivity extends AppCompatActivity {
    private HistoryDatabaseImplementation dbImplementation;
    private ArrayAdapter<HistoryItem> adapter;
    private ArrayList<HistoryItem> historyList;
    ListView historyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_database);

        // Initializing the database implementation object and opening the database.
        dbImplementation = new HistoryDatabaseImplementation(this);
        dbImplementation.open();

        // Initializing the list view object.
        historyListView = findViewById(R.id.historyListView);
    }

    public void clearHistoryClicked(View view){
        // Deleting all the entries in the history database.
        dbImplementation.deleteAllHistory();

        // Clearing the adapter.
        adapter.clear();

        // Displaying toast telling the user the history has been cleared.
        Toast toast = Toast.makeText(getApplicationContext(), R.string.historyClearedToast, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        dbImplementation.open();

        // Getting the list of entries in the history database. /
        historyList = dbImplementation.getHistoryAsList();

        // Creating an array adapter.
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);

        // Assigning the adapter to the list view.
        historyListView.setAdapter(adapter);

        // Creating an onItemClickListener for when the user selects a challenge.
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Launching activity that shows details about the challenge selected.
                Intent intent = new Intent(getApplicationContext(), CompletedChallengeInformationActivity.class);
                String[] data = {historyList.get(position).getDate(), historyList.get(position).getExerciseName(), historyList.get(position).getReps(), String.valueOf(historyList.get(position).getId())};
                intent.putExtra("challengeInformation", data);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause(){
        super.onPause();
        dbImplementation.close();
    }
}