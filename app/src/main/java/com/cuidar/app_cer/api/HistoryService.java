package com.cuidar.app_cer.api;

import com.cuidar.app_cer.model.history.Entry;
import com.cuidar.app_cer.model.history.EntryBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface HistoryService {

    @POST("/history")
    Call<Entry> createEntry(@Body EntryBody body, @Header("Authorization") String token);
}
