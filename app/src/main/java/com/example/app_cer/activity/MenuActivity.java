package com.example.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;

import com.example.app_cer.R;


public class MenuActivity extends AppCompatActivity {

    private Button exitButton;
    private Typeface quicksand;
    private RecyclerView recyclerViewOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        exitButton = findViewById(R.id.exitButton);

        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);

        exitButton.setTypeface(quicksand);
    }
}
