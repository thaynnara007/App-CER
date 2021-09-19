package com.cuidar.app_cer.model.image;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Image implements Serializable {
    private int id;
    private int stepId;
    private String pictureUrl;
    private String imageName;
    private String token;

    public Image(int id, int stepId, String pictureUrl, String imageName, String token) {
        this.id = id;
        this.stepId = stepId;
        this.pictureUrl = pictureUrl;
        this.imageName = imageName;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public int getStepId() {
        return stepId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public String getToken() {
        return token;
    }

    @NonNull
    @Override
    public String toString() {
        return "url: " + this.pictureUrl;
    }
}
