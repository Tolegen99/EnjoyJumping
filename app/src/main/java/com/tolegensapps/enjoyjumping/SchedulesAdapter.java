package com.tolegensapps.enjoyjumping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SchedulesAdapter extends RecyclerView.Adapter<SchedulesAdapter.SchedulesViewHolder> {

    private ArrayList<ScheduleModel> mSchedules;

    public SchedulesAdapter(ArrayList<ScheduleModel> schedules) {
        mSchedules = schedules;
    }

    @NonNull
    @Override
    public SchedulesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.schedule_item, viewGroup, false);
        return new SchedulesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchedulesViewHolder schedulesViewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SchedulesViewHolder extends RecyclerView.ViewHolder {

        private TextView fieldTimeStart;
        private TextView fieldTimeEnd;
        private TextView fieldTrainerName;
        private TextView fieldVacantPlace;

        public SchedulesViewHolder(@NonNull View itemView) {
            super(itemView);

            fieldTimeStart = itemView.findViewById(R.id.field_time_start);
            fieldTimeEnd = itemView.findViewById(R.id.field_time_end);
            fieldTrainerName = itemView.findViewById(R.id.field_trainer_name);
            fieldVacantPlace = itemView.findViewById(R.id.field_vacant_place);

        }
    }
}
