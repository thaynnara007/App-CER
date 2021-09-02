package com.cuidar.app_cer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.api.PatientService;
import com.cuidar.app_cer.helper.RetrofitConfig;
import com.cuidar.app_cer.model.address.Address;
import com.cuidar.app_cer.model.patient.Patient;
import com.cuidar.app_cer.utils.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditRegisterActivity extends AppCompatActivity {

    private EditText nameInput, lastNameInput, ageInput, phoneInput, emailInput, passwordInput, confirmPasswordInput;
    private EditText stateInput, cityInput, zipCodeInput, districtInput, streetInput, numberInput;
    private Button editButton, backButton;
    private Typeface quicksand;

    private Retrofit retrofit;
    private PatientService service;
    private Context context;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_register);

        context = getApplicationContext();
        retrofit = RetrofitConfig.getRetrofit();
        service = retrofit.create(PatientService.class);
        token = Util.getAccessToken(context);

        nameInput = findViewById(R.id.nameInputEdit);
        lastNameInput = findViewById(R.id.lastNameInputEdit);
        ageInput = findViewById(R.id.ageInputEdit);
        phoneInput = findViewById(R.id.phoneInputEdit);
        emailInput = findViewById(R.id.emailRegisterInputEdit);
        passwordInput = findViewById(R.id.passwordRegisterInputEdit);
        confirmPasswordInput = findViewById(R.id.passwordRegisterInputEdit2);
        stateInput = findViewById(R.id.stateInputEdit);
        cityInput = findViewById(R.id.cityInputEdit);
        zipCodeInput = findViewById(R.id.zipCodeInputEdit);
        districtInput = findViewById(R.id.districtInputEdit);
        streetInput = findViewById(R.id.streetInputEdit);
        numberInput = findViewById(R.id.numberInputEdit);
        backButton = findViewById(R.id.editBackButton);
        editButton = findViewById(R.id.registerEditButton);

        quicksand = ResourcesCompat.getFont(getApplicationContext(), R.font.quicksand_medium);

        nameInput.setTypeface(quicksand, Typeface.BOLD);
        lastNameInput.setTypeface(quicksand, Typeface.BOLD);
        ageInput.setTypeface(quicksand, Typeface.BOLD);
        phoneInput.setTypeface(quicksand, Typeface.BOLD);
        emailInput.setTypeface(quicksand, Typeface.BOLD);
        passwordInput.setTypeface(quicksand, Typeface.BOLD);
        confirmPasswordInput.setTypeface(quicksand, Typeface.BOLD);
        stateInput.setTypeface(quicksand, Typeface.BOLD);
        cityInput.setTypeface(quicksand, Typeface.BOLD);
        zipCodeInput.setTypeface(quicksand, Typeface.BOLD);
        districtInput.setTypeface(quicksand, Typeface.BOLD);
        streetInput.setTypeface(quicksand, Typeface.BOLD);
        numberInput.setTypeface(quicksand, Typeface.BOLD);
        editButton.setTypeface(quicksand, Typeface.BOLD);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getPatient();
    }

    private void getPatient(){
        Call<Patient> getPatientCall = service.getPatient(token);

        getPatientCall.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                if(response.isSuccessful()){
                    Patient patient = response.body();
                    Address address = patient.getAddress();

                    int day = 1;
                    int year = 0;
                    int month = 2;
                    String[] birthdayNums = patient.getBirthday().split("T")[0].split("-");
                    String birthday = String.format(
                            "%s/%s/%s", birthdayNums[day], birthdayNums[month], birthdayNums[year]
                    );

                    nameInput.setText(patient.getName());
                    lastNameInput.setText(patient.getLastName());
                    emailInput.setText(patient.getEmail());
                    phoneInput.setText(patient.getPhoneNumber());
                    ageInput.setText(birthday);

                    if (address != null) {
                        stateInput.setText(address.getState());
                        cityInput.setText(address.getCity());
                        zipCodeInput.setText(address.getZipCode());
                        districtInput.setText(address.getDistrict());
                        streetInput.setText(address.getStreet());
                        numberInput.setText(address.getNumber());
                    }

                }else
                    Util.whenNotSuccessful(response, context, "GET PATIENT:");
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Log.d("ERROR", "ERROR-GET-PATIENT: " + t.getMessage());
            }
        });
    }

    private void updatePatient() {

    }
}
