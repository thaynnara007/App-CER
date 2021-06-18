package com.example.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app_cer.R;
import com.example.app_cer.adapter.StatusAdapter;
import com.example.app_cer.model.Status;
import com.example.app_cer.model.StatusCard;
import com.example.app_cer.user_preferences.ActivityData;
import com.example.app_cer.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ControlActivity extends AppCompatActivity {

    private Button backButton;
    private RecyclerView recyclerViewOptions;
    private List<StatusCard> options = new ArrayList<>();
    private ActivityData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        backButton = findViewById(R.id.controlBackButton);
        recyclerViewOptions = findViewById(R.id.recyclerViewControl);
        data = new ActivityData(getApplicationContext());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewOptions.setLayoutManager(layoutManager);

        this.generateOptions();

        StatusAdapter adapter = new StatusAdapter(options);
        recyclerViewOptions.setAdapter(adapter);
    }

    private void generateOptions(){
        ArrayList<Status> status1 = new ArrayList<>();
        status1.add(new Status(Constants.SOLID_NAME, data.getStatus(Constants.SOLID_NAME)));
        status1.add(new Status(Constants.LIQUID_NAME, data.getStatus(Constants.LIQUID_NAME)));
        status1.add(new Status(Constants.PASTY_NAME, data.getStatus(Constants.LIQUID_NAME)));

        ArrayList<Status> status2 = new ArrayList<>();
        status2.add(new Status(Constants.BODY_NAME, data.getStatus(Constants.BODY_NAME)));
        status2.add(new Status(Constants.TEETH_NAME, data.getStatus(Constants.TEETH_NAME)));

        ArrayList<Status> status3 = new ArrayList<>();
        status3.add(new Status(Constants.SHIRT_NAME, data.getStatus(Constants.SHIRT_NAME)));
        status3.add(new Status(Constants.PANTS_NAME, data.getStatus(Constants.PANTS_NAME)));

        StatusCard statusCard1 = new StatusCard(
                Constants.MEAL_NAME,
                R.drawable.meal_fade_vector,
                status1
        );

        StatusCard statusCard2 = new StatusCard(
                Constants.HYGINE_NAME,
                R.drawable.hygine_fade_vector,
                status2
        );

        StatusCard statusCard3 = new StatusCard(
                Constants.CLOTHES_NAME,
                R.drawable.hanger_fade_vector,
                status3
        );

        this.options.add(statusCard1);
        this.options.add(statusCard2);
        this.options.add(statusCard3);
    }
}
