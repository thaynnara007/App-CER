package com.cuidar.app_cer.api;

import com.cuidar.app_cer.model.patient.ChangePasswordBody;
import com.cuidar.app_cer.model.patient.ForgetPasswordBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PatientService {

    @POST("patient/forgetPassword")
    Call<Void> sendForgetPasswordEmail (@Body ForgetPasswordBody body);

    @PUT("patient/changePassword")
    Call<Void> changePassword (@Body ChangePasswordBody body, @Header("Authorization") String token);

}
