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
import com.cuidar.app_cer.adapter.OptionAdapter;
import com.cuidar.app_cer.api.CategoryService;
import com.cuidar.app_cer.helper.RetrofitConfig;
import com.cuidar.app_cer.model.Option;
import com.cuidar.app_cer.model.category.CategoriesPaginated;
import com.cuidar.app_cer.model.category.Category;
import com.cuidar.app_cer.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DailyLifeActivity extends AppCompatActivity {

    private Button backButton;
    private ProgressBar loading;
    private RecyclerView recyclerViewOptions;
    private List<Option> options = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    private Retrofit retrofit;
    private CategoryService service;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_life);

        context = getApplicationContext();
        retrofit = RetrofitConfig.getRetrofit();
        service = retrofit.create(CategoryService.class);

        backButton = findViewById(R.id.dailyLifeBackButton);
        loading = findViewById(R.id.loadingDailyLife);
        recyclerViewOptions = findViewById(R.id.recyclerViewDailyOptions);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewOptions.setLayoutManager(layoutManager);

        this.getCategories();

        OptionAdapter adapter = new OptionAdapter(options);
        recyclerViewOptions.setAdapter(adapter);
    }

    private void getCategories() {
        loading.setVisibility(View.VISIBLE);
        String token = Util.getAccessToken(context);
        Call<CategoriesPaginated> getCategoriesCall = service.getCategories("1", "3", token);

        getCategoriesCall.enqueue(new Callback<CategoriesPaginated>() {
            @Override
            public void onResponse(Call<CategoriesPaginated> call, Response<CategoriesPaginated> response) {
                if(response.isSuccessful()){
                    CategoriesPaginated categoriesPaginated = response.body();

                    // categories = new ArrayList<Category>();
                    Collections.addAll(categories, categoriesPaginated.getRows());

                    generateOptions();
                    loading.setVisibility(View.GONE);
                }else
                    Util.whenNotSuccessful(response, context, "GET CATEGORIES:");

            }

            @Override
            public void onFailure(Call<CategoriesPaginated> call, Throwable t) {
                Log.d("ERROR", "ERROR-GET-CATEGORIES: " + t.getMessage());
            }
        });

    }

    private void generateOptions() {
        for ( final Category category: categories) {
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToActivities = new Intent(context, MealsActivity.class);

                    goToActivities.putExtra("categoryId", category.getId());

                    startActivity(goToActivities);
                }
            };

            int icon = Util.getIcon(category.getIcon());
            Option opt = new Option(
                    category.getName(),
                    category.getDescription(),
                    icon,
                    onClickListener);

            options.add(opt);
        }

        OptionAdapter adapter = new OptionAdapter(options);
        recyclerViewOptions.setAdapter(adapter);
    }
}
