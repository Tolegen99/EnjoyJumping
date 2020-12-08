package com.tolegensapps.enjoyjumping;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog {

    Activity mActivity;
    AlertDialog mDialog;

    LoadingDialog(Activity myActivity) {
        mActivity = myActivity;
    }

    void startLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        LayoutInflater inflater = mActivity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));
        builder.setCancelable(false);

        mDialog = builder.create();
        mDialog.show();
    }

    void dismissDialog() {
        mDialog.dismiss();
    }
}
