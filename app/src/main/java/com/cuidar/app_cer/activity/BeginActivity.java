package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.user_preferences.ActivityData;
import com.cuidar.app_cer.utils.Constants;

public class BeginActivity extends AppCompatActivity {

    private Button letsGo;
    private TextView welcomeText;
    private TextView mainText;
    private TextView hiddenText;
    private Typeface quicksand;
    private ActivityData dataFile;
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        welcomeText = findViewById(R.id.welcome_text);
        hiddenText = findViewById(R.id.begin_hidden_text);
        mainText = findViewById(R.id.begin_main_text);
        letsGo = findViewById(R.id.letsGoButton);
        dataFile = new ActivityData(getApplicationContext());

        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);
        letsGo.setTypeface(quicksand, Typeface.BOLD);

        accessToken = dataFile.getToken();

        if (!accessToken.equals("")){
            hiddenText.setText(Constants.BEGIN_HIDDEN_TEXT);
            mainText.setText(dataFile.getUserName() + "!");
            welcomeText.setText(Constants.BEGIN_WELCOME_TEXT);
        }

        letsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNextActivity = new Intent(
                        getApplicationContext(),
                        MenuActivity.class
                );

                if (accessToken.equals("")) {
                    goToNextActivity = new Intent(
                            getApplicationContext(),
                            Login.class
                    );
                }

                startActivity(goToNextActivity);
                finish();
            }
        });
    }
}
