package com.cuidar.app_cer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.model.Entry;

import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.MyViewHolder> {

    private List<Entry> entries;

    public EntryAdapter(List<Entry> entries) {
        this.entries = entries;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name= itemView.findViewById(R.id.activityEntry);
        }
    }

    @NonNull
    @Override
    public EntryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View entry = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entry, parent, false);


        return new EntryAdapter.MyViewHolder(entry);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryAdapter.MyViewHolder holder, int position) {
        Entry entry = this.entries.get(position);

        holder.name.setText(entry.getName());

    }

    @Override
    public int getItemCount() {
        return this.entries.size();
    }
}
