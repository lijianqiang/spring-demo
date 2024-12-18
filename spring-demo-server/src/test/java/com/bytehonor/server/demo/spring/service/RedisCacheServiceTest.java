package com.bytehonor.server.demo.spring.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bytehonor.sdk.starter.redis.service.RedisCacheService;

@SpringBootTest
public class RedisCacheServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(RedisCacheServiceTest.class);

    @Autowired
    private RedisCacheService redisCacheService;

    @Test
    public void test() {
        String key = "name";
        String value = "Elon Musk";
        redisCacheService.kvSet(key, value);

        String val = redisCacheService.kvGet(key);

        LOG.info("test val:{}", val);

        Assertions.assertTrue(value.equals(val), "test");
    }

    @Test
    public void testIncrement() {
        String key = "testIncrement";
        redisCacheService.delete(key);
        int size = 10;
        for (int i = 0; i < size; i++) {
            redisCacheService.increment(key);
        }
        int val = redisCacheService.getInteger(key);

        LOG.info("testIncrement val:{}", val);
        redisCacheService.delete(key);
        Assertions.assertTrue(val == size, "test");
    }

    @Test
    public void testIncrement2() {
        String key = "testIncrement2";
        redisCacheService.delete(key);
        int size = 10;
        long val = 0L;
        for (int i = 0; i < size; i++) {
            val = redisCacheService.increment(key);
            LOG.info("testIncrement2 i:{}, val:{}", i, val);
        }

        redisCacheService.delete(key);
        Assertions.assertTrue(val == size, "testIncrement2");
    }

    @Test
    public void testDecrement() {
        String key = "testDecrement";
        int size = 10;
        redisCacheService.putInteger(key, size);
        long val = 0L;
        for (int i = 0; i < size; i++) {
            val = redisCacheService.decrement(key);
            LOG.info("testDecrement i:{}, val:{}", i, val);
        }

        LOG.info("testDecrement val:{}", redisCacheService.getInteger(key));
        Assertions.assertTrue(val == 0, "testDecrement");
    }

    @Test
    public void testDecrement2() {
        String key = "testDecrement2";
        redisCacheService.delete(key);
        long val = redisCacheService.decrement(key);

        LOG.info("testDecrement2 val:{}", redisCacheService.getInteger(key));
        Assertions.assertTrue(val == -1L, "testDecrement2");
    }

    @Test
    public void testLongSet() {
        String key = "testLongSet";
        redisCacheService.delete(key);
        int size = 10;
        for (int i = 0; i < size; i++) {
            redisCacheService.setLongAdd(key, 1L * i);
        }
        Set<Long> set = redisCacheService.setLongMemebers(key);
        for (Long lo : set) {
            LOG.info("testLongSet long:{}", lo);
        }

        redisCacheService.delete(key);
        Assertions.assertTrue(set.size() == size, "testLongSet");
    }

    @Test
    public void testLongSet2() {
        String key = "testLongSet2";
        redisCacheService.delete(key);
        int size = 10;
        Set<Long> set1 = new HashSet<Long>();
        for (int i = 0; i < size; i++) {
            set1.add(1L * i);
        }
        redisCacheService.setLongAdds(key, set1);

        Set<Long> set2 = redisCacheService.setLongMemebers(key);
        LOG.info("testLongSet2 set2:{}", set2.size());
        for (Long lo : set2) {
            LOG.info("testLongSet2 long:{}", lo);
        }

        redisCacheService.delete(key);
        Assertions.assertTrue(set2.size() == size, "testLongSet2");
    }

    @Test
    public void testStringSet() {
        String key = "testStringSet";
        redisCacheService.delete(key);
        int size = 10;
        Set<String> set1 = new HashSet<String>();
        for (int i = 0; i < size; i++) {
            set1.add("string" + i);
        }
        redisCacheService.setAdds(key, set1);

        Set<String> set2 = redisCacheService.setMemebers(key);
        LOG.info("testStringSet set2:{}", set2.size());
        for (String lo : set2) {
            LOG.info("testStringSet long:{}", lo);
        }

        redisCacheService.delete(key);
        Assertions.assertTrue(set2.size() == size, "testStringSet");
    }
}
