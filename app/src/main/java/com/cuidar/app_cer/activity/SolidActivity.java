package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.model.Step;
import com.cuidar.app_cer.model.activity.Activity;
import com.cuidar.app_cer.utils.Util;

import java.util.ArrayList;

public class SolidActivity extends AppCompatActivity {

    private Button backButton, letsGoButton;
    private Typeface quicksand;
    private TextView title;
    private TextView subtitle;
    private TextView description;
    private ImageView iconImage;
    private ConstraintLayout layout;
    private ArrayList<Step> steps = new ArrayList<>();

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid);

        title = findViewById(R.id.activityTitle);
        subtitle = findViewById(R.id.activitySubtitle);
        description = findViewById(R.id.activityDescription);
        iconImage = findViewById(R.id.activityIcon);
        layout = findViewById(R.id.activityLayout);
        backButton = findViewById(R.id.solidBackButton);
        letsGoButton = findViewById(R.id.solidStartButton);
        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);

        Bundle data = getIntent().getExtras();
        activity = (Activity) data.getSerializable("activity");
        final int backgroundColor = data.getInt("backgroundColor");
        final int textColor = data.getInt("textColor");
        String categoryName = data.getString("categoryName");
        final int icon = Util.getIcon(activity.getIcon());

        layout.setBackgroundColor(backgroundColor);
        title.setText(activity.getName());
        title.setTextColor(textColor);
        subtitle.setText(categoryName);
        subtitle.setTextColor(textColor);
        description.setText(activity.getDescription());
        description.setTextColor(textColor);
        iconImage.setImageResource(icon);

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

                goToStepActivity.putExtra("activityName", activity.getName());
                goToStepActivity.putExtra("steps", steps);
                goToStepActivity.putExtra("backgroundColor", backgroundColor);
                goToStepActivity.putExtra("textColor", textColor);
                goToStepActivity.putExtra("icon", icon);

                startActivity(goToStepActivity);
            }
        }));
    }

    private void genereteSteps(){
        Step step1 = new Step(
                "Etapa 1",
                "Fale o nome da fruta para a criança!",
                R.drawable.solid_step1
        );

        Step step2 = new Step(
                "Etapa 2",
                "Coloque a criança para sentir o cheiro da fruta!",
                R.drawable.solid_step2
        );

        Step step3 = new Step(
                "Etapa 3",
                "Coloque a criança para pegar a fruta!",
                R.drawable.solid_step3
        );

        Step step4 = new Step(
                "Etapa 4",
                "Coloque a criança para comer a fruta!",
                R.drawable.solid_step4
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
