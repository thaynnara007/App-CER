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
import com.cuidar.app_cer.model.ForgetPasswordBody;
import com.cuidar.app_cer.utils.Util;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SendEmailActivity extends AppCompatActivity {

    private EditText emailInput;
    private Button sendButton, backButton;
    private Typeface quicksand;
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
                else {
                    sendEmail(email);

                    Intent goToVerifyCodeActivity = new Intent(context, VerifyCodeActivity.class);
                    goToVerifyCodeActivity.putExtra("email", email);

                    startActivity(goToVerifyCodeActivity);
                }
            }
        });


    }

    private void sendEmail(String email){
        ForgetPasswordBody body = new ForgetPasswordBody(email);
        Call<Void> sendEmailCall = service.sendForgetPasswordEmail(body);

        sendEmailCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                    Util.showToast(context, "Email enviado.", null);
                else
                    Util.whenNotSuccessful(response, context, "SEND EMAIL");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("ERROR", "ERROR-SEND-EMAIL: " + t.getMessage());
            }
        });
    }
}
