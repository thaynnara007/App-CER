package com.cuidar.app_cer.model;

import java.util.HashMap;

public class StatusCard {

    private String name;
    private int image;
    private HashMap<String, Integer> status;

    public StatusCard() {

    }

    public StatusCard(String name, int image, HashMap<String, Integer> status) {
        this.name = name;
        this.image = image;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public HashMap<String, Integer> getStatus() {
        return status;
    }

    public void setStatus(HashMap<String, Integer> status) {
        this.status = status;
    }
}
