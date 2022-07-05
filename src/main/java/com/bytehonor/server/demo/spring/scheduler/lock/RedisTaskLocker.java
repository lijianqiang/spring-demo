package com.bytehonor.server.demo.spring.scheduler.lock;

import com.bytehonor.sdk.define.spring.constant.TimeConstants;
import com.bytehonor.sdk.server.spring.ApplicationContextHolder;
import com.bytehonor.sdk.server.spring.scheduler.lock.TaskLocker;
import com.bytehonor.sdk.starter.redis.service.RedisCacheService;

public class RedisTaskLocker extends TaskLocker {

    private final RedisCacheService redisCacheService;

    public RedisTaskLocker() {
        this.redisCacheService = ApplicationContextHolder.getBean(RedisCacheService.class);
    }

    @Override
    public boolean lock(String key) {
        return redisCacheService.lock(key, TimeConstants.MINUTE * 2);
    }

}
