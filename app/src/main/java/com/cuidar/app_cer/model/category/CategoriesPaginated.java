package com.cuidar.app_cer.model.category;

public class CategoriesPaginated {

    private int count;
    private Category[] rows;
    private int pages;

    public CategoriesPaginated(int count, Category[] rows, int pages) {
        this.count = count;
        this.rows = rows;
        this.pages = pages;
    }

    public int getCount() {
        return count;
    }

    public Category[] getRows() {
        return rows;
    }

    public int getPages() {
        return pages;
    }
}
