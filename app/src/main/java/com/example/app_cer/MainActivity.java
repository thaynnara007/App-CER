package com.example.app_cer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button letsGo;
    Typeface quicksand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        letsGo = findViewById(R.id.letsGoButton);
        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);
        letsGo.setTypeface(quicksand);
    }
}
