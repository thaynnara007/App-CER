package com.cuidar.app_cer.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.model.Option;

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
        Context imageContext = holder.optionIcon.getContext();

        int color = ContextCompat.getColor(imageContext, R.color.colorAccent);
        Drawable icon = AppCompatResources.getDrawable(imageContext, option.getIcon());
        DrawableCompat.setTint(icon, color);
        holder.optionIcon.setImageResource(option.getIcon());

        holder.optionName.setText(option.getName());
        holder.optionDescription.setText(option.getDescription());
        holder.startButton.setOnClickListener(option.getOnClick());
        holder.startButton.setTypeface(this.quicksand, Typeface.BOLD);
    }

    @Override
    public int getItemCount() {
        return this.options.size();
    }

    public void add (Option newOption) {
        this.options.add(newOption);
        notifyItemInserted(this.options.size() - 1);
   }

    public void addAll(List<Option> newOptions) {
        for ( Option option: newOptions) {
            this.add(option);
        }
    }
}
