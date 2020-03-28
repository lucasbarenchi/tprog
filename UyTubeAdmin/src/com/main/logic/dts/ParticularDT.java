package com.main.logic.dts;

import java.util.List;

public class ParticularDT extends PlaylistDT {

    public ParticularDT(String name, boolean isPrivate) {
        super();
        this.setName(name);
        this.setPrivate(isPrivate);
    }

    public ParticularDT(String name, boolean isPrivate, List<String> categoriesList) {
        super();
        this.setName(name);
        this.setPrivate(isPrivate);
        this.setCategories(categoriesList);
    }
}
