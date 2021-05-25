package com.example.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app_cer.R;

public class MainActivity extends AppCompatActivity {

    private Button letsGo;
    private Typeface quicksand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        letsGo = findViewById(R.id.letsGoButton);

        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);
        letsGo.setTypeface(quicksand, Typeface.BOLD);

        letsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLoginActivity = new Intent(
                        getApplicationContext(),
                        Login.class
                );

                startActivity(goToLoginActivity);
            }
        });
    }
}
