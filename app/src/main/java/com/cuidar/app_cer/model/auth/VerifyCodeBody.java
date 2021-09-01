package com.cuidar.app_cer.model.auth;

public class VerifyCodeBody {

    private String email;
    private String code;

    public VerifyCodeBody(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
