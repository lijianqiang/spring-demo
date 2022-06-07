package com.bytehonor.server.demo.spring.service;

public interface RedisCacheService {

    public String get(String key);
    
    public void set(String key, String value);
}
