package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.model.Step.Step;
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

    }
}
