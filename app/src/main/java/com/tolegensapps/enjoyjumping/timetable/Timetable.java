package com.tolegensapps.enjoyjumping.timetable;

import java.util.Date;

public class Timetable {
    public Date time;
    public String trainerName;
    public int countOfClients;

    public Timetable() {
    }

    public Timetable(Date time, String trainerName, int countOfClients) {
        this.time = time;
        this.trainerName = trainerName;
        this.countOfClients = countOfClients;
    }

    public Date getTime() {
        return time;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public int getCountOfClients() {
        return countOfClients;
    }
}
