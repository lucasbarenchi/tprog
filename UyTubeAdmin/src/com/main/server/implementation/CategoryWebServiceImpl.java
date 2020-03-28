package com.main.server.implementation;

import javax.jws.WebService;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.CategoryDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.interfaces.ICategoryController;
import com.main.server.service.CategoryWebService;

@WebService(endpointInterface = "com.main.server.service.CategoryWebService")
public class CategoryWebServiceImpl implements CategoryWebService{

    private ICategoryController categoryController = ControllerFactory.getInstance().getCategoryController();
    
    @Override
    public CategoryDT[] listCategories() {
        return categoryController.listCategories().stream().toArray(size -> new CategoryDT[size]);
    };
 
    @Override
    public CategoryDT getCategory(String name) throws EntityNotFoundException{
        return categoryController.getCategory(name);
    }
    
}
