package com.tolegensapps.enjoyjumping.presentation.profile;

import android.util.Log;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.backendless.rt.data.EventHandler;

import java.util.List;

public class ProfilePresenter {




    void initProfileData(final ProfileFragment profileFragment, String userId) {

//        Заполнение Профиля данными юзера

        Backendless.Data.of(BackendlessUser.class).findById(userId, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser user) {
                profileFragment.fieldUserName.setText(String.valueOf(user.getProperty("userName")));
                profileFragment.fieldUserEmail.setText(String.valueOf(user.getEmail()));
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });

//          Создание запроса для нахождения абонемента по юзерИД

        String whereClause = "userId = '" + userId + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

//                Заполнение Профиля данными абонемента

        Backendless.Data.of(Tickets.class).find(queryBuilder, new AsyncCallback<List<Tickets>>() {

            @Override
            public void handleResponse(List<Tickets> response) {
                for (Tickets ticket: response) {
                    ProfileFragment.fieldNumberOfVisits.setText(String.valueOf(ticket.getNumberOfVisits()));
                    profileFragment.fieldEveningSub.setText(Tickets.checkEveningTrue(ticket.getEveningSub()));
                    profileFragment.progressBar.setProgress(Tickets.transferToProgressValue(ticket.getNumberOfVisits()));

                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d("ProfileFragment","fail");
            }
        });

//        Слушатель для реал тайм базы данных в случае изминения информации об абонементе

        final EventHandler<Tickets> ticketsTableRT = Backendless.Data.of(Tickets.class).rt();
        ticketsTableRT.addUpdateListener(whereClause, new AsyncCallback<Tickets>() {

            @Override
            public void handleResponse(Tickets ticket) {
                ProfileFragment.fieldNumberOfVisits.setText(String.valueOf(ticket.getNumberOfVisits()));
                profileFragment.fieldEveningSub.setText(Tickets.checkEveningTrue(ticket.getEveningSub()));
                profileFragment.progressBar.setProgress(Tickets.transferToProgressValue(ticket.getNumberOfVisits()));
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }

        });
    }
}
