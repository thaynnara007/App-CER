package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cuidar.app_cer.R;

public class EditRegisterActivity extends AppCompatActivity {

    private EditText nameInput, genderInput, ageInput, phoneInput, emailInput, passwordInput;
    private Button editButton, backButton;
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
        backButton = findViewById(R.id.editBackButton);
        editButton = findViewById(R.id.registerEditButton);

        quicksand = ResourcesCompat.getFont(getApplicationContext(), R.font.quicksand_medium);

        nameInput.setTypeface(quicksand, Typeface.BOLD);
        genderInput.setTypeface(quicksand, Typeface.BOLD);
        ageInput.setTypeface(quicksand, Typeface.BOLD);
        phoneInput.setTypeface(quicksand, Typeface.BOLD);
        emailInput.setTypeface(quicksand, Typeface.BOLD);
        passwordInput.setTypeface(quicksand, Typeface.BOLD);
        editButton.setTypeface(quicksand, Typeface.BOLD);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
