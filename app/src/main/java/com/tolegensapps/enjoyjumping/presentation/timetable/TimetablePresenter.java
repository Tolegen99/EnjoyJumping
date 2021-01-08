package com.tolegensapps.enjoyjumping.presentation.timetable;

import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.tolegensapps.enjoyjumping.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class TimetablePresenter {

    private RecyclerView mRecyclerView;
    private List<Timetable> mTimetables = new ArrayList<>();

    public TimetablePresenter(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    void initTimetablesFromBase(final Activity activity) {
        Backendless.Data.of(Timetable.class).find(new AsyncCallback<List<Timetable>>() {
            @Override
            public void handleResponse(List<Timetable> response) {
                mTimetables = response;
                TimetableAdapter adapter = new TimetableAdapter((ArrayList<Timetable>) mTimetables);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    public void hideFloatingButtonOnScroll() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    MainActivity.btnScanCode.hide();
                } else {
                    MainActivity.btnScanCode.show();
                }

                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
