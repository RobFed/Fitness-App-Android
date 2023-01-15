package com.example.androidproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Workout.db";
    public static final String TABLE_NAME = "Workout";
    public static final String COLUMN_ID = "WorkoutID";
    public static final String COLUMN_NAME = "WorkoutName";
    public static final String COLUMN_STATS = "WorkoutStats";
    public static final String KEY_MESSAGE = "Workout";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table " + TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement," + COLUMN_NAME + " text not null);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public String loadHandler(){
        String result = "";
        String query = "Select*FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            int result_2 = cursor.getInt(2);
            result += String.valueOf(result_0) + " " + result_1 + String.valueOf(result_2) + System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addHandler(Workout workout){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, workout.getWorkoutID());
        values.put(COLUMN_NAME, workout.getWorkoutName());
        values.put(COLUMN_STATS, workout.getWorkoutStats());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public Workout findHandler(String workoutname){
        //SELECT * FROM table_name WHERE column_name
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + "'" + workoutname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Workout workout = new Workout();
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            workout.setWorkoutID(Integer.parseInt(cursor.getString(0)));
            workout.setWorkoutName(cursor.getString(1));
            workout.setWorkoutStats(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            workout = null;
        }
        db.close();
        return workout;
    }

}
