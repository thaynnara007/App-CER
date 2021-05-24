package com.example.app_cer.model;

import android.widget.Button;

public class Option {

    private String name, description;
    private int icon;
    private Button startButton;

    public Option(String name, String description, int icon, Button startButton) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.startButton = startButton;
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Button getStartButton() {
        return startButton;
    }

    public void setStartButton(Button startButton) {
        this.startButton = startButton;
    }
}
