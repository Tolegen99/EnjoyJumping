package com.tolegensapps.enjoyjumping.presentation.schedule;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.tolegensapps.enjoyjumping.MainActivity;
import com.tolegensapps.enjoyjumping.R;

import java.util.ArrayList;
import java.util.List;

public class  TimetableFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Timetable> mTimetables = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflatedView = inflater.inflate(R.layout.fragment_timetable, container, false);

        mRecyclerView = inflatedView.findViewById(R.id.recyclerViewShedules);



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

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    MainActivity.btnScanCode.hide();
                } else{
                    MainActivity.btnScanCode.show();
                }

                super.onScrolled(recyclerView, dx, dy);
            }
        });



        return inflatedView;
    }
}