package com.cuidar.app_cer.api;

import com.cuidar.app_cer.model.Step.Step;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface StepService {

    @GET("/step/activity/:activityId")
    Call<List<Step>> getSteps (
            @Path("activityId") int activityId,
            @Header("Authorization") String token
    );
}
