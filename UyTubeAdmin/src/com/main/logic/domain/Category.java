package com.main.logic.domain;

import java.util.Objects;

import com.main.logic.dts.CategoryDT;

public class Category {

    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDT getDT() {
        return new CategoryDT(name);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Category) {
            return Objects.equals(name, ((Category) object).getName());
        }

        return false;
    }

}
