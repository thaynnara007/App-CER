package com.cuidar.app_cer.model;

import java.io.Serializable;

public class Step implements Serializable {

    private String name;
    private String description;
    private String congrats = "";
    private int image = 0;

    public Step(String name, String description, String congrats,int image) {
        this.name = name;
        this.description = description;
        this.congrats = congrats;
        this.image = image;
    }

    public Step(String name, String description, String congrats) {
        this.name = name;
        this.description = description;
        this.congrats = congrats;
    }

    public Step(String name, String description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Step(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCongrats() {
        return congrats;
    }

    public void setCongrats(String congrats) {
        this.congrats = congrats;
    }
}
