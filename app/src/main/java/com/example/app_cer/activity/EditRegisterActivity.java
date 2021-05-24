package com.example.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.app_cer.R;

public class EditRegisterActivity extends AppCompatActivity {

    private EditText nameInput, genderInput, ageInput, phoneInput, emailInput, passwordInput;
    private Button editButton;
    private Typeface quicksand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_register);

        nameInput = findViewById(R.id.nameInputEdit);
        genderInput = findViewById(R.id.genderInputEdit);
        ageInput = findViewById(R.id.ageInputEdit);
        phoneInput = findViewById(R.id.phoneInputEdit);
        emailInput = findViewById(R.id.emailRegisterInputEdit);
        passwordInput = findViewById(R.id.passwordRegisterInputEdit);
        editButton = findViewById(R.id.registerEditButton);

        quicksand = ResourcesCompat.getFont(getApplicationContext(), R.font.quicksand_medium);

        nameInput.setTypeface(quicksand);
        genderInput.setTypeface(quicksand);
        ageInput.setTypeface(quicksand);
        phoneInput.setTypeface(quicksand);
        emailInput.setTypeface(quicksand);
        passwordInput.setTypeface(quicksand);
        editButton.setTypeface(quicksand);
    }
}
