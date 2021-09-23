package com.cuidar.app_cer.api;

import com.cuidar.app_cer.model.history.Entry;
import com.cuidar.app_cer.model.history.EntryResponse;
import com.cuidar.app_cer.model.history.EntryBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HistoryService {

    @POST("/history")
    Call<Entry> createEntry(@Body EntryBody body, @Header("Authorization") String token);

    @GET("/history/patient/{patientId}")
    Call<EntryResponse> getHistory(
            @Path("patientId") int patientId,
            @Query("start") String start,
            @Query("end") String end,
            @Header("Authorization") String token
    );
}
