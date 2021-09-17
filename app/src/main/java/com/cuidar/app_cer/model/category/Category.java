package com.cuidar.app_cer.model.category;

public class Category {

    private int id;
    private String name;
    private String description;
    private String pageDescription;
    private String icon;
    private String color;
    private String textColor;

    public Category(int id, String name, String description, String pageDescription, String icon, String color, String textColor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pageDescription = pageDescription;
        this.icon = icon;
        this.color = color;
        this.textColor = textColor;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPageDescription() {
        return pageDescription;
    }

    public String getIcon() {
        return icon;
    }

    public String getColor() {
        return color;
    }

    public String getTextColor() {
        return textColor;
    }
}
