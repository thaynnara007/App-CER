package com.example.app_cer.activity;

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

import com.example.app_cer.R;

public class Login extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private CheckBox rememberMeCheckBox;
    private Button forgetPasswordButton, getInButton, registerButton;
    private Typeface quicksand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);
        forgetPasswordButton = findViewById(R.id.forgetPasswordButton);
        getInButton = findViewById(R.id.getInButton);
        registerButton = findViewById(R.id.registerButton);

        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);
        emailInput.setTypeface(quicksand);
        passwordInput.setTypeface(quicksand);
        rememberMeCheckBox.setTypeface(quicksand);
        forgetPasswordButton.setTypeface(quicksand);
        getInButton.setTypeface(quicksand);
        registerButton.setTypeface(quicksand);

        final Context context = getApplicationContext();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRegisterActivity = new Intent(
                        context,
                        RegisterActivity.class
                );

                startActivity(goToRegisterActivity);
            }
        });

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
