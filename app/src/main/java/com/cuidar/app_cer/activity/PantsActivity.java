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

public class PantsActivity extends AppCompatActivity {

    private Button backButton, letsGoButton;
    private Typeface quicksand;
    private ArrayList<Step> steps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pants);

        backButton = findViewById(R.id.pantsBackButton);
        letsGoButton = findViewById(R.id.pantsStartButton);
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

                goToStepActivity.putExtra("activityName", Constants.PANTS_NAME);
                goToStepActivity.putExtra("steps", steps);
                goToStepActivity.putExtra("backgroundColor", R.color.colorPrimaryBlue);
                goToStepActivity.putExtra("textColor", R.color.white);
                goToStepActivity.putExtra("icon", R.drawable.pants_vector);

                startActivity(goToStepActivity);
            }
        }));
    }

    private void generateSteps() {

        Step step1 = new Step(
                "Etapa 1",
                "Escolha a calça ou o short que a criança irá vestir!",
                R.drawable.pants_step1
        );

        Step step2 = new Step(
                "Etapa 2",
                "Encoste a criança em uma superfície!",
                R.drawable.shirt_step2
        );

        Step step3 = new Step(
                "Etapa 3",
                "Coloque a perna direita na calça enquanto conversa com ela!",
                R.drawable.pants_step3
        );

        Step step4 = new Step(
                "Etapa 4",
                "Coloque a perna esquerda, enquanto conversa com ela!",
                R.drawable.pants_step4
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
        this.steps.add(stepFinal);
    }
}