package com.bytehonor.server.demo.spring.model;

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserProfileTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserProfileTest.class);

    @Test
    public void test() {
        int size = 10000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            new UserProfile();
        }
        LOG.info("new cost:{}", System.currentTimeMillis() - start);

        Supplier<UserProfile> supplier = UserProfile::new;
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            supplier.get();
        }
        LOG.info("get cost:{}", System.currentTimeMillis() - start);

        // 10000000, new:99ms,get:134ms
    }

}
