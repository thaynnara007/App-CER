package com.example.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
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
    TextView stepName, stepDescription;
    int currentStep, lastStep;
    ArrayList<Step> steps = new ArrayList<>();

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

        steps = (ArrayList<Step>) getIntent().getSerializableExtra("steps");
        lastStep = steps.size() - 1;
        currentStep = 0;

        Bundle data = getIntent().getExtras();
        int color = ContextCompat.getColor(getApplicationContext(), data.getInt("backgroundColor"));
        int icon = data.getInt("icon");


        currentLayout.setBackgroundColor(color);
        stepIcon.setImageResource(icon);

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
                }
                else {
                    finish();
                }
            }
        });
    }
}