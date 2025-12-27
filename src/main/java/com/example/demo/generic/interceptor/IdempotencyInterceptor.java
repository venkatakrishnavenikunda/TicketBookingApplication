package com.example.demo.generic.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

@Component
public class IdempotencyInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        String key = request.getHeader("Idempotency-Key");
        if (key == null) return true;

        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            throw new RuntimeException("Duplicate request");
        }
        redisTemplate.opsForValue().set(key, "LOCKED", 10, TimeUnit.MINUTES);
        return true;
    }
}
