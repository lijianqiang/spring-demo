package com.bytehonor.server.demo.spring;

import org.junit.jupiter.api.Assertions;

public class TestWrapper {

    static  public void assertTrue(String message, boolean condition) {
        Assertions.assertTrue(condition, message);
    }
}
