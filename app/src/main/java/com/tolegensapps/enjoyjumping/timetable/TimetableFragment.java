package com.tolegensapps.enjoyjumping.timetable;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tolegensapps.enjoyjumping.ChooseTodayAlertDialog;
import com.tolegensapps.enjoyjumping.R;

public class  TimetableFragment extends Fragment {

    TimetableUtils mTimetableUtils;

    public RecyclerView mRecyclerView;
    private TextView mFieldTodayOrTomorrow;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflatedView = inflater.inflate(R.layout.fragment_timetable, container, false);

        mRecyclerView = inflatedView.findViewById(R.id.recyclerViewShedules);
        mFieldTodayOrTomorrow = inflatedView.findViewById(R.id.field_today_or_tomorrow);
        mTimetableUtils = new TimetableUtils(mRecyclerView);

        mFieldTodayOrTomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        mTimetableUtils.initTimetablesFromBase(getActivity());
        mTimetableUtils.hideFloatingButtonOnScroll();

        return inflatedView;
    }

    public void openDialog() {
        ChooseTodayAlertDialog chooseTodayAlertDialog = new ChooseTodayAlertDialog();
        chooseTodayAlertDialog.show(getFragmentManager(), "example dialog");
    }

}