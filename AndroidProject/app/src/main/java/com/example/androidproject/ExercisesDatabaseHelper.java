package com.example.androidproject;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExercisesDatabaseHelper extends SQLiteOpenHelper {
    public static final String EXERCISES_TABLE_NAME = "TableOfExercises";
    public static final String EXERCISES_ID_COL = "ID";
    public static final String EXERCISES_REPS_COL = "Reps";
    public static final String EXERCISES_NAME_COL = "Exercise";
    public static final String EXERCISES_DATABASE_NAME = "ExercisesDatabase.db";
    private static final int EXERCISES_DATABASE_VERSION = 1;

    // Table creation statement.
    private static final String EXERCISES_DATABASE_CREATE = "create table " + EXERCISES_TABLE_NAME +
            "(" + EXERCISES_ID_COL + " integer primary key autoincrement, " +
            EXERCISES_REPS_COL + " text not null, " +
            EXERCISES_NAME_COL + " text not null);";

    ExercisesDatabaseHelper(Context context){
        // Superclass constructor call.
        super(context, EXERCISES_DATABASE_NAME, null, EXERCISES_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Executing the database creation statement.
        db.execSQL(EXERCISES_DATABASE_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Deleting table and calling onCreate to recreate the table.
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISES_TABLE_NAME);
        onCreate(db);
    }
}
