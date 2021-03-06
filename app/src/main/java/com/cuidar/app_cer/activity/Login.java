package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.api.AuthService;
import com.cuidar.app_cer.helper.RetrofitConfig;
import com.cuidar.app_cer.model.auth.LoginBody;
import com.cuidar.app_cer.model.auth.LoginResponse;
import com.cuidar.app_cer.model.patient.Patient;
import com.cuidar.app_cer.user_preferences.ActivityData;
import com.cuidar.app_cer.utils.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button forgetPasswordButton, getInButton;
    private Typeface quicksand;
    private ProgressBar loading;

    private Retrofit retrofit;
    private AuthService service;
    private ActivityData dataFile;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = getApplicationContext();

        retrofit = RetrofitConfig.getRetrofit();
        service = retrofit.create(AuthService.class);
        dataFile = new ActivityData(context);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        forgetPasswordButton = findViewById(R.id.forgetPasswordButton);
        getInButton = findViewById(R.id.getInButton);
        loading = findViewById(R.id.loadingLogin);

        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);
        emailInput.setTypeface(quicksand, Typeface.BOLD);
        passwordInput.setTypeface(quicksand, Typeface.BOLD);
        forgetPasswordButton.setTypeface(quicksand, Typeface.BOLD);
        getInButton.setTypeface(quicksand, Typeface.BOLD);


        getInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if ((email != null && !email.equals("")) && (password != null && !password.equals("")))
                    login(email, password);
                else
                    Util.showToast(context, "O email e a senha precisam estar preenchidos.", null);
            }
        });

        forgetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSendEmailActivity = new Intent(context, SendEmailActivity.class);
                startActivity(goToSendEmailActivity);
            }
        });

    }
    private void login(String email, String password){
        loading.setVisibility(View.VISIBLE);
        LoginBody body = new LoginBody(email, password);

        Call<LoginResponse> loginCall = service.login(body);

        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    Patient patient = loginResponse.getPatient();

                    dataFile.postToken(loginResponse.getToken());
                    dataFile.postUserName(loginResponse.getPatient().getName());
                    dataFile.postUserId(loginResponse.getPatient().getId());

                    Intent goToMenuActivity = new Intent(
                            context,
                            MenuActivity.class
                    );
                    goToMenuActivity.putExtra("firstLogin", patient.getFirstLogin());

                    loading.setVisibility(View.GONE);
                    startActivity(goToMenuActivity);
                }else {
                    Util.whenNotSuccessful(response, context, "LOGIN");
                    loading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("ERROR", "ERROR-LOGIN: " + t.getMessage());
                loading.setVisibility(View.GONE);
            }
        });
    }
}
