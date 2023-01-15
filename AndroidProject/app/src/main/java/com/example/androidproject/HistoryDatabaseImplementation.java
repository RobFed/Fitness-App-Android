package com.example.androidproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class HistoryDatabaseImplementation {
    private SQLiteDatabase database;
    private HistoryDatabaseHelper dbHelper;
    private String[] columns = {HistoryDatabaseHelper.HISTORY_ID_COL, HistoryDatabaseHelper.HISTORY_DATE_COL, HistoryDatabaseHelper.HISTORY_REPS_COL, HistoryDatabaseHelper.HISTORY_NAME_COL};

    HistoryDatabaseImplementation(Context context){
        // Calling the database constructor.
        dbHelper = new HistoryDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public void addHistoryItem(HistoryItem historyItem){
        // Getting the date, reps and exercise name from the history item object.
        String date = historyItem.getDate();
        String reps = historyItem.getReps();
        String name = historyItem.getExerciseName();

        // Creating a content values object and putting the appropriate data.
        ContentValues cValues = new ContentValues();
        cValues.put(HistoryDatabaseHelper.HISTORY_DATE_COL, date);
        cValues.put(HistoryDatabaseHelper.HISTORY_REPS_COL, reps);
        cValues.put(HistoryDatabaseHelper.HISTORY_NAME_COL, name);

        // Inserting the content values into the database.
        database.insert(HistoryDatabaseHelper.HISTORY_TABLE_NAME, null, cValues);
    }

    public void deleteHistoryItem(int id){
        // Deleting the specified entry from the database.
        database.delete(HistoryDatabaseHelper.HISTORY_TABLE_NAME, HistoryDatabaseHelper.HISTORY_ID_COL + "=" + id, null);
    }

    public void deleteAllHistory(){
        // Deleting the specified entry from the database.
        database.delete(HistoryDatabaseHelper.HISTORY_TABLE_NAME, null, null);
    }

    public ArrayList<HistoryItem> getHistoryAsList(){
        // Creating a cursor containing all the history entries in the database.
        Cursor cursor = database.query(HistoryDatabaseHelper.HISTORY_TABLE_NAME, columns, null, null, null, null, null);

        // Creating a list with all the exercises from the cursor.
        ArrayList<HistoryItem> historyList = new ArrayList<HistoryItem>();
        cursor.moveToFirst();
        for (int i=0; i<cursor.getCount(); i++){
            int id = cursor.getInt(0);
            String date = cursor.getString(1);
            String reps = cursor.getString(2);
            String name = cursor.getString(3);
            HistoryItem historyItem = new HistoryItem(id, date, reps, name);
            historyList.add(historyItem);
            cursor.moveToNext();
        }

        return historyList;
    }

    public long countItemsOnDate(String date){
        long count = DatabaseUtils.queryNumEntries(database, HistoryDatabaseHelper.HISTORY_TABLE_NAME, HistoryDatabaseHelper.HISTORY_DATE_COL + "= '" + date + "'");

        return count;
    }

    public long countExerciseCompleted(String name){
        long count = DatabaseUtils.queryNumEntries(database, HistoryDatabaseHelper.HISTORY_TABLE_NAME, HistoryDatabaseHelper.HISTORY_NAME_COL + "= '" + name + "'");

        return count;
    }
}
