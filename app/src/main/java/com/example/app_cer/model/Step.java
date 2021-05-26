package com.example.app_cer.model;

public class Step {

    private String name;
    private String description;
    private int image;

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
}
