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

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.api.PatientService;
import com.cuidar.app_cer.helper.RetrofitConfig;
import com.cuidar.app_cer.model.patient.ChangePasswordBody;
import com.cuidar.app_cer.utils.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText passwordInput, passwordInput2;
    private Button readyButton, backButton;
    private Typeface quicksand;

    private Retrofit retrofit;
    private PatientService service;
    private Context context;

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        retrofit = RetrofitConfig.getRetrofit();
        service = retrofit.create(PatientService.class);
        context = getApplicationContext();

        passwordInput = findViewById(R.id.change_password_input);
        passwordInput2 = findViewById(R.id.change_password_input2);
        readyButton = findViewById(R.id.change_password_button);
        backButton = findViewById(R.id.change_password_back);

        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);
        passwordInput.setTypeface(quicksand, Typeface.BOLD);
        passwordInput2.setTypeface(quicksand, Typeface.BOLD);
        readyButton.setTypeface(quicksand, Typeface.BOLD);
        backButton.setTypeface(quicksand, Typeface.BOLD);

        token = getIntent().getExtras().getString("token");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password1 = passwordInput.getText().toString();
                String password2 = passwordInput2.getText().toString();

                if ( password1 == null || password2 == null || password1.isEmpty() || password2.isEmpty()) {
                    Util.showToast(context, "Os campos de senha precisam estar preenchidos", null);
                    return;
                }
                if (!password1.equals(password2)) {
                    Util.showToast(context, "As senhas não estão iguais.", null);
                    return;
                }
                changePassword(password2, token);
            }
        });


    }

    private void changePassword (String newPassword, String token) {
        ChangePasswordBody body = new ChangePasswordBody(newPassword);

        Call<Void> changePasswordCall = service.changePassword(body, token);

        changePasswordCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Util.showToast(context, "Senha alterada", null);

                    Intent goToLoginActivity = new Intent(context, Login.class);
                    goToLoginActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    startActivity(goToLoginActivity);
                }else
                    Util.whenNotSuccessful(response, context, "CHANGE PASSWORD:");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("ERROR", "ERROR-CHANGE-PASSWORD: " + t.getMessage());
            }
        });
    }


}
