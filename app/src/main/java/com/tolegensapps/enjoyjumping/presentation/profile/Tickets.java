package com.tolegensapps.enjoyjumping.presentation.profile;

public class Tickets {
    private String userId;
    private Boolean eveningSub;
    private int numberOfVisits;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getEveningSub() {
        return eveningSub;
    }

    public void setEveningSub(Boolean eveningSub) {
        this.eveningSub = eveningSub;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public static int transferToProgressValue(int numberOfVisits) {
        int progressBarValue;
        switch (numberOfVisits) {
            case 0:
                progressBarValue = 0;
                break;
            case 1:
                progressBarValue = 8;
                break;
            case 2:
                progressBarValue = 17;
                break;
            case 3:
                progressBarValue = 25;
                break;
            case 4:
                progressBarValue = 33;
                break;
            case 5:
                progressBarValue = 42;
                break;
            case 6:
                progressBarValue = 50;
                break;
            case 7:
                progressBarValue = 58;
                break;
            case 8:
                progressBarValue = 67;
                break;
            case 9:
                progressBarValue = 75;
                break;
            case 10:
                progressBarValue = 85;
                break;
            case 11:
                progressBarValue = 92;
                break;
            case 12:
                progressBarValue = 100;

                break;
            default:
                progressBarValue = 100;
        }

        return progressBarValue;
    }

    public static String checkEveningTrue(Boolean eveningTrue) {

        String txtEveningSub;

        if (eveningTrue)
            txtEveningSub = "Вечерний";
        else
            txtEveningSub = "Дневной";

        return txtEveningSub;
    }

}
