package com.example.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_cer.R;
import com.example.app_cer.adapter.OptionAdapter;
import com.example.app_cer.model.Option;

import java.util.ArrayList;
import java.util.List;

public class HygineActivity extends AppCompatActivity {

    private Button backButton;
    private RecyclerView recyclerViewOptions;
    private List<Option> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hygine);

        backButton = findViewById(R.id.hygineBackButton);
        recyclerViewOptions = findViewById(R.id.recyclerViewHygineOptions);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewOptions.setLayoutManager(layoutManager);

        this.generateOptions();

        OptionAdapter adapter = new OptionAdapter(options);
        recyclerViewOptions.setAdapter(adapter);
    }

    private void generateOptions() {
        final Context context = getApplicationContext();

        View.OnClickListener onClickListenerBody = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToBodyActivity = new Intent(context, BodyActivity.class);

                startActivity(goToBodyActivity);
            }
        };

        View.OnClickListener onClickListenerTeeth = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTeethActivity = new Intent(context, TeethActivity.class);

                startActivity(goToTeethActivity);
            }
        };


        Option optBody = new Option(
                "Corpo",
                "Atividade responsável por estímulos motores, sensoriais e corporais.",
                R.drawable.body,
                onClickListenerBody);

        Option optTeeth = new Option(
                "Dentes",
                "Atividade responsável por estímulos motores, sensoriais e corporais.",
                R.drawable.tooth,
                onClickListenerTeeth);


        this.options.add(optBody);
        this.options.add(optTeeth);

    }
}
