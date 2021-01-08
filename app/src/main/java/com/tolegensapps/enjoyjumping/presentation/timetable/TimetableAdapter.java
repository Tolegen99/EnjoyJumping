package com.tolegensapps.enjoyjumping.presentation.timetable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tolegensapps.enjoyjumping.R;

import java.util.ArrayList;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder> {

    private ArrayList<Timetable> mTimetables;

    public TimetableAdapter(ArrayList<Timetable> timetables) {
        mTimetables = timetables;
    }

    @NonNull
    @Override
    public TimetableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.timetable_item, viewGroup, false);
        return new TimetableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder timetableViewHolder, int i) {

        Timetable timetable = mTimetables.get(i);

        StringBuilder stringBuilder = new StringBuilder(String.valueOf(timetable.getTime()));
        String str2 = stringBuilder.substring(11, 16);

        timetableViewHolder.fieldTime.setText(str2);
        timetableViewHolder.fieldTrainerName.setText(timetable.getTrainerName());
        timetableViewHolder.fieldVacantPosition.setText(String.valueOf(timetable.getCountOfClients()));
    }

    @Override
    public int getItemCount() {
        return mTimetables.size();
    }

    class TimetableViewHolder extends RecyclerView.ViewHolder {

        private TextView fieldTime;
        private TextView fieldTrainerName;
        private TextView fieldVacantPosition;

        public TimetableViewHolder(@NonNull View itemView) {
            super(itemView);

            fieldTime = itemView.findViewById(R.id.field_time);
            fieldTrainerName = itemView.findViewById(R.id.field_trainer_name);
            fieldVacantPosition = itemView.findViewById(R.id.field_vacant_position);
        }
    }
}
