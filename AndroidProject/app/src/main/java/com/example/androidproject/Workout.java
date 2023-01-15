package com.example.androidproject;

public class Workout {
    //fields
    private String workoutName;
    private int workoutStats;
    private int workoutID;

    //const
    public Workout(){}
    public Workout(int stats, String name){
        this.workoutStats = stats;
        this.workoutName = name;
    }

    public void setWorkoutName(String name){
        this.workoutName = name;
    }
    public String getWorkoutName(){
        return this.workoutName;
    }

    public void setWorkoutStats(int stats){
        this.workoutStats = stats;
    }
    public int getWorkoutStats(){
        return this.workoutStats;
    }

    public void setWorkoutID(int id){
        this.workoutID = id;
    }
    public int getWorkoutID(){
        return this.workoutID;
    }
}
