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

public class LiquidActivity extends AppCompatActivity {

    private Button backButton, letsGoButton;
    private Typeface quicksand;
    private ArrayList<Step> steps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquid);

        backButton = findViewById(R.id.liquidBackButton);
        letsGoButton = findViewById(R.id.liquidStartButton);
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

                goToStepActivity.putExtra("activityName", Constants.LIQUID_NAME);
                goToStepActivity.putExtra("steps", steps);
                goToStepActivity.putExtra("backgroundColor", R.color.colorPrimaryYellow);
                goToStepActivity.putExtra("textColor", R.color.colorAccent);
                goToStepActivity.putExtra("icon", R.drawable.liquid_vector);

                startActivity(goToStepActivity);
            }
        }));
    }

    private void genereteSteps(){
        Step step1 = new Step(
                "Etapa 1",
                "Encha o copo de bico com o líquido, pode ser água ou suco!",
                R.drawable.liquid_step1
        );

        Step step2 = new Step(
                "Etapa 2",
                "Coloque a criança para pegar no copo!",
                R.drawable.liquid_step2
        );

        Step step3 = new Step(
                "Etapa 3",
                "Coloque-a para beber o líquido com calma!",
                R.drawable.liquid_step3
        );

        Step step4 = new Step(
                "Etapa 4",
                "Agora pode deixar ela brincar com o copo vazio!",
                R.drawable.liquid_step4
        );

        Step stepFinal = new Step(
                "PARABÉNS",
                "O desenvolvimento do seu filho agradece!",
                "Você concluiu essa atividade!",
                R.drawable.meal_end
        );

        this.steps.add(step1);
        this.steps.add(step2);
        this.steps.add(step3);
        this.steps.add(step4);
        this.steps.add(stepFinal);
    }
}