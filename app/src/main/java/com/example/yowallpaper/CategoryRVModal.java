package com.example.yowallpaper;

public class CategoryRVModal {
    private  String category;
    private  String categoryIVUrl;

    public CategoryRVModal(String category, String categoryIVUrl) {
        this.category = category;
        this.categoryIVUrl = categoryIVUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryIVUrl() {
        return categoryIVUrl;
    }

    public void setCategoryIVUrl(String categoryIVUrl) {
        this.categoryIVUrl = categoryIVUrl;
    }
}
