package com.cuidar.app_cer.model.activity;

import com.cuidar.app_cer.model.category.Category;

import java.io.Serializable;

public class Activity implements Serializable {

    private int id;
    private int categoryId;
    private String name;
    private String description;
    private String pageDescription;
    private String icon;
    private Category category;

    public Activity(int id, int categoryId, String name, String description, String pageDescription, String icon) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.pageDescription = pageDescription;
        this.icon = icon;
    }

    public Activity(int id, int categoryId, String name, String description, String pageDescription, String icon, Category category) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.pageDescription = pageDescription;
        this.icon = icon;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
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

    public Category getCategory() {
        return category;
    }
}
