package com.cuidar.app_cer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.model.Status;
import com.cuidar.app_cer.model.StatusCard;
import com.cuidar.app_cer.utils.Constants;

import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyViewHolder> {

    private List<StatusCard> status;


    public StatusAdapter(List<StatusCard> status) {
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
        StatusCard card = this.status.get(position);
        holder.statusIcon.setImageResource(card.getImage());
        holder.name.setText(card.getName());

        //Status statusObj1 = card.getStatus().get(0);
        holder.status1.setText("aaa");

        if (card.getName().equals(Constants.MEAL_NAME)) {
            //Status statusObj2 = card.getStatus().get(2);
            holder.status2.setText("bbbb");
        }

        //Status statusObj3 = card.getStatus().get(1);
        holder.status3.setText("ccc");

    }

    @Override
    public int getItemCount() {
        return this.status.size();
    }
}
