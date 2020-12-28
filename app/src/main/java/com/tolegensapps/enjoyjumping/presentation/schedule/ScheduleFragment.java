package com.tolegensapps.enjoyjumping.presentation.schedule;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.tolegensapps.enjoyjumping.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Timetable> mTimetables = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflatedView = inflater.inflate(R.layout.fragment_schedule, container, false);

        mRecyclerView = inflatedView.findViewById(R.id.recyclerViewShedules);

//        mTimetables.add(new Timetable("13.00", "Толеген",  2));
//        mTimetables.add(new Timetable("14.00", "Зангар",  3));
//        mTimetables.add(new Timetable("15.00", "Наташа", 3));
//        mTimetables.add(new Timetable("15.00", "Наташа", 3));
//        mTimetables.add(new Timetable("15.00", "Наташа", 3));
//        mTimetables.add(new Timetable("15.00", "Наташа", 3));
//        mTimetables.add(new Timetable("15.00", "Наташа", 3));

        Backendless.Data.of(Timetable.class).find(new AsyncCallback<List<Timetable>>() {
            @Override
            public void handleResponse(List<Timetable> response) {
                mTimetables = response;
                TimetableAdapter adapter = new TimetableAdapter((ArrayList<Timetable>) mTimetables);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });





        return inflatedView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Занятия");
    }
}