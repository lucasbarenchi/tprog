package com.main.logic.interfaces;

import java.util.List;

import com.main.logic.dts.CategoryDT;
import com.main.logic.exception.EntityNotFoundException;

public interface ICategoryController {
    boolean createCategory(String name);
    List<CategoryDT> listCategories();
    CategoryDT getCategory(String name) throws EntityNotFoundException;
}
