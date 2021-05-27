package com.example.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app_cer.R;
import com.example.app_cer.adapter.OptionAdapter;
import com.example.app_cer.adapter.StatusAdapter;
import com.example.app_cer.model.Status;

import java.util.ArrayList;
import java.util.List;

public class ControlActivity extends AppCompatActivity {

    private Button backButton;
    private RecyclerView recyclerViewOptions;
    private List<Status> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        backButton = findViewById(R.id.controlBackButton);
        recyclerViewOptions = findViewById(R.id.recyclerViewControl);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewOptions.setLayoutManager(layoutManager);

        this.generateOptions();

        StatusAdapter adapter = new StatusAdapter(options);
        recyclerViewOptions.setAdapter(adapter);
    }

    private void generateOptions(){
        Status status1 = new Status(
                "Refeição",
                "Sólidos: 12",
                "Pastosos: 15",
                "Líquidos: 9",
                R.drawable.plate_fade
        );

        Status status2 = new Status(
                "Higiene",
                "Corpo: 11",
                "Dentes: 6",
                R.drawable.hygine_fade
        );

        Status status3 = new Status(
                "Roupas",
                "Blusa: 11",
                "Calça: 6",
                R.drawable.hanger_fade
        );

        this.options.add(status1);
        this.options.add(status2);
        this.options.add(status3);
    }
}
