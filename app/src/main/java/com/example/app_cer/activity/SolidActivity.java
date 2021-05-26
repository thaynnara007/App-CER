package com.example.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app_cer.R;
import com.example.app_cer.model.Step;

import java.util.ArrayList;
import java.util.List;

public class SolidActivity extends AppCompatActivity {

    private Button backButton, letsGoButton;
    private Typeface quicksand;
    private ArrayList<Step> steps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid);

        backButton = findViewById(R.id.solidBackButton);
        letsGoButton = findViewById(R.id.solidStartButton);
        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);


        letsGoButton.setTypeface(quicksand, Typeface.BOLD);

        this.genereteSteps();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        letsGoButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToStepActivity = new Intent(getApplicationContext(), StepActivity.class);

                goToStepActivity.putExtra("steps", steps);
                goToStepActivity.putExtra("backgroundColor", R.color.colorPrimaryYellow);
                goToStepActivity.putExtra("icon", R.drawable.banana);

                startActivity(goToStepActivity);
            }
        }));
    }

    private void genereteSteps(){
        Step step1 = new Step(
                "Etapa 1",
                "Fale o nome da fruta para a criança!",
                R.drawable.solid_step1
        );

        Step step2 = new Step(
                "Etapa 2",
                "Coloque a criança para sentir o cheiro da fruta!"
        );

        Step step3 = new Step(
                "Etapa 3",
                "Coloque a criança para pegar a fruta!"
        );

        Step step4 = new Step(
                "Etapa 4",
                "Coloque a criança para comer a fruta!"
        );

        this.steps.add(step1);
        this.steps.add(step2);
        this.steps.add(step3);
        this.steps.add(step4);
    }
}