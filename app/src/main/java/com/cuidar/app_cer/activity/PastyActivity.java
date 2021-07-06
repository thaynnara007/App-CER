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

public class PastyActivity extends AppCompatActivity {

    private Button backButton, letsGoButton;
    private Typeface quicksand;
    private ArrayList<Step> steps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasty);

        backButton = findViewById(R.id.pastyBackButton);
        letsGoButton = findViewById(R.id.pastyStartButton);
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

                goToStepActivity.putExtra("activityName", Constants.PASTY_NAME);
                goToStepActivity.putExtra("steps", steps);
                goToStepActivity.putExtra("backgroundColor", R.color.colorPrimaryYellow);
                goToStepActivity.putExtra("textColor", R.color.colorAccent);
                goToStepActivity.putExtra("icon", R.drawable.soup_vector);

                startActivity(goToStepActivity);
            }
        }));
    }

    private void genereteSteps(){
        Step step1 = new Step(
                "Etapa 1",
                "Escolha os ingredientes para fazer a sopinha!",
                R.drawable.pasty_step1
        );

        Step step2 = new Step(
                "Etapa 2",
                "Fale o nome dos ingredientes para a ela!",
                R.drawable.pasty_step2
        );

        Step step3 = new Step(
                "Etapa 3",
                "Coloque-a para sentir o cheiro dos ingredientes!",
                R.drawable.pasty_step3
        );

        Step step4 = new Step(
                "Etapa 4",
                "Coloque-a para pegar nos ingredientes!",
                R.drawable.pasty_step4
        );

        Step step5 = new Step(
                "Etapa 5",
                "Prepare a sopinha enquanto a criança a observa!",
                R.drawable.pasty_step5
        );

        Step step6 = new Step(
                "Etapa 6",
                "Agora é só comer a sopinha, seja com a mão ou com a colher!",
                R.drawable.pasty_step6
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
        this.steps.add(step5);
        this.steps.add(step6);
        this.steps.add(stepFinal);
    }
}
