package com.tolegensapps.enjoyjumping;

import java.util.Date;

public class ScheduleModel {
    private String mTimeStart;
    private String mTimeEnd;
    private String mTrainerName;
//    private int mTrainerImg;
    private int mVacantPlace;

    public ScheduleModel(String timeStart, String timeEnd, String trainerName, int vacantPlace) {
        mTimeStart = timeStart;
        mTimeEnd = timeEnd;
        mTrainerName = trainerName;
        mVacantPlace = vacantPlace;
    }

    public String getTimeStart() {
        return mTimeStart;
    }

    public void setTimeStart(String timeStart) {
        mTimeStart = timeStart;
    }

    public String getTimeEnd() {
        return mTimeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        mTimeEnd = timeEnd;
    }

    public String getTrainerName() {
        return mTrainerName;
    }

    public void setTrainerName(String trainerName) {
        mTrainerName = trainerName;
    }

//    public int getTrainerImg() {
//        return mTrainerImg;
//    }
//
//    public void setTrainerImg(int trainerImg) {
//        mTrainerImg = trainerImg;
//    }

    public int getVacantPlace() {
        return mVacantPlace;
    }

    public void setVacantPlace(int vacantPlace) {
        mVacantPlace = vacantPlace;
    }
}
