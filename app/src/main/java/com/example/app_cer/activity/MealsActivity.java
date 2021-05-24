package com.example.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_cer.R;
import com.example.app_cer.adapter.OptionAdapter;
import com.example.app_cer.model.Option;

import java.util.ArrayList;
import java.util.List;

public class MealsActivity extends AppCompatActivity {

    private Button backButton;
    private RecyclerView recyclerViewOptions;
    private List<Option> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

        backButton = findViewById(R.id.mealsBackButton);
        recyclerViewOptions = findViewById(R.id.recyclerViewMealsOptions);

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

        View.OnClickListener onClickListenerSolids = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "CONTROLE", Toast.LENGTH_SHORT).show();
            }
        };

        View.OnClickListener onClickListenerPasty = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "CONTROLE", Toast.LENGTH_SHORT).show();
            }
        };

        View.OnClickListener onClickListenerLiquid = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "CONTROLE", Toast.LENGTH_SHORT).show();
            }
        };

        Option optSolids = new Option(
                "Sólidas",
                "Atividade responsável por estímulos motores e sensoriais.",
                R.drawable.banana,
                onClickListenerSolids);

        Option optPasty = new Option(
                "Pastosas",
                "Atividade responsável por estímulos motores e sensoriais.",
                R.drawable.soup,
                onClickListenerPasty);

        Option optLiquid= new Option(
                "Líquidas",
                "Atividade responsável por estímulos motores, sensoriais e corporais.",
                R.drawable.liquid,
                onClickListenerLiquid);

        this.options.add(optSolids);
        this.options.add(optPasty);
        this.options.add(optLiquid);
    }
}