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

public class ClothesActivity extends AppCompatActivity {

    private Button backButton;
    private RecyclerView recyclerViewOptions;
    private List<Option> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);

        backButton = findViewById(R.id.clothesBackButton);
        recyclerViewOptions = findViewById(R.id.recyclerViewClothesOptions);

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

        View.OnClickListener onClickListenerShirt = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "CONTROLE", Toast.LENGTH_SHORT).show();
            }
        };

        View.OnClickListener onClickListenerPants = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "CONTROLE", Toast.LENGTH_SHORT).show();
            }
        };


        Option optShirt = new Option(
                "Blusa",
                "Atividade responsável por estímulos motores, sensoriais e corporais.",
                R.drawable.shiert,
                onClickListenerShirt);

        Option optPants = new Option(
                "Calça",
                "Atividade responsável por estímulos motores, sensoriais e corporais.",
                R.drawable.pants,
                onClickListenerPants);


        this.options.add(optShirt);
        this.options.add(optPants);
    }

}

