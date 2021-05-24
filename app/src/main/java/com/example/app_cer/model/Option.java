package com.example.app_cer.model;

import android.view.View;

public class Option {

    private String name, description;
    private int icon;
    private View.OnClickListener onClick;

    public Option(String name, String description, int icon) {
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    public Option(String name, String description, int icon, View.OnClickListener onClick) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.onClick = onClick;
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

    public View.OnClickListener getOnClick() {
        return onClick;
    }

    public void setOnClick(View.OnClickListener onClick) {
        this.onClick = onClick;
    }
}
