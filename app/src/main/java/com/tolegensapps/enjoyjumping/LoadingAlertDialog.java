package com.tolegensapps.enjoyjumping;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

public class LoadingAlertDialog {


    Activity mActivity;
    AlertDialog mDialog;

    public LoadingAlertDialog(Activity myActivity) {
        mActivity = myActivity;
    }


    public void startLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        LayoutInflater inflater = mActivity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.alert_dialog_loading, null));
        builder.setCancelable(false);

        mDialog = builder.create();
        mDialog.show();
    }

    public void dismissDialog() {
        mDialog.dismiss();
    }
}
