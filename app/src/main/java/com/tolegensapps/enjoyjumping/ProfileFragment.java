package com.tolegensapps.enjoyjumping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.rt.data.EventHandler;

public class ProfileFragment extends Fragment {

    private static String userId;



    private ProgressBar progressBar;
    private static TextView fieldNumberOfVisits;
    private TextView fieldUserName;
    private TextView fieldUserEmail;
    private TextView fieldEveningSub;

    private TextView fieldLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflatedView = inflater.inflate(R.layout.fragment_profile, container, false);

        userId = getArguments().getString("objectId");

        initUI(inflatedView);
        initUIBehaviour();
        initProfileData();
        return inflatedView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");

    }


    private void initUI(View inflatedView) {
        fieldNumberOfVisits = inflatedView.findViewById(R.id.fieldNumberOfVisits);
        fieldEveningSub = inflatedView.findViewById(R.id.fieldEveningSub);
        progressBar = inflatedView.findViewById(R.id.progressBarNumbOfVisits);
        fieldLogout = inflatedView.findViewById(R.id.fieldLogout);

        fieldUserName = inflatedView.findViewById(R.id.fieldUserName);
        fieldUserEmail = inflatedView.findViewById(R.id.fieldUserEmail);
        fieldEveningSub = inflatedView.findViewById(R.id.fieldEveningSub);

    }

    private void initUIBehaviour() {
//        Выйти из аккаунта
        fieldLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog loadingDialog = new LoadingDialog(getActivity());
                loadingDialog.startLoadingDialog();
                Backendless.UserService.logout(new AsyncCallback<Void>() {

                    @Override
                    public void handleResponse(Void response) {
                        Intent intentLogout = new Intent(getActivity().getApplication(), WelcomeActivity.class);
                        startActivity(intentLogout);
                        Log.w("MYAPP", "Yes");
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.e("MYAPP", "server reported an error - " + fault.getMessage());
                    }
                });
            }
        });
    }

    private void initProfileData() {


        Backendless.Data.of(BackendlessUser.class).findById(userId, new AsyncCallback<BackendlessUser>() {
            String txtUserName;
            String txtUserEmail;
            int numberOfVisits;
            String txtEveningSub;
            int progressValue;

            @Override
            public void handleResponse(BackendlessUser user) {
                txtUserName = String.valueOf(user.getProperty("userName"));
                txtUserEmail = String.valueOf(user.getEmail());
                numberOfVisits = (int) user.getProperty("numberOfVisits");

                if ((boolean) user.getProperty("eveningSub"))
                    txtEveningSub = "Вечерний";
                else
                    txtEveningSub = "Дневной";

                progressValue = initProgressValue(numberOfVisits);

                fieldUserName.setText(txtUserName);
                fieldUserEmail.setText(txtUserEmail);
                fieldEveningSub.setText(txtEveningSub);
                progressBar.setProgress(progressValue);
                fieldNumberOfVisits.setText(String.valueOf(numberOfVisits));
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });


        final EventHandler<BackendlessUser> userTableRT = Backendless.Data.of(BackendlessUser.class).rt();

        userTableRT.addUpdateListener("objectId = '" + userId + "'", new AsyncCallback<BackendlessUser>() {
            String txtUserName;
            String txtUserEmail;
            int numberOfVisits;
            String txtEveningSub;
            int progressValue;

            @Override
            public void handleResponse(final BackendlessUser user) {
                txtUserName = String.valueOf(user.getProperty("userName"));
                txtUserEmail = String.valueOf(user.getEmail());
                numberOfVisits = (int) user.getProperty("numberOfVisits");

                if ((boolean) user.getProperty("eveningSub"))
                    txtEveningSub = "Вечерний";
                else
                    txtEveningSub = "Дневной";

                progressValue = initProgressValue(numberOfVisits);

                fieldUserName.setText(txtUserName);
                fieldUserEmail.setText(txtUserEmail);
                fieldEveningSub.setText(txtEveningSub);
                progressBar.setProgress(progressValue);
                fieldNumberOfVisits.setText(String.valueOf(numberOfVisits));

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("MYAPP", fault.getMessage());
            }

        });
    }

    private int initProgressValue(int numberOfVisits) {
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