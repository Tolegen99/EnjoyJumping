package com.tolegensapps.enjoyjumping.presentation.timetable;

public class Timetable {
    public String time;
    public String trainerName;
    public int countOfClients;

    public Timetable() {
    }

    public Timetable(String time, String trainerName, int countOfClients) {
        this.time = time;
        this.trainerName = trainerName;
        this.countOfClients = countOfClients;
    }

    public String getTime() {
        return time;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public int getCountOfClients() {
        return countOfClients;
    }
}
