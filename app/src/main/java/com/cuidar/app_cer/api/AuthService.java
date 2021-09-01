package com.cuidar.app_cer.api;

import com.cuidar.app_cer.model.login.LoginBody;
import com.cuidar.app_cer.model.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("auth/patient/login")
    Call<LoginResponse> login(@Body LoginBody body);

}
