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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.api.StepService;
import com.cuidar.app_cer.helper.RetrofitConfig;
import com.cuidar.app_cer.model.Step.Step;
import com.cuidar.app_cer.model.activity.Activity;
import com.cuidar.app_cer.utils.Util;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityDetailsActivity extends AppCompatActivity {

    private Button backButton, letsGoButton;
    private Typeface quicksand;
    private TextView title;
    private TextView subtitle;
    private TextView description;
    private ImageView iconImage;
    private ConstraintLayout layout;
    private ProgressBar loading;

    private Activity activity;
    private int backgroundColor;
    private int textColor;
    private int icon;

    private Retrofit retrofit;
    private StepService service;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid);

        context = getApplicationContext();
        retrofit = RetrofitConfig.getRetrofit();
        service = retrofit.create(StepService.class);

        title = findViewById(R.id.activityTitle);
        subtitle = findViewById(R.id.activitySubtitle);
        description = findViewById(R.id.activityDescription);
        iconImage = findViewById(R.id.activityIcon);
        layout = findViewById(R.id.activityLayout);
        loading = findViewById(R.id.loadingSteps);
        backButton = findViewById(R.id.solidBackButton);
        letsGoButton = findViewById(R.id.solidStartButton);
        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);

        Bundle data = getIntent().getExtras();
        activity = (Activity) data.getSerializable("activity");
        backgroundColor = data.getInt("backgroundColor");
        textColor = data.getInt("textColor");
        String categoryName = data.getString("categoryName");
        icon = Util.getIcon(activity.getIcon());

        layout.setBackgroundColor(backgroundColor);
        title.setText(activity.getName());
        title.setTextColor(textColor);
        subtitle.setText(categoryName);
        subtitle.setTextColor(textColor);
        description.setText(activity.getPageDescription());
        description.setTextColor(textColor);
        iconImage.setImageResource(icon);

        letsGoButton.setTypeface(quicksand, Typeface.BOLD);

        this.getSteps();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getSteps() {
        String token = Util.getAccessToken(context);
        Call<ArrayList<Step>> getCategoriesCall = service.getSteps(
                activity.getId(), true, token);

        getCategoriesCall.enqueue(new Callback<ArrayList<Step>>() {
            @Override
            public void onResponse(Call<ArrayList<Step>> call, Response<ArrayList<Step>> response) {
                if(response.isSuccessful()){
                    final ArrayList<Step> steps = response.body();
                    Collections.sort(steps);

                    loading.setVisibility(View.GONE);
                    letsGoButton.setOnClickListener((new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent goToStepActivity = new Intent(getApplicationContext(), StepActivity.class);

                            goToStepActivity.putExtra("activityId", activity.getId());
                            goToStepActivity.putExtra("steps", steps);
                            goToStepActivity.putExtra("backgroundColor", backgroundColor);
                            goToStepActivity.putExtra("textColor", textColor);
                            goToStepActivity.putExtra("icon", icon);

                            startActivity(goToStepActivity);
                        }
                    }));

                }else {
                    loading.setVisibility(View.GONE);
                    Intent intent = Util.whenNotSuccessful(response, context, "GET STEPS:");

                    if(intent != null)
                        startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Step>> call, Throwable t) {
                loading.setVisibility(View.GONE);
                Log.d("ERROR", "ERROR-GET-STEPS: " + t.getMessage());
            }
        });
    }
}
