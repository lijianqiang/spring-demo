package com.bytehonor.server.demo.spring.scheduler.handler;

import com.bytehonor.sdk.define.spring.constant.TimeConstants;
import com.bytehonor.sdk.server.spring.SpringServer;
import com.bytehonor.sdk.server.spring.scheduler.handler.TaskHandler;
import com.bytehonor.sdk.starter.redis.service.RedisCacheService;

public class RedisTaskHandler extends TaskHandler {

    private static final long LOCK_MILLIS = TimeConstants.MINUTE * 2;

    private final RedisCacheService redisCacheService;

    public RedisTaskHandler() {
        this.redisCacheService = SpringServer.getBean(RedisCacheService.class);
    }

    @Override
    public boolean lock(String key) {
        return redisCacheService.lock(key, LOCK_MILLIS);
    }

    @Override
    public void pause(String name) {
        redisCacheService.lock(name, TimeConstants.MONTH * 6);
    }

    @Override
    public void play(String name) {
        redisCacheService.delete(name);
    }

    @Override
    public boolean isPause(String name) {
        return redisCacheService.kvGet(name) != null;
    }
}
