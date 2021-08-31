package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.user_preferences.ActivityData;

public class Login extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button forgetPasswordButton, getInButton;
    private Typeface quicksand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        forgetPasswordButton = findViewById(R.id.forgetPasswordButton);
        getInButton = findViewById(R.id.getInButton);

        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);
        emailInput.setTypeface(quicksand, Typeface.BOLD);
        passwordInput.setTypeface(quicksand, Typeface.BOLD);
        forgetPasswordButton.setTypeface(quicksand, Typeface.BOLD);
        getInButton.setTypeface(quicksand, Typeface.BOLD);

        final Context context = getApplicationContext();

        getInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMenuActivity = new Intent(
                        context,
                        MenuActivity.class
                );

                startActivity(goToMenuActivity);
            }
        });

    }
}
