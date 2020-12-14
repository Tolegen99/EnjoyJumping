package com.tolegensapps.enjoyjumping.presentation.profile;

import android.content.Intent;
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
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.tolegensapps.enjoyjumping.LoadingDialog;
import com.tolegensapps.enjoyjumping.R;
import com.tolegensapps.enjoyjumping.WelcomeActivity;

public class ProfileFragment extends Fragment {

    private static String mUserId;

    ProfilePresenter profilePresenter;

    protected ProgressBar progressBar;
    protected static TextView fieldNumberOfVisits;
    protected TextView fieldUserName;
    protected TextView fieldUserEmail;
    protected TextView fieldEveningSub;

    protected TextView fieldLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflatedView = inflater.inflate(R.layout.fragment_profile, container, false);

        mUserId = getArguments().getString("objectId");

        profilePresenter = new ProfilePresenter();

        initUI(inflatedView);
        initUIBehaviour();
        profilePresenter.initProfileData(this, mUserId);
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


}