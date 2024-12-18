package com.bytehonor.server.demo.spring.model;

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.server.demo.spring.TestWrapper;

public class UserProfileTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserProfileTest.class);

    @Test
    public void test() {
        UserProfile profile = null;
        int size = 10000000;
        long start = System.currentTimeMillis();
        LOG.info("begin");

        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            profile = new UserProfile();
        }
        long costNew = System.currentTimeMillis() - start;
        LOG.info("new cost:{}", costNew);

        Supplier<UserProfile> supplier = UserProfile::new;
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            profile = supplier.get();
        }
        long costGet = System.currentTimeMillis() - start;
        LOG.info("get cost:{}", costGet);

        try {
            Class<?> clz = Class.forName(UserProfile.class.getName());
            start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                profile = (UserProfile) clz.getDeclaredConstructor().newInstance();
            }
            long costClz = System.currentTimeMillis() - start;
            LOG.info("clz cost:{}", costClz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 10000000, new:99ms,get:134ms,clz:220ms

        TestWrapper.assertTrue("*testExpire*", profile != null);
    }

}
