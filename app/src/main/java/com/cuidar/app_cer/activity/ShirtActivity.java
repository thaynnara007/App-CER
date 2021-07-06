package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.model.Step;
import com.cuidar.app_cer.utils.Constants;

import java.util.ArrayList;

public class ShirtActivity extends AppCompatActivity {

    private Button backButton, letsGoButton;
    private Typeface quicksand;
    private ArrayList<Step> steps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shirt);

        backButton = findViewById(R.id.shirtBackButton);
        letsGoButton = findViewById(R.id.shirtStartButton);
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

                goToStepActivity.putExtra("activityName", Constants.SHIRT_NAME);
                goToStepActivity.putExtra("steps", steps);
                goToStepActivity.putExtra("backgroundColor", R.color.colorPrimaryBlue);
                goToStepActivity.putExtra("textColor", R.color.white);
                goToStepActivity.putExtra("icon", R.drawable.shiert_vector);

                startActivity(goToStepActivity);
            }
        }));
    }

    private void generateSteps() {

        Step step1 = new Step(
                "Etapa 1",
                "Escolha a blusinha que será vestida!",
                R.drawable.shirt_step1
        );

        Step step2 = new Step(
                "Etapa 2",
                "Encoste a criança em uma superfície.",
                R.drawable.shirt_step2
        );

        Step step3 = new Step(
                "Etapa 3",
                "Coloque o braço direito, na manga enquanto conversa com ela!",
                R.drawable.shirt_step3
        );

        Step step4 = new Step(
                "Etapa 4",
                "Agora o braço esquerdo, colocando a manga enquanto conversa com ela.",
                R.drawable.shirt_step4
        );

        Step step5 = new Step(
                "Etapa 5",
                "Mantendo a conversa, insira a cabecinha na gola da blusa.",
                R.drawable.shirt_step5
        );

        Step stepFinal = new Step(
                "PARABÉNS",
                "O seu progresso está melhorando!",
                "Você concluiu essa atividade!",
                R.drawable.cloth_end
        );

        this.steps.add(step1);
        this.steps.add(step2);
        this.steps.add(step3);
        this.steps.add(step4);
        this.steps.add(step5);
        this.steps.add(stepFinal);
    }
}
