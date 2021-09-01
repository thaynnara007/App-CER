package com.cuidar.app_cer.model.auth;

import com.cuidar.app_cer.model.patient.Patient;

public class LoginResponse {

    private String token;
    private Patient patient;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
