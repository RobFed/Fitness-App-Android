package com.example.androidproject;

public class Exercise {
    private int id;
    private String reps;
    private String exerciseName;

    // Creating various constructors.
    public Exercise(){}
    public Exercise(String reps, String exerciseName){
        this.reps = reps;
        this.exerciseName = exerciseName;
    }
    public Exercise(int id, String reps, String exerciseName){
        this.id = id;
        this.reps = reps;
        this.exerciseName = exerciseName;
    }

    public int getId(){
        return id;
    }

    public String getReps(){
        return reps;
    }

    public String getExerciseName(){
        return exerciseName;
    }

    public String toString(){return reps + " " + exerciseName;}
}
