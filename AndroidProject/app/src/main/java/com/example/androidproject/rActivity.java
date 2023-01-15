package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

public class rActivity extends AppCompatActivity {
    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHandler(this);
        database = dbHelper.getWritableDatabase();
        String[] columns = {DBHandler.KEY_MESSAGE};
        Cursor cursor = database.query(DBHandler.TABLE_NAME, columns, null, null, null, null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast() ){
            Log.i("ChatWindow.java", "SQL Message: " + cursor.getString(cursor.getColumnIndex(DBHandler.KEY_MESSAGE)));
            Log.i("ChatWindow.java", "Cursor's column count: " + cursor.getColumnCount());
            cursor.moveToNext();
        }

        for(int i = 0; i < cursor.getColumnCount(); i++){
            Log.i("ChatWindow.java", cursor.getColumnName(i));

        }

        cursor.close();
    }
    protected static final String ACTIVITY_NAME = "MainActivity";

    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}