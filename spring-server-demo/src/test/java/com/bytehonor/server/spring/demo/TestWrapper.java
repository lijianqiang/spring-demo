package com.bytehonor.server.spring.demo;

import org.junit.jupiter.api.Assertions;

public class TestWrapper {

    static  public void assertTrue(String message, boolean condition) {
        Assertions.assertTrue(condition, message);
    }
}
