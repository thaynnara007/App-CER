package com.cuidar.app_cer.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.model.Entry;
import com.cuidar.app_cer.model.StatusCard;

import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyViewHolder> {

    private List<StatusCard> status;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();


    public StatusAdapter(List<StatusCard> status) {
        this.status = status;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView statusIcon;
        private TextView name;
        private RecyclerView entriesRecyclerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.statusIcon = itemView.findViewById(R.id.statusImage);
            this.name = itemView.findViewById(R.id.statusTitle);
            this.entriesRecyclerView = itemView.findViewById(R.id.activitiesControlRecyclerView);
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
        Context imageContext = holder.statusIcon.getContext();

        int fadeColor = ContextCompat.getColor(imageContext, R.color.ColorFade);
        Drawable icon = AppCompatResources.getDrawable(imageContext, card.getImage());
        holder.statusIcon.setBackground(icon);
        DrawableCompat.setTint(holder.statusIcon.getBackground(), fadeColor);

        holder.name.setText(card.getName());

        GridLayoutManager layoutManager = new GridLayoutManager(
                holder.entriesRecyclerView.getContext(),
                2
        );

        List<Entry> entries = card.getEntries();

        layoutManager.setInitialPrefetchItemCount(entries.size());
        EntryAdapter adapter = new EntryAdapter(entries);

        holder.entriesRecyclerView.setLayoutManager(layoutManager);
        holder.entriesRecyclerView.setAdapter(adapter);
        holder.entriesRecyclerView.setRecycledViewPool(viewPool);

    }

    @Override
    public int getItemCount() {
        return this.status.size();
    }
}
