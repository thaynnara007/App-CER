package com.cuidar.app_cer.model;

public class ForgetPasswordBody {

    private String email;

    public ForgetPasswordBody(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
