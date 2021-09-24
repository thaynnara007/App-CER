package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.adapter.StatusAdapter;
import com.cuidar.app_cer.api.HistoryService;
import com.cuidar.app_cer.helper.RetrofitConfig;
import com.cuidar.app_cer.model.StatusCard;
import com.cuidar.app_cer.model.history.Entry;
import com.cuidar.app_cer.model.history.EntryResponse;
import com.cuidar.app_cer.model.history.HistoryResponse;
import com.cuidar.app_cer.user_preferences.ActivityData;
import com.cuidar.app_cer.utils.MyCalendar;
import com.cuidar.app_cer.utils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ControlActivity extends AppCompatActivity {

    private Button backButton;
    private RecyclerView recyclerViewOptions;
    private ProgressBar loading;
    private List<StatusCard> options = new ArrayList<>();

    private ActivityData data;
    private MyCalendar calendar;

    private Context context;
    private Retrofit retrofit;
    private HistoryService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        context = getApplicationContext();
        retrofit = RetrofitConfig.getRetrofit();
        service = retrofit.create(HistoryService.class);
        data = new ActivityData(context);
        calendar = new MyCalendar();

        backButton = findViewById(R.id.controlBackButton);
        recyclerViewOptions = findViewById(R.id.recyclerViewControl);
        loading = findViewById(R.id.controlLoading);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewOptions.setLayoutManager(layoutManager);

        StatusAdapter adapter = new StatusAdapter(options);
        recyclerViewOptions.setAdapter(adapter);

        this.getHistoryEntries();
    }

    private void getHistoryEntries(){
        int patientId = data.getUserId();
        String start = calendar.getFirstDayOfMonth();
        String end = calendar.getLastDayOfMonth();
        String token = Util.getAccessToken(context);

        Call<HistoryResponse> getHistory = service.getHistory(
                patientId, start, end, token
        );

        getHistory.enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                if(response.isSuccessful()){
                    HistoryResponse history = response.body();
                    Log.d("TAG", "onResponse: " + history.getResult());

                    generateStatusCard(history);

                    StatusAdapter adapter = new StatusAdapter(options);
                    recyclerViewOptions.setAdapter(adapter);
                    loading.setVisibility(View.GONE);

                } else {
                    loading.setVisibility(View.GONE);
                    Intent intent = Util.whenNotSuccessful(response, context, "GET HISTORY:");

                    if(intent != null)
                        startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                Log.d("ERROR", "ERROR-GET-HISTORY: " + t.getMessage());
            }
        });
    }

    private void generateStatusCard(HistoryResponse response) {
        HashMap<String, EntryResponse> history = response.getResult();

        for (String category : history.keySet()){
            EntryResponse entry = history.get(category);
            int icon = Util.getIcon(entry.getIcon());

            StatusCard card = new StatusCard(category, icon, entry.getActivities());
            this.options.add(card);
        }

    }
}
