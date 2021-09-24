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
import com.cuidar.app_cer.helper.PaginationScrollListener;
import com.cuidar.app_cer.helper.RetrofitConfig;
import com.cuidar.app_cer.model.Option;
import com.cuidar.app_cer.model.category.CategoriesPaginated;
import com.cuidar.app_cer.model.category.Category;
import com.cuidar.app_cer.utils.Constants;
import com.cuidar.app_cer.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DailyLifeActivity extends AppCompatActivity {

    private Button backButton;
    private ProgressBar loading;
    private RecyclerView recyclerViewOptions;

    private Retrofit retrofit;
    private CategoryService service;
    private Context context;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = Constants.DEFAULT_CURRENT_PAGE;

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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewOptions.setLayoutManager(layoutManager);

        final OptionAdapter adapter = new OptionAdapter(new ArrayList<Option>());
        recyclerViewOptions.setAdapter(adapter);

        recyclerViewOptions.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                currentPage += 1;
                getCategories(adapter);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        getCategories(adapter);
    }

    private void getCategories(final OptionAdapter adapter) {
        isLoading = true;
        loading.setVisibility(View.VISIBLE);

        String token = Util.getAccessToken(context);
        Call<CategoriesPaginated> getCategoriesCall = service.getCategories(
                currentPage, Constants.DEFAULT_PAGE_SIZE, token);

        getCategoriesCall.enqueue(new Callback<CategoriesPaginated>() {
            @Override
            public void onResponse(Call<CategoriesPaginated> call, Response<CategoriesPaginated> response) {
                if(response.isSuccessful()){
                    CategoriesPaginated categoriesPaginated = response.body();

                    List<Option> newOptions = generateOptions(categoriesPaginated.getRows());
                    adapter.addAll(newOptions);

                    if (currentPage >= categoriesPaginated.getPages())
                        isLastPage = true;

                    isLoading = false;
                    loading.setVisibility(View.GONE);
                }else {
                    loading.setVisibility(View.GONE);
                    isLoading = false;

                    Intent intent = Util.whenNotSuccessful(response, context, "GET CATEGORIES:");

                    if(intent != null)
                        startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CategoriesPaginated> call, Throwable t) {
                Log.d("ERROR", "ERROR-GET-CATEGORIES: " + t.getMessage());

                loading.setVisibility(View.GONE);
                isLoading = false;
            }
        });
    }

    private List<Option> generateOptions(Category[] categories) {
        List<Option> options = new ArrayList<>();

        for ( final Category category: categories) {
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToActivities = new Intent(context, CategoryActivitiesActivity.class);

                    goToActivities.putExtra("category", category);

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

        return options;
    }
}
