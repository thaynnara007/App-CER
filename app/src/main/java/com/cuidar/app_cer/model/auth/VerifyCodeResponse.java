package com.cuidar.app_cer.model.auth;

public class VerifyCodeResponse {

    private String token;

    public VerifyCodeResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
