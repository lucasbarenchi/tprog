package com.main.logic.controller;

import com.main.logic.interfaces.ICategoryController;
import com.main.logic.interfaces.IPlaylistController;
import com.main.logic.interfaces.IUserController;
import com.main.logic.interfaces.IVideoController;

public class ControllerFactory {

    private static ControllerFactory instance = null;

    public static ControllerFactory getInstance() {
        if (instance == null) {
            instance = new ControllerFactory();
        }

        return instance;
    }

    private ControllerFactory() {}

    public ICategoryController getCategoryController() {
        return CategoryController.getInstance();
    }

    public IPlaylistController getPlaylistController() {
        return PlaylistController.getInstance();
    }

    public IUserController getUserController() {
        return UserController.getInstance();
    }

    public IVideoController getVideoController() {
        return VideoController.getInstance();
    }

}
