package com.example.app_cer.adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_cer.R;
import com.example.app_cer.model.Option;

import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.MyViewHolder> {

    private List<Option> options;
    private Typeface quicksand;

    public OptionAdapter (List<Option> options){
        this.options = options;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView optionIcon;
        private TextView optionName;
        private TextView optionDescription;
        private Button startButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.optionIcon = itemView.findViewById(R.id.optionIcon);
            this.optionName = itemView.findViewById(R.id.optionName);
            this.optionDescription = itemView.findViewById(R.id.optionDescription);
            this.startButton = itemView.findViewById(R.id.optionStartButton);
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View optionCard = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.option, parent, false);

        this.quicksand = ResourcesCompat.getFont(parent.getContext(), R.font.quicksand_medium);

        return new MyViewHolder(optionCard);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Option option = this.options.get(position);

        holder.optionIcon.setImageResource(option.getIcon());
        holder.optionName.setText(option.getName());
        holder.optionDescription.setText(option.getDescription());
        holder.startButton.setOnClickListener(option.getOnClick());
        holder.startButton.setTypeface(this.quicksand);
    }

    @Override
    public int getItemCount() {
        return this.options.size();
    }
}
