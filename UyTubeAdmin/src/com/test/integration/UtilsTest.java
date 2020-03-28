package com.test.integration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.main.logic.utils.DateUtils;

public class UtilsTest {


    @Test
    public void testDateUtils() {
        assertTrue(DateUtils.isLeapYear(2016));
        assertFalse(DateUtils.isLeapYear(2017));
    }

}
