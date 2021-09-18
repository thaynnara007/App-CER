package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.model.Step.Step;
import com.cuidar.app_cer.user_preferences.ActivityData;

import java.util.ArrayList;

public class StepActivity extends AppCompatActivity {

    private int currentStep, lastStep;
    private String activityName;

    private ConstraintLayout currentLayout;
    private Button nextStepButton, beforeButton;
    private ImageView stepImage, stepIcon;
    private TextView stepName, stepDescription, congrats;
    private ArrayList<Step> steps = new ArrayList<>();
    private Typeface quicksand;
    private ActivityData dataFile;
    private Context context;

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
        context = getApplicationContext();
        dataFile = new ActivityData(context);

        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);
        nextStepButton.setTypeface(quicksand, Typeface.BOLD);
        beforeButton.setTypeface(quicksand, Typeface.BOLD);

        steps = (ArrayList<Step>) getIntent().getSerializableExtra("steps");
        lastStep = steps.size() - 1;
        currentStep = 0;

        final Bundle data = getIntent().getExtras();
        final int color = ContextCompat.getColor(context, data.getInt("backgroundColor"));
        int textColor = ContextCompat.getColor(context, data.getInt("textColor"));
        int icon = data.getInt("icon");
        activityName = data.getString("activityName");


        currentLayout.setBackgroundColor(color);
        stepIcon.setImageResource(icon);
        stepName.setTextColor(textColor);
        stepDescription.setTextColor(textColor);
        congrats.setTextColor(textColor);

        if (lastStep >= 0) {
            Step step = steps.get(currentStep);

            stepName.setText(step.getName());
            stepDescription.setText(step.getDescription());
            stepImage.setImageResource(step.getImage().getId());
        }

        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentStep++;

                if (lastStep >= currentStep) {
                    Step step = steps.get(currentStep);

                    stepName.setText(step.getName());
                    stepDescription.setText(step.getDescription());
                    stepImage.setImageResource(step.getImage().getId());
                    congrats.setText(step.getCongrats());

                    if (lastStep == currentStep) {
                        stepName.setTypeface(quicksand, Typeface.BOLD);
                        stepName.setTextSize(35);
                        nextStepButton.setText("Concluir!");
                    }
                }
                else {
                    if (activityName != null) {
                        dataFile.postStatus(activityName, dataFile.getStatus(activityName) + 1);
                    }

                    Intent backToDailyLifeActivity = new Intent(context, DailyLifeActivity.class);
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
                    stepImage.setImageResource(step.getImage().getId());
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
