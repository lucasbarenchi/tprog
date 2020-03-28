package com.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.main.logic.collection.CategoryCollection;
import com.main.logic.controller.ControllerFactory;
import com.main.logic.domain.Category;
import com.main.logic.dts.CategoryDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.interfaces.ICategoryController;

public class CategoryControllerTest {

    private static CategoryCollection categoryCollection;
    private static ICategoryController categoryController;


    @Before
    public void init() {
        categoryCollection = CategoryCollection.getInstance();
        categoryController = ControllerFactory.getInstance().getCategoryController();
    }

    @Test
    public void testCreateCategory() throws EntityNotFoundException {
        categoryCollection.deleteAll();
        boolean created = categoryController.createCategory("categoriaTEST");
        assertTrue(created);

        boolean categorySameName = categoryController.createCategory("categoriaTEST");
        assertFalse(categorySameName);

        Category category = categoryCollection.getCategory("categoriaTEST");
        assertNotNull(category);
        assertEquals(category.getName(), "categoriaTEST");

    }

    @Test
    public void testlistCategories() {
        categoryCollection.deleteAll();
        assertTrue(categoryCollection.isEmpty());
        categoryController.createCategory("categoriaTEST1");
        categoryController.createCategory("categoriaTEST2");

        List<CategoryDT> categorias = categoryController.listCategories();
        assertEquals(2, categorias.size());
        assertEquals("categoriaTEST2", categorias.get(0).getName());
        assertEquals("categoriaTEST1", categorias.get(1).getName());
    }

    @Test
    public void testGetCategory() throws EntityNotFoundException {
        categoryCollection.deleteAll();
        categoryController.createCategory("categoriaTEST");
        CategoryDT category = categoryController.getCategory("categoriaTEST");
        assertEquals(category.getName(), "categoriaTEST");
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetThrows() throws EntityNotFoundException{
        categoryCollection.deleteAll();
        categoryController.getCategory("categoriaTEST");
    }

}
