package com.apress.book.ldap.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringUtilsTest {
    @Test
    public void shouldReturnTrueWithNull() {
        assertTrue(StringUtils.isEmpty(null));
    }

    @Test
    public void shouldReturnTrueWithEmpty() {
        assertTrue(StringUtils.isEmpty(""));
    }

    @Test
    public void shouldReturnFalseWithText() {
        assertFalse(StringUtils.isEmpty("Practical Spring Ldap"));
    }
}
