package com.example.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_cer.R;
import com.example.app_cer.model.Step;

import java.util.ArrayList;

public class StepActivity extends AppCompatActivity {

    ConstraintLayout currentLayout;
    Button nextStepButton, beforeButton;
    ImageView stepImage, stepIcon;
    TextView stepName, stepDescription, congrats;
    int currentStep, lastStep;
    ArrayList<Step> steps = new ArrayList<>();
    Typeface quicksand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        currentLayout = findViewById(R.id.step_layout);
        nextStepButton = findViewById(R.id.nextStepButton);
        beforeButton = findViewById(R.id.stepBeforeButton);
        stepIcon = findViewById(R.id.stepIcon);
        stepImage = findViewById(R.id.stepImage);
        stepName = findViewById(R.id.stepName);
        stepDescription = findViewById(R.id.stepDescription);
        congrats = findViewById(R.id.textCongrats);

        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);
        nextStepButton.setTypeface(quicksand, Typeface.BOLD);
        beforeButton.setTypeface(quicksand, Typeface.BOLD);

        steps = (ArrayList<Step>) getIntent().getSerializableExtra("steps");
        lastStep = steps.size() - 1;
        currentStep = 0;

        final Bundle data = getIntent().getExtras();
        final int color = ContextCompat.getColor(getApplicationContext(), data.getInt("backgroundColor"));
        int textColor = ContextCompat.getColor(getApplicationContext(), data.getInt("textColor"));
        int icon = data.getInt("icon");


        currentLayout.setBackgroundColor(color);
        stepIcon.setImageResource(icon);
        stepName.setTextColor(textColor);
        stepDescription.setTextColor(textColor);

        if (lastStep >= 0) {
            Step step = steps.get(currentStep);

            stepName.setText(step.getName());
            stepDescription.setText(step.getDescription());
            stepImage.setImageResource(step.getImage());
        }

        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentStep++;

                if (lastStep >= currentStep) {
                    Step step = steps.get(currentStep);

                    stepName.setText(step.getName());
                    stepDescription.setText(step.getDescription());
                    stepImage.setImageResource(step.getImage());
                    congrats.setText(step.getCongrats());

                    if (lastStep == currentStep) {
                        stepName.setTypeface(quicksand, Typeface.BOLD);
                        stepName.setTextSize(35);
                        nextStepButton.setText("Concluir!");
                    }
                }
                else {
                    Intent backToDailyLifeActivity = new Intent(getApplicationContext(), DailyLifeActivity.class);
                    backToDailyLifeActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    startActivity(backToDailyLifeActivity);
                }
            }
        });

        beforeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentStep > lastStep)
                    currentStep = lastStep - 1;
                else
                    currentStep--;

                if (currentStep >= 0) {
                    Step step = steps.get(currentStep);

                    stepName.setText(step.getName());
                    stepDescription.setText(step.getDescription());
                    stepImage.setImageResource(step.getImage());
                    stepName.setTypeface(quicksand, Typeface.NORMAL);
                    stepName.setTextSize(30);
                    nextStepButton.setText("Próximo passo");
                }
                else {
                    finish();
                }
            }
        });
    }
}
