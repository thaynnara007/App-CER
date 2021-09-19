package com.cuidar.app_cer.api;

import com.cuidar.app_cer.model.Step.Step;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StepService {

    @GET("/step/activity/{activityId}")
    Call<ArrayList<Step>> getSteps (
            @Path("activityId") int activityId,
            @Query("includeImages") boolean includeImages,
            @Header("Authorization") String token
    );
}
