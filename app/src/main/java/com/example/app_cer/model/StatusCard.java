package com.example.app_cer.model;

import java.util.ArrayList;

public class StatusCard {

    private String name;
    private int image;
    private ArrayList<Status> status;

    public StatusCard() {

    }

    public StatusCard(String name, int image, ArrayList<Status> status) {
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

    public ArrayList<Status> getStatus() {
        return status;
    }

    public void setStatus(ArrayList<Status> status) {
        this.status = status;
    }
}
