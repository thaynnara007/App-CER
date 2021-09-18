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
import android.widget.ProgressBar;

import com.cuidar.app_cer.R;
import com.cuidar.app_cer.api.PatientService;
import com.cuidar.app_cer.helper.RetrofitConfig;
import com.cuidar.app_cer.model.address.Address;
import com.cuidar.app_cer.model.patient.EditPatientBody;
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
    private ProgressBar loading;

    private Retrofit retrofit;
    private PatientService service;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_register);

        context = getApplicationContext();
        retrofit = RetrofitConfig.getRetrofit();
        service = retrofit.create(PatientService.class);


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
        loading = findViewById(R.id.loadingEditProfile);

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

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Patient patient = getPatientInfo();
                Address address = getAddressInfo();

                if (patient != null && address != null){
                    updatePatient(patient, address);
                }
            }
        });

        getPatient();
    }

    private Patient getPatientInfo(){
        String name = nameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String birthday = ageInput.getText().toString();
        String phoneNumber = phoneInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String password2 = confirmPasswordInput.getText().toString();

        Boolean valid = Util.isValid(birthday, "dd/MM/yyyy");

        if (!valid){
            Util.showToast(context, "Formato de data incorreto.", null);
            return null;
        }
        if (!password.equals(password2)) {
            Util.showToast(context, "As senhas precisam estar iguais", null);
            return  null;
        }

        int day = 0;
        int month = 1;
        int year = 2;

        String[] birthdayArray = birthday.split("/");
        String birthdayFormatted = String.format(
                "%s/%s/%s", birthdayArray[month], birthdayArray[day], birthdayArray[year]
        );

        Patient patient = new Patient(name, lastName, birthdayFormatted, phoneNumber, email, password);
        return patient;

    }

    private Address getAddressInfo(){
        String state = stateInput.getText().toString();
        String city = cityInput.getText().toString();
        String zipCode = zipCodeInput.getText().toString();
        String district = districtInput.getText().toString();
        String street = streetInput.getText().toString();
        String number = numberInput.getText().toString();

        Address address = new Address(state, city, zipCode, district, street, number);

        return  address;
    }

    private void getPatient(){
        loading.setVisibility(View.VISIBLE);
        String token = Util.getAccessToken(context);
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

                    loading.setVisibility(View.GONE);

                }else
                    Util.whenNotSuccessful(response, context, "GET PATIENT:");
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Log.d("ERROR", "ERROR-GET-PATIENT: " + t.getMessage());
            }
        });
    }

    private void updatePatient(Patient patient, Address address) {
        loading.setVisibility(View.VISIBLE);

        String token = Util.getAccessToken(context);
        EditPatientBody body = new EditPatientBody(patient, address);
        Call<Patient> updatePatientCall = service.updatePatient(body, token);

        updatePatientCall.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                if(response.isSuccessful()){
                    Util.showToast(context, "Dados atualizados", null);
                    loading.setVisibility(View.GONE);
                }else
                    Util.whenNotSuccessful(response, context, "UPDATE PATIENT:");
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Log.d("ERROR", "ERROR-UPDATE-PATIENT: " + t.getMessage());
            }
        });
    }
}
