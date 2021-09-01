package com.cuidar.app_cer.api;

import com.cuidar.app_cer.model.auth.LoginBody;
import com.cuidar.app_cer.model.auth.LoginResponse;
import com.cuidar.app_cer.model.auth.VerifyCodeBody;
import com.cuidar.app_cer.model.auth.VerifyCodeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("auth/patient/login")
    Call<LoginResponse> login(@Body LoginBody body);

    @POST("auth/patient/verifyCode")
    Call<VerifyCodeResponse> verifyCode (@Body VerifyCodeBody body);

}
