package com.test.integration;

import org.junit.Assert;
import org.junit.Test;

import com.main.logic.exception.DefaultPlaylistException;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.exception.KeyAlreadyInUseException;
import com.main.logic.exception.PrivacyException;

public class ExceptionTests {

    @Test
    public void testCreateEntityNotFoundException() {
        EntityNotFoundException exception = new EntityNotFoundException("Entidad no encontrada.");
        Assert.assertEquals("Entidad no encontrada.", exception.getMessage());
    }

    @Test
    public void testCreateKeyAlreadyInUseException() {
        KeyAlreadyInUseException exception = new KeyAlreadyInUseException("Clave en uso.");
        Assert.assertEquals("Clave en uso.", exception.getMessage());
    }
    @Test
    public void testCreatePrivacyException() {
        PrivacyException exception = new PrivacyException("Privacidad");
        Assert.assertEquals("Privacidad", exception.getMessage());
    }
    @Test
    public void testDefaultPlaylistException() {
        DefaultPlaylistException exception = new DefaultPlaylistException("Default");
        Assert.assertEquals("Default", exception.getMessage());
    }
}
