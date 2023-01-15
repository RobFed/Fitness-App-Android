package com.example.androidproject;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryDatabaseHelper extends SQLiteOpenHelper {
    public static final String HISTORY_TABLE_NAME = "TableOfCompletedChallenges";
    public static final String HISTORY_ID_COL = "ID";
    public static final String HISTORY_DATE_COL = "Date";
    public static final String HISTORY_REPS_COL = "Reps";
    public static final String HISTORY_NAME_COL = "Exercise";
    public static final String HISTORY_DATABASE_NAME = "CompletedChallengesDatabase.db";
    private static final int HISTORY_DATABASE_VERSION = 1;

    // Table creation statement.
    private static final String HISTORY_DATABASE_CREATE = "create table " + HISTORY_TABLE_NAME + "(" +
            HISTORY_ID_COL + " integer primary key autoincrement, " +
            HISTORY_DATE_COL + " text not null, " +
            HISTORY_REPS_COL + " text not null, " +
            HISTORY_NAME_COL + " text not null);";

    HistoryDatabaseHelper(Context context){
        // Superclass constructor call.
        super(context, HISTORY_DATABASE_NAME, null, HISTORY_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Executing the database creation statement.
        db.execSQL(HISTORY_DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Deleting table and calling onCreate to recreate the table.
        db.execSQL("DROP TABLE IF EXISTS " + HISTORY_TABLE_NAME);
        onCreate(db);
    }
}
