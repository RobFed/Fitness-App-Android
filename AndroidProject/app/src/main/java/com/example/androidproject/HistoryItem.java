package com.example.androidproject;


public class HistoryItem {
    private int id;
    private String date;
    private String reps;
    private String exerciseName;

    // Creating various constructors.
    public HistoryItem(String date, String reps, String exerciseName){
        this.date = date;
        this.reps = reps;
        this.exerciseName = exerciseName;
    }
    public HistoryItem(int id, String date, String reps, String exerciseName){
        this.id = id;
        this.date = date;
        this.reps = reps;
        this.exerciseName = exerciseName;
    }

    public int getId(){return id;}

    public String getDate(){return date;}

    public String getReps(){return reps;}

    public String getExerciseName(){return exerciseName;}

    public String toString(){return date + " - " + reps + " " + exerciseName;}
}
