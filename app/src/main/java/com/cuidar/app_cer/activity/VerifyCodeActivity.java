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
import com.cuidar.app_cer.model.auth.VerifyCodeBody;
import com.cuidar.app_cer.model.auth.VerifyCodeResponse;
import com.cuidar.app_cer.utils.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VerifyCodeActivity extends AppCompatActivity {

    private EditText codeInput;
    private Button verifyButton, backButton;
    private Typeface quicksand;
    private ProgressBar loading;
    private String email;

    private Retrofit retrofit;
    private AuthService service;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);

        retrofit = RetrofitConfig.getRetrofit();
        service = retrofit.create(AuthService.class);
        context = getApplicationContext();

        codeInput = findViewById(R.id.verify_code_input);
        verifyButton = findViewById(R.id.verify_code_button);
        backButton = findViewById(R.id.verify_code_back);
        loading = findViewById(R.id.loadingVerifyCode);

        quicksand = ResourcesCompat.getFont(getBaseContext(), R.font.quicksand_medium);
        codeInput.setTypeface(quicksand, Typeface.BOLD);
        verifyButton.setTypeface(quicksand, Typeface.BOLD);
        backButton.setTypeface(quicksand, Typeface.BOLD);

        email = getIntent().getExtras().getString("email");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = codeInput.getText().toString();

                if (code == null || code.equals(""))
                    Util.showToast(context,"O código precisa ser informado.", null);
                else
                    verifyCode(code);
            }
        });
    }

    private void verifyCode (String code){
        loading.setVisibility(View.VISIBLE);
        VerifyCodeBody body = new VerifyCodeBody(email, code);

        Call<VerifyCodeResponse> verifyCodeCall = service.verifyCode(body);

        verifyCodeCall.enqueue(new Callback<VerifyCodeResponse>() {
            @Override
            public void onResponse(Call<VerifyCodeResponse> call, Response<VerifyCodeResponse> response) {
                if (response.isSuccessful()){
                    VerifyCodeResponse responseBody = response.body();

                    Intent goToChangePasswordActivity = new Intent(context, ChangePasswordActivity.class);
                    goToChangePasswordActivity.putExtra("token", responseBody.getToken());

                    loading.setVisibility(View.GONE);
                    startActivity(goToChangePasswordActivity);
                }else
                    Util.whenNotSuccessful(response, context, "VERIFY CODE:" );
            }

            @Override
            public void onFailure(Call<VerifyCodeResponse> call, Throwable t) {
                Log.d("ERROR", "ERROR-VERIFY-CODE: " + t.getMessage());
            }
        });

    }
}
