package com.bytehonor.server.demo.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytehonor.sdk.starter.redis.dao.RedisLettuceDao;
import com.bytehonor.server.demo.spring.service.RedisCacheService;

@Service("redisCacheService")
public class RedisCacheServiceImpl implements RedisCacheService {

    @Autowired
    private RedisLettuceDao redisLettuceDao;

    @Override
    public String get(String key) {
        return redisLettuceDao.kvGet(key);
    }

    @Override
    public void set(String key, String value) {
        redisLettuceDao.kvSet(key, value);
    }

}
