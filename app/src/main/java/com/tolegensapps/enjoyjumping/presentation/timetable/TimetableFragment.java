package com.tolegensapps.enjoyjumping.presentation.timetable;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.tolegensapps.enjoyjumping.ChooseTodayAlertDialog;
import com.tolegensapps.enjoyjumping.MainActivity;
import com.tolegensapps.enjoyjumping.R;

import java.util.ArrayList;
import java.util.List;

public class  TimetableFragment extends Fragment {

    TimetablePresenter mTimetablePresenter;

    public RecyclerView mRecyclerView;
    private TextView mFieldTodayOrTomorrow;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflatedView = inflater.inflate(R.layout.fragment_timetable, container, false);

        mRecyclerView = inflatedView.findViewById(R.id.recyclerViewShedules);
        mFieldTodayOrTomorrow = inflatedView.findViewById(R.id.field_today_or_tomorrow);
        mTimetablePresenter = new TimetablePresenter(mRecyclerView);

        mFieldTodayOrTomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        mTimetablePresenter.initTimetablesFromBase(getActivity());
        mTimetablePresenter.hideFloatingButtonOnScroll();

        return inflatedView;
    }

    public void openDialog() {
        ChooseTodayAlertDialog chooseTodayAlertDialog = new ChooseTodayAlertDialog();
        chooseTodayAlertDialog.show(getFragmentManager(), "example dialog");
    }

}