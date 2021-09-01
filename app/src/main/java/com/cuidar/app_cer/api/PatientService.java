package com.cuidar.app_cer.api;

import com.cuidar.app_cer.model.ForgetPasswordBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PatientService {

    @POST("patient/forgetPassword")
    Call<Void> sendForgetPasswordEmail (@Body ForgetPasswordBody body);
}
