package com.bytehonor.server.spring.demo.dao;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bytehonor.sdk.starter.redis.dao.RedisLettuceDao;
import com.bytehonor.server.spring.demo.TestWrapper;

@SpringBootTest
public class RedisLettuceDaoTest {

    @Autowired
    private RedisLettuceDao redisLettuceDao;

    @Test
    public void testExpire() {
        String key = "key_expire";
        redisLettuceDao.kvSet(key, key);
        redisLettuceDao.expire(key, 60L, TimeUnit.SECONDS);
        TestWrapper.assertTrue("*testExpire*", true);
    }

    @Test
    public void testExpireAt() {
        String key = "key_expireat";
        redisLettuceDao.kvSet(key, key);
        redisLettuceDao.expireAt(key, 1568434342000L);
        TestWrapper.assertTrue("*testExpire*", true);
    }

    @Test
    public void testKvSetIfAbsent() {
        String key = "key_expireabs";
        redisLettuceDao.kvSetIfAbsent(key, key, 1000L * 60);
        TestWrapper.assertTrue("*testExpire*", true);
    }

}
