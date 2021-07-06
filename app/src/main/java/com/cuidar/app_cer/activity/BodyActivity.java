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

public class BodyActivity extends AppCompatActivity {

    private Button backButton, letsGoButton;
    private Typeface quicksand;
    private ArrayList<Step> steps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        backButton = findViewById(R.id.bodyBackButton);
        letsGoButton = findViewById(R.id.bodyStartButton);
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

                goToStepActivity.putExtra("activityName", Constants.BODY_NAME);
                goToStepActivity.putExtra("steps", steps);
                goToStepActivity.putExtra("backgroundColor", R.color.colorPrimaryPink);
                goToStepActivity.putExtra("textColor", R.color.white);
                goToStepActivity.putExtra("icon", R.drawable.body_vector);

                startActivity(goToStepActivity);
            }
        }));
    }

    private void generateSteps() {

        Step step1 = new Step(
                "Etapa 1",
                "Tire a roupa movimentando os membros superiores e inferiores!",
                R.drawable.body_step1
        );

        Step step2 = new Step(
                "Etapa 2",
                "Coloque a criança sentada no local do banho!",
                R.drawable.body_step2
        );

        Step step3 = new Step(
                "Etapa 3",
                "Estimule-a para que ela vá se ensaboando!",
                R.drawable.body_step3
        );

        Step step4 = new Step(
                "Etapa 4",
                "Deixe ela ir retirando o shampoo de seu cabelo!",
                R.drawable.body_step4
        );

        Step step5 = new Step(
                "Etapa 5",
                "Deixe que ela possa brincar com o sabonete, o chuveirinho, etc.",
                R.drawable.body_step5
        );

        Step stepFinal = new Step(
                "PARABÉNS",
                "Você foi muito bem!",
                "Você concluiu essa atividade!",
                R.drawable.hygine_end
        );

        this.steps.add(step1);
        this.steps.add(step2);
        this.steps.add(step3);
        this.steps.add(step4);
        this.steps.add(step5);
        this.steps.add(stepFinal);

    }
}
