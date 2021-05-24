package com.example.app_cer.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;

        private TextView optionName;
        private TextView optionDescription;
        private Button startButton;
        public MyViewHolder(@NonNull View itemView, ImageView image, TextView optionName, TextView optionDescription, Button startButton) {
            super(itemView);

            this.image = image;
            this.optionName = optionName;
            this.optionDescription = optionDescription;
            this.startButton = startButton;
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
