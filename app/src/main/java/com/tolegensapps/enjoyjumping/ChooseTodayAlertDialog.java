package com.tolegensapps.enjoyjumping;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ChooseTodayAlertDialog extends AppCompatDialogFragment {

    private TextView mFieldChooseToday;
    private TextView mFieldChooseTomorrow;

    private View mViewTickToday;
    private View mViewTickTomorrow;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog_choose_today, null);

        builder.setView(view);
        builder.setCancelable(true);

        mFieldChooseToday = view.findViewById(R.id.field_choose_today);
        mFieldChooseTomorrow = view.findViewById(R.id.field_choose_tomorrow);

        mViewTickToday = view.findViewById(R.id.view_tick_today);
        mViewTickTomorrow = view.findViewById(R.id.view_tick_tomorrow);

        mFieldChooseToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewTickToday.setVisibility(view.VISIBLE);
                mViewTickTomorrow.setVisibility(view.INVISIBLE);
            }
        });

        mFieldChooseTomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewTickTomorrow.setVisibility(view.VISIBLE);
                mViewTickToday.setVisibility(view.INVISIBLE);

            }
        });




        return builder.create();
    }

//    Activity mActivity;
//    AlertDialog mDialog;

//    public ChooseTodayAlertDialog(Activity myActivity) {
//        mActivity = myActivity;
//    }


//    public void startLoadingDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
//
//        LayoutInflater inflater = mActivity.getLayoutInflater();
//
//
//        builder.setView(inflater.inflate(R.layout.alert_dialog_choose_today, null));
//        builder.setCancelable(true);
//
//        mDialog = builder.create();
//        mDialog.show();
//    }

//    public void dismissDialog() {
//        mDialog.dismiss();
//    }

}
