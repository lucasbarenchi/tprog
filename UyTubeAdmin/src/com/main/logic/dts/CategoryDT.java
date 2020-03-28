package com.main.logic.dts;

import java.io.Serializable;
import java.util.Objects;

public class CategoryDT implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;

    public CategoryDT() {}
    
    public CategoryDT(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CategoryDT) {
            return Objects.equals(name, ((CategoryDT) obj).getName());
        }

        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
