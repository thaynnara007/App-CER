package com.cuidar.app_cer.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.cuidar.app_cer.utils.Constants;

public class RetrofitConfig {

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
