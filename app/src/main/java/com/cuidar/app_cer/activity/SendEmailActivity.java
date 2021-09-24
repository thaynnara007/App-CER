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
import com.cuidar.app_cer.api.PatientService;
import com.cuidar.app_cer.helper.RetrofitConfig;
import com.cuidar.app_cer.model.patient.ForgetPasswordBody;
import com.cuidar.app_cer.utils.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SendEmailActivity extends AppCompatActivity {

    private EditText emailInput;
    private Button sendButton, backButton;
    private Typeface quicksand;
    private ProgressBar loading;

    private Retrofit retrofit;
    private PatientService service;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        retrofit = RetrofitConfig.getRetrofit();
        service = retrofit.create(PatientService.class);
        context = getApplicationContext();

        emailInput = findViewById(R.id.send_email_input);
        sendButton = findViewById(R.id.send_email_button);
        backButton = findViewById(R.id.send_email_back);
        loading = findViewById(R.id.loadingSendEmail);

        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);
        emailInput.setTypeface(quicksand, Typeface.BOLD);
        sendButton.setTypeface(quicksand, Typeface.BOLD);
        backButton.setTypeface(quicksand, Typeface.BOLD);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();

                if (email == null || email.equals(""))
                    Util.showToast(context, "O email deve ser preenchido.", null);
                else
                    sendEmail(email);
            }
        });


    }

    private void sendEmail(String email){
        loading.setVisibility(View.VISIBLE);

        final ForgetPasswordBody body = new ForgetPasswordBody(email);
        Call<Void> sendEmailCall = service.sendForgetPasswordEmail(body);

        sendEmailCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Util.showToast(context, "Email enviado.", null);

                    Intent goToVerifyCodeActivity = new Intent(context, VerifyCodeActivity.class);
                    goToVerifyCodeActivity.putExtra("email", body.getEmail());

                    loading.setVisibility(View.GONE);
                    startActivity(goToVerifyCodeActivity);
                }else {
                    loading.setVisibility(View.GONE);
                    Util.whenNotSuccessful(response, context, "SEND EMAIL");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("ERROR", "ERROR-SEND-EMAIL: " + t.getMessage());
                loading.setVisibility(View.GONE);
            }
        });
    }
}
