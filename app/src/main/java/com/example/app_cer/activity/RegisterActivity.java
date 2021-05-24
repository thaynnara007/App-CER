package com.example.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app_cer.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameInput, genderInput, ageInput, phoneInput, emailInput, passwordInput;
    private Button backButton, registerButton;
    private Typeface quicksand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameInput = findViewById(R.id.nameInput);
        genderInput = findViewById(R.id.genderInput);
        ageInput = findViewById(R.id.ageInput);
        phoneInput = findViewById(R.id.phoneInput);
        emailInput = findViewById(R.id.emailRegisterInput);
        passwordInput = findViewById(R.id.passwordRegisterInput);
        backButton = findViewById(R.id.registerBackButton);
        registerButton = findViewById(R.id.registerPageButton);

        quicksand = ResourcesCompat.getFont(getApplicationContext(), R.font.quicksand_medium);

        nameInput.setTypeface(quicksand);
        genderInput.setTypeface(quicksand);
        ageInput.setTypeface(quicksand);
        phoneInput.setTypeface(quicksand);
        emailInput.setTypeface(quicksand);
        passwordInput.setTypeface(quicksand);
        registerButton.setTypeface(quicksand);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
