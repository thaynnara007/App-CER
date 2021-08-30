package com.cuidar.app_cer.model;

public class LoginResponse {

    private String token;
    private Patient ptient;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Patient getPtient() {
        return ptient;
    }

    public void setPtient(Patient ptient) {
        this.ptient = ptient;
    }
}
