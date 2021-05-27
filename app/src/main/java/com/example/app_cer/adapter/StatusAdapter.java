package com.example.app_cer.adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_cer.R;
import com.example.app_cer.model.Status;

import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyViewHolder> {

    private List<Status> status;


    public StatusAdapter(List<Status> status) {
        this.status = status;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView statusIcon;
        private TextView name, status1, status2, status3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.statusIcon = itemView.findViewById(R.id.statusImage);
            this.name= itemView.findViewById(R.id.statusTitle);
            this.status1= itemView.findViewById(R.id.status1);
            this.status2 = itemView.findViewById(R.id.status2);
            this.status3 = itemView.findViewById(R.id.status3);
        }
    }

    @NonNull
    @Override
    public StatusAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View optionCard = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.status, parent, false);


        return new StatusAdapter.MyViewHolder(optionCard);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusAdapter.MyViewHolder holder, int position) {
        Status status = this.status.get(position);

        holder.statusIcon.setImageResource(status.getImage());
        holder.name.setText(status.getName());
        holder.status1.setText(status.getStatus1());
        if (status.getName().equals("Refeição"))
            holder.status2.setText(status.getStatus2());
        holder.status3.setText(status.getStatus3());

    }

    @Override
    public int getItemCount() {
        return this.status.size();
    }
}
