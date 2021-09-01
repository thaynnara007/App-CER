package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.adapter.OptionAdapter;
import com.cuidar.app_cer.model.Option;
import com.cuidar.app_cer.user_preferences.ActivityData;

import java.util.ArrayList;
import java.util.List;


public class MenuActivity extends AppCompatActivity {

    private Button exitButton;
    private Typeface quicksand;
    private RecyclerView recyclerViewOptions;
    private List<Option> options = new ArrayList<>();
    private ActivityData dataFile;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        context = getApplicationContext();

        dataFile = new ActivityData(context);

        exitButton = findViewById(R.id.exitButton);
        recyclerViewOptions = findViewById(R.id.recyclerViewOptions);

        quicksand = ResourcesCompat.getFont(context, R.font.quicksand_medium);

        exitButton.setTypeface(quicksand);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataFile.deleteToken();
                dataFile.deleteUserName();

                Intent backToBeginActivity = new Intent(context, BeginActivity.class);
                backToBeginActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(backToBeginActivity);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewOptions.setLayoutManager(layoutManager);

        this.generateOptions();

        OptionAdapter adapter = new OptionAdapter(options);
        recyclerViewOptions.setAdapter(adapter);

        Boolean firstLogin = getIntent().getBooleanExtra("firstLogin", false);
        if (firstLogin)
            openDialog();
    }

    private void openDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Atualizar senha");
        alert.setMessage("Sua senha ainda é o seu CPF, mas agora que você tem nosso aplicativo você pode mudar sua senha para uma mais segura que só você saiba!");

        alert.setPositiveButton("Mudar senha agora", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent goToProfileActivity = new Intent(context, EditRegisterActivity.class);

                startActivity(goToProfileActivity);
            }
        });
        alert.setNegativeButton("Mudar depois", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.create();
        alert.show();

    }

    private void generateOptions(){
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
/*
        Option optProfile = new Option(
                "Perfil",
                "Verifique todas as informações pessoais de sua conta!",
                R.drawable.blue_profile_vector,
                onClickListenerProfile); */

        this.options.add(optActivity);
        this.options.add(optControl);
    //    this.options.add(optProfile);
    }
}
