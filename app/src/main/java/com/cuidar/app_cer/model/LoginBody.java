package com.cuidar.app_cer.model;

public class LoginBody {

    private String email;
    private String password;

    public LoginBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
