package com.main.server.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.main.logic.dts.CategoryDT;
import com.main.logic.exception.EntityNotFoundException;

@WebService
@SOAPBinding(style = Style.RPC)
public interface CategoryWebService {

    @WebMethod
    public CategoryDT[] listCategories();

    CategoryDT getCategory(String name) throws EntityNotFoundException;
}
