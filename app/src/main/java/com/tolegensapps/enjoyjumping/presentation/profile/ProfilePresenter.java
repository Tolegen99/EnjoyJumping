package com.tolegensapps.enjoyjumping.presentation.profile;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.rt.data.EventHandler;
import com.tolegensapps.enjoyjumping.LoadingAlertDialog;

public class ProfilePresenter {

    void initProfileData(String userId, final LoadingAlertDialog loadingAlertDialog) {

//        Заполнение Профиля данными юзера

        Backendless.Data.of(BackendlessUser.class).findById(userId, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser user) {

                StringBuilder stringBuilder = new StringBuilder(String.valueOf(user.getProperty("ticketValidity")));
                StringBuilder str2 = stringBuilder.delete(10, 29)
                        .delete(0, 4);





                ProfileFragment.mFieldUserName.setText(String.valueOf(user.getProperty("userName")));
                ProfileFragment.mFieldUserEmail.setText(String.valueOf(user.getEmail()));
                ProfileFragment.mFieldNumberOfVisits.setText(String.valueOf(user.getProperty("ticketNumberOfVisits")));
                ProfileFragment.mFieldEveningSub.setText(checkEveningTrue((boolean) user.getProperty("ticketEveningSub")));
                ProfileFragment.mFieldValidity.setText(str2);
                loadingAlertDialog.dismissDialog();

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });


//        Слушатель для реал тайм базы данных в случае изминения информации

        final EventHandler<BackendlessUser> userTableRT = Backendless.Data.of(BackendlessUser.class).rt();
        userTableRT.addUpdateListener(new AsyncCallback<BackendlessUser>() {

            @Override
            public void handleResponse(BackendlessUser user) {

                StringBuilder stringBuilder = new StringBuilder(String.valueOf(user.getProperty("ticketValidity")));
                StringBuilder str2 = stringBuilder.delete(10, 29)
                        .delete(0, 4);



                ProfileFragment.mFieldUserName.setText(String.valueOf(user.getProperty("userName")));
                ProfileFragment.mFieldUserEmail.setText(String.valueOf(user.getEmail()));
                ProfileFragment.mFieldNumberOfVisits.setText(String.valueOf(user.getProperty("ticketNumberOfVisits")));
                ProfileFragment.mFieldEveningSub.setText(checkEveningTrue((boolean) user.getProperty("ticketEveningSub")));
                ProfileFragment.mFieldValidity.setText(str2);
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }

        });
    }

    public static String checkEveningTrue(Boolean eveningTrue) {

        String txtEveningSub;

        if (eveningTrue)
            txtEveningSub = "Вечерний";
        else
            txtEveningSub = "Дневной";

        return txtEveningSub;
    }

    private int transferToProgressValue(int numberOfVisits) {
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
}
