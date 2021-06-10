package com.example.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_cer.R;
import com.example.app_cer.adapter.OptionAdapter;
import com.example.app_cer.model.Option;

import java.util.ArrayList;
import java.util.List;


public class MenuActivity extends AppCompatActivity {

    private Button exitButton;
    private Typeface quicksand;
    private RecyclerView recyclerViewOptions;
    private List<Option> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        exitButton = findViewById(R.id.exitButton);
        recyclerViewOptions = findViewById(R.id.recyclerViewOptions);

        quicksand = ResourcesCompat.getFont(getApplicationContext(), R.font.quicksand_medium);

        exitButton.setTypeface(quicksand);
        exitButton.setOnClickListener(new View.OnClickListener() {
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

    private void generateOptions(){

        final Context context = getApplicationContext();

        View.OnClickListener onClickListenerActivity = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToDailyLifeActivity = new Intent(context, DailyLifeActivity.class);

                startActivity(goToDailyLifeActivity);
            }
        };

        View.OnClickListener onClickListenerControl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToControlActivity = new Intent(context, ControlActivity.class);

                startActivity(goToControlActivity);
            }
        };

        View.OnClickListener onClickListenerProfile = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToEditProfileActivity = new Intent(context, EditRegisterActivity.class);

                startActivity(goToEditProfileActivity);
            }
        };

        Option optActivity = new Option(
                "Atividade",
                "Aqui você pode escolher entre refeições, higiene e roupas.",
                R.drawable.calendar_vector,
                onClickListenerActivity);

        Option optControl = new Option(
                "Controle",
                "Verifique quantas vezes você realizou as atividades!",
                R.drawable.clipboard_vector,
                onClickListenerControl);

        Option optProfile = new Option(
                "Perfil",
                "Verifique todas as informações pessoais de sua conta!",
                R.drawable.blue_profile_vector,
                onClickListenerProfile);

        this.options.add(optActivity);
        this.options.add(optControl);
        this.options.add(optProfile);
    }
}
