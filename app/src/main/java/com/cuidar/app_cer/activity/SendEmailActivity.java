package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.cuidar.app_cer.R;

public class SendEmailActivity extends AppCompatActivity {

    private EditText emailInput;
    private Button sendButton, backButton;
    private Typeface quicksand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        emailInput = findViewById(R.id.send_email_input);
        sendButton = findViewById(R.id.send_email_button);
        backButton = findViewById(R.id.send_email_back);

        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);
        emailInput.setTypeface(quicksand, Typeface.BOLD);
        sendButton.setTypeface(quicksand, Typeface.BOLD);
        backButton.setTypeface(quicksand, Typeface.BOLD);
    }
}
