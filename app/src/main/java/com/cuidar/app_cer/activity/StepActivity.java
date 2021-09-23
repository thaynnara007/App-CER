package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.api.HistoryService;
import com.cuidar.app_cer.helper.RetrofitConfig;
import com.cuidar.app_cer.model.Step.Step;
import com.cuidar.app_cer.model.history.Entry;
import com.cuidar.app_cer.model.history.EntryBody;
import com.cuidar.app_cer.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StepActivity extends AppCompatActivity {

    private int currentStep, lastStep;
    private int activityId;

    private ConstraintLayout currentLayout;
    private Button nextStepButton, beforeButton;
    private ImageView stepImage, stepIcon;
    private TextView stepName, stepDescription, congrats;
    private ArrayList<Step> steps = new ArrayList<>();
    private Typeface quicksand;

    private Retrofit retrofit;
    private HistoryService service;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        context = getApplicationContext();
        retrofit = RetrofitConfig.getRetrofit();
        service = retrofit.create(HistoryService.class);

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
        final int color = data.getInt("backgroundColor");
        int textColor = data.getInt("textColor");
        int icon = data.getInt("icon");
        activityId = data.getInt("activityId");

        currentLayout.setBackgroundColor(color);
        stepIcon.setImageResource(icon);
        stepName.setTextColor(textColor);
        stepDescription.setTextColor(textColor);
        congrats.setTextColor(textColor);

        if (lastStep >= 0) {
            Step step = steps.get(currentStep);

            stepName.setText(step.getName());
            stepDescription.setText(step.getDescription());
            getImageFromUrl(stepImage, step.getImage().getPictureUrl());
        }

        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentStep++;

                if (lastStep >= currentStep) {
                    Step step = steps.get(currentStep);

                    stepName.setText(step.getName());
                    stepDescription.setText(step.getDescription());
                    getImageFromUrl(stepImage, step.getImage().getPictureUrl());

                    if (lastStep == currentStep) {
                        stepName.setTypeface(quicksand, Typeface.BOLD);
                        stepName.setTextSize(35);
                        nextStepButton.setText("Concluir!");
                        congrats.setText("Você concluiu essa atividade!");
                    }
                }
                else {
                    if (activityId > 0) {
                        String token = Util.getAccessToken(context);
                        EntryBody body = new EntryBody(activityId);

                        Call<Entry> createEntryCall = service.createEntry(body, token);
                        createEntryCall.enqueue(new Callback<Entry>() {
                            @Override
                            public void onResponse(Call<Entry> call, Response<Entry> response) {
                                if(!response.isSuccessful()) {
                                    Intent intent = Util.whenNotSuccessful(response, context, "CREATE ENTRY:");

                                    if(intent != null)
                                        startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<Entry> call, Throwable t) {
                                Log.d("ERROR", "ERROR-CREATE-ENTRY: " + t.getMessage());
                            }
                        });
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
                    getImageFromUrl(stepImage, step.getImage().getPictureUrl());
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

    private void getImageFromUrl(ImageView view, String url){
        if (view != null && url != null && !url.isEmpty()){
            Picasso.get().load(url).into(view);
        }
    }
}






