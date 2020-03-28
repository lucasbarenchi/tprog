package com.main.logic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.main.logic.collection.CategoryCollection;
import com.main.logic.domain.Category;
import com.main.logic.dts.CategoryDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.interfaces.ICategoryController;

public class CategoryController implements ICategoryController {

    private static CategoryController instance;

    public static CategoryController getInstance() {
        if (instance == null) {
            instance = new CategoryController();
        }

        return instance;
    }

    @Override
    public boolean createCategory(String name) {
        CategoryCollection categoryCollection = CategoryCollection.getInstance();
        Category category;
        try{
            category = categoryCollection.getCategory(name);
            return false;
        } catch (EntityNotFoundException e) {
            category = new Category(name);
            categoryCollection.addCategory(category);
            return true;
        }
    }

    @Override
    public List<CategoryDT> listCategories() {
        CategoryCollection categoryCollection = CategoryCollection.getInstance();
        Map<String, Category> categories = categoryCollection.getAllCategories();
        List<CategoryDT> categoriesDts = new ArrayList<>();
        categories.forEach((key, value) -> categoriesDts.add(value.getDT()));
        return categoriesDts;
    }

    @Override
    public CategoryDT getCategory(String name) throws EntityNotFoundException{
        CategoryCollection categoryCollection = CategoryCollection.getInstance();
        try {
            Category category = categoryCollection.getCategory(name);
            return category.getDT();
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }
}
