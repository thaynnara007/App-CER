package com.cuidar.app_cer.api;

import com.cuidar.app_cer.model.activity.ActivitiesPaginated;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ActivityService {

    @GET("/activity/category/{categoryId}")
    Call<ActivitiesPaginated> getActivities (
            @Path("categoryId") int categoryId,
            @Query("page") int page,
            @Query("pageSize") int pageSize,
            @Header("Authorization") String token
    );
}
