package com.cuidar.app_cer.model.activity;

import com.cuidar.app_cer.model.category.Category;

public class ActivitiesPaginated {
    private int count;
    private int pages;
    private Category category;
    private Activity[] rows;

    public ActivitiesPaginated(int count, int pages, Category category, Activity[] rows) {
        this.count = count;
        this.pages = pages;
        this.category = category;
        this.rows = rows;
    }

    public int getCount() {
        return count;
    }

    public int getPages() {
        return pages;
    }

    public Category getCategory() {
        return category;
    }

    public Activity[] getRows() {
        return rows;
    }
}
