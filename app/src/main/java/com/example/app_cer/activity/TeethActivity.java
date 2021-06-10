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

public class TeethActivity extends AppCompatActivity {

    private Button backButton, letsGoButton;
    private Typeface quicksand;
    private ArrayList<Step> steps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teeth);

        backButton = findViewById(R.id.teethBackButton);
        letsGoButton = findViewById(R.id.teethStartButton);
        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);

        letsGoButton.setTypeface(quicksand, Typeface.BOLD);

        this.generateSteps();

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
                goToStepActivity.putExtra("backgroundColor", R.color.colorPrimaryPink);
                goToStepActivity.putExtra("textColor", R.color.white);
                goToStepActivity.putExtra("icon", R.drawable.tooth_vector);

                startActivity(goToStepActivity);
            }
        }));
    }

    private void generateSteps() {

        Step step1 = new Step(
                "Etapa 1",
                "Mostre a escova para a criança!"
        );

        Step step2 = new Step(
                "Etapa 2",
                "Passe a escova na mão da criança!"
        );

        Step step3 = new Step(
                "Etapa 3",
                "Deixa-a brincar, pegar, morder a escova!"
        );

        Step step4 = new Step(
                "Etapa 4",
                "Fale o nome do objeto para a criança!"
        );

        Step step5 = new Step(
                "Etapa 5",
                "Estimule a criança a abrir a boca conversando com ela!"
        );

        Step step6 = new Step(
                "Etapa 6",
                "Realize a escovação e higienização!"
        );


        Step stepFinal = new Step(
                "PARABÉNS",
                "Você está indo muito bem!",
                "Você concluiu essa atividade!",
                R.drawable.body_end
        );

        this.steps.add(step1);
        this.steps.add(step2);
        this.steps.add(step3);
        this.steps.add(step4);
        this.steps.add(step5);
        this.steps.add(step6);
        this.steps.add(stepFinal);

    }
}
