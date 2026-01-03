package com.example.sms_sender.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class BlockService {

    private final StringRedisTemplate redisTemplate;

    public BlockService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isBlocked(String userId) {
        String key = "blocked:" + userId;
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
