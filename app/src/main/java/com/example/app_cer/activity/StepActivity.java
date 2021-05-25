package com.example.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;

import com.example.app_cer.R;

public class StepActivity extends AppCompatActivity {

    ConstraintLayout currentLayout;
    Button nextStepButton, beforeButton;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        currentLayout = findViewById(R.id.step_layout);
        int color = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryYellow);

        currentLayout.setBackgroundColor(color);
    }
}
