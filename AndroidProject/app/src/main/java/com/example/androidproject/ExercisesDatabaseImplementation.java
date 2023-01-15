package com.example.androidproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ExercisesDatabaseImplementation {
    private SQLiteDatabase database;
    private ExercisesDatabaseHelper dbHelper;
    private String[] columns = {ExercisesDatabaseHelper.EXERCISES_ID_COL, ExercisesDatabaseHelper.EXERCISES_REPS_COL, ExercisesDatabaseHelper.EXERCISES_NAME_COL};

    ExercisesDatabaseImplementation(Context context){
        // Calling the database constructor.
        dbHelper = new ExercisesDatabaseHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public void addExercise(Exercise exercise){
        // Getting the reps and exercise name from the exercise object.
        String reps = exercise.getReps();
        String name = exercise.getExerciseName();

        // Creating a content values object and putting the appropriate data.
        ContentValues cValues = new ContentValues();
        cValues.put(ExercisesDatabaseHelper.EXERCISES_REPS_COL, reps);
        cValues.put(ExercisesDatabaseHelper.EXERCISES_NAME_COL, name);

        // Inserting the content values into the database.
        database.insert(ExercisesDatabaseHelper.EXERCISES_TABLE_NAME, null, cValues);
    }

    public void addOriginalExerciseList(){
        // Populating the database with the original exercise list.
        String[] originalExerciseList = ExercisesData.getOriginalExerciseList();
        int count = originalExerciseList.length;
        for (int i=0; i<count; i++){
            String exerciseString = originalExerciseList[i];
            String reps = exerciseString.substring(0, exerciseString.indexOf('/'));
            String name = exerciseString.substring(exerciseString.indexOf('/')+1);
            Exercise exercise = new Exercise(reps, name);
            addExercise(exercise);
        }
    }

    public void deleteExercise(int id){
        // Deleting the exercise from the database.
        database.delete(ExercisesDatabaseHelper.EXERCISES_TABLE_NAME, ExercisesDatabaseHelper.EXERCISES_ID_COL + " = " + id, null);
    }

    public void deleteAllExercises(){
        // Deleting all the entries in the table.
        database.delete(ExercisesDatabaseHelper.EXERCISES_TABLE_NAME,null,null);
    }

    public long count(){
        // Getting the number of exercises in the database.
        long count = DatabaseUtils.queryNumEntries(database, ExercisesDatabaseHelper.EXERCISES_TABLE_NAME);

        return count;
    }

    public Exercise getExerciseAtIndex(int index){
        // Getting all the exercises in the database.
        Cursor cursor = getAllExercisesAsCursor();

        // Moving the cursor to the appropriate index.
        cursor.moveToPosition(index);

        // Getting the information of the selected exercise.
        int id = cursor.getInt(0);
        String reps = cursor.getString(1);
        String name = cursor.getString(2);

        // Creating the exercise object.
        Exercise exercise = new Exercise(id, reps, name);

        return exercise;
    }

    public Cursor getAllExercisesAsCursor(){
        // Creating a cursor with all the exercises in the database.
        Cursor cursor = database.query(ExercisesDatabaseHelper.EXERCISES_TABLE_NAME, columns, null, null, null, null,null);

        return cursor;
    }

    public ArrayList <Exercise> getAllExercisesAsList(){
        // Getting cursor containing all the exercises.
        Cursor cursor = getAllExercisesAsCursor();

        // Creating a list with all the exercises from the cursor.
        ArrayList <Exercise> exercisesList = new ArrayList<>();
        cursor.moveToFirst();
        for (int i=0; i<cursor.getCount(); i++){
            int id = cursor.getInt(0);
            String reps = cursor.getString(1);
            String name = cursor.getString(2);
            Exercise exercise = new Exercise(id, reps, name);
            exercisesList.add(exercise);
            cursor.moveToNext();
        }

        return exercisesList;
    }
}
