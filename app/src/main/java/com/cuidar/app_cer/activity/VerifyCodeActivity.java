package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.cuidar.app_cer.R;

public class VerifyCodeActivity extends AppCompatActivity {

    private EditText codeInput;
    private Button verifyButton, backButton;
    private Typeface quicksand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);

        codeInput = findViewById(R.id.verify_code_input);
        verifyButton = findViewById(R.id.verify_code_button);
        backButton = findViewById(R.id.verify_code_back);

        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);
        codeInput.setTypeface(quicksand, Typeface.BOLD);
        verifyButton.setTypeface(quicksand, Typeface.BOLD);
        backButton.setTypeface(quicksand, Typeface.BOLD);
    }
}
