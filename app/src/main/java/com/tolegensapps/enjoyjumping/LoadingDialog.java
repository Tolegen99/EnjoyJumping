package com.tolegensapps.enjoyjumping;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

public class LoadingDialog {


    Activity mActivity;
    AlertDialog mDialog;
    View view;

    public LoadingDialog(Activity myActivity) {
        mActivity = myActivity;
    }


    public void startLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        LayoutInflater inflater = mActivity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));
        builder.setCancelable(false);

        mDialog = builder.create();
        mDialog.show();
    }

    public void dismissDialog() {
        mDialog.dismiss();
    }
}
