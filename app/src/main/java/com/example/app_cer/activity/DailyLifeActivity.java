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

public class DailyLifeActivity extends AppCompatActivity {

    private Button backButton;
    private RecyclerView recyclerViewOptions;
    private List<Option> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_life);

        backButton = findViewById(R.id.dailyLifeBackButton);
        recyclerViewOptions = findViewById(R.id.recyclerViewDailyOptions);

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

        View.OnClickListener onClickListenerMeal = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMealsActivity = new Intent(context, MealsActivity.class);

                startActivity(goToMealsActivity);
            }
        };

        View.OnClickListener onClickListenerHygine = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "CONTROLE", Toast.LENGTH_SHORT).show();
            }
        };

        View.OnClickListener onClickListenerClothes = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "CONTROLE", Toast.LENGTH_SHORT).show();
            }
        };

        Option optMeal = new Option(
                "Refeição",
                "Aqui você encontra alimentos sólidos, líquidos e pastosos.",
                R.drawable.meal,
                onClickListenerMeal);

        Option optHygine = new Option(
                "Higiene",
                "Você pode escolher entre a higiene do corpo ou dos dentes.",
                R.drawable.hygine,
                onClickListenerHygine);

        Option optClothes = new Option(
                "Roupas",
                "Escolha qual das peças de roupa vamos praticar hoje!",
                R.drawable.hanger,
                onClickListenerClothes);

        this.options.add(optMeal);
        this.options.add(optHygine);
        this.options.add(optClothes);
    }
}
