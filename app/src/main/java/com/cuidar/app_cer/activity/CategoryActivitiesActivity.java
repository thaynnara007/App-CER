package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.adapter.OptionAdapter;
import com.cuidar.app_cer.api.ActivityService;
import com.cuidar.app_cer.helper.PaginationScrollListener;
import com.cuidar.app_cer.helper.RetrofitConfig;
import com.cuidar.app_cer.model.Option;
import com.cuidar.app_cer.model.activity.ActivitiesPaginated;
import com.cuidar.app_cer.model.activity.Activity;
import com.cuidar.app_cer.model.category.Category;
import com.cuidar.app_cer.utils.Constants;
import com.cuidar.app_cer.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryActivitiesActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private TextView title;
    private TextView description;
    private Button backButton;
    private RecyclerView recyclerViewOptions;
    private ProgressBar loading;

    private Retrofit retrofit;
    private ActivityService service;
    private Context context;

    private Category category;
    private int backgroundColor;
    private int textColor;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = Constants.DEFAULT_CURRENT_PAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

        context = getApplicationContext();
        retrofit = RetrofitConfig.getRetrofit();
        service = retrofit.create(ActivityService.class);

        layout = findViewById(R.id.categoryActivitiesLayout);
        title = findViewById(R.id.categoryTitle);
        description = findViewById(R.id.categoryPageDescription);
        backButton = findViewById(R.id.mealsBackButton);
        recyclerViewOptions = findViewById(R.id.recyclerViewMealsOptions);
        loading = findViewById(R.id.loadingActivities);

        category = (Category) getIntent().getExtras().getSerializable("category");
        backgroundColor = Color.parseColor(category.getColor());
        textColor = Color.parseColor(category.getTextColor());

        layout.setBackgroundColor(backgroundColor);
        title.setText(category.getName());
        title.setTextColor(textColor);
        description.setText(category.getPageDescription());
        description.setTextColor(textColor);

        DrawableCompat.setTint(backButton.getBackground(), textColor);
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
                getActivities(adapter);
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

        getActivities(adapter);
    }

    private void getActivities(final OptionAdapter adapter) {
        isLoading = true;
        loading.setVisibility(View.VISIBLE);

        String token = Util.getAccessToken(context);
        Call<ActivitiesPaginated> getCategoriesCall = service.getActivities(
                category.getId(), currentPage, Constants.DEFAULT_PAGE_SIZE, token);

        getCategoriesCall.enqueue(new Callback<ActivitiesPaginated>() {
            @Override
            public void onResponse(Call<ActivitiesPaginated> call, Response<ActivitiesPaginated> response) {
                if(response.isSuccessful()){
                    ActivitiesPaginated activitiesPaginated = response.body();
                    category = activitiesPaginated.getCategory();

                    List<Option> newOptions = generateOptions(activitiesPaginated.getRows());
                    adapter.addAll(newOptions);

                    if (currentPage >= activitiesPaginated.getPages())
                        isLastPage = true;

                    isLoading = false;
                    loading.setVisibility(View.GONE);
                }else
                    Util.whenNotSuccessful(response, context, "GET CATEGORIES:");
            }

            @Override
            public void onFailure(Call<ActivitiesPaginated> call, Throwable t) {
                Log.d("ERROR", "ERROR-GET-CATEGORIES: " + t.getMessage());
            }
        });
    }

    private List<Option> generateOptions(Activity[] activities) {
        List<Option> options = new ArrayList<>();

        for ( final Activity activity: activities) {
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToActivities = new Intent(context, SolidActivity.class);

                    goToActivities.putExtra("backgroundColor", backgroundColor);
                    goToActivities.putExtra("textColor", textColor);
                    goToActivities.putExtra("categoryName", category.getName());
                    goToActivities.putExtra("activity", activity);

                    startActivity(goToActivities);
                }
            };

            int icon = Util.getIcon(activity.getIcon());
            Option opt = new Option(
                    activity.getName(),
                    activity.getDescription(),
                    icon,
                    onClickListener);

            options.add(opt);
        }

        return options;
    }
}
