package com.main.logic.collection;

import java.util.HashMap;
import java.util.Map;

import com.main.logic.domain.Category;
import com.main.logic.exception.EntityNotFoundException;

public final class CategoryCollection {

    private Map<String, Category> categories = new HashMap<>();

    private static CategoryCollection instance;

    public static CategoryCollection getInstance() {
        if (instance == null) {
            instance = new CategoryCollection();
        }
        return instance;
    }

    private CategoryCollection() {
    }

    public Map<String, Category> getAllCategories() {
        return categories;
    }
    public Category getCategory(String categoryName) throws EntityNotFoundException {
        if (categories.get(categoryName) == null) {
            throw new EntityNotFoundException("Categoría no encontrada");
        }
        return categories.get(categoryName);
    }

    public void addCategory(Category category) {
        categories.put(category.getName(), category);
    }

    public Category removeCategory(Category category) {
        return categories.remove(category.getName());
    }

    public boolean isEmpty() {
        return categories.isEmpty();
    }

    public void deleteAll() {
        categories = new HashMap<>();
    }
}
