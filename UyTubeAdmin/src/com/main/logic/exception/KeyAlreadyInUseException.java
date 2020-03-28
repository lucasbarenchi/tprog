package com.main.logic.exception;

public class KeyAlreadyInUseException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public KeyAlreadyInUseException(String message) {
        super(message);
    }
}
