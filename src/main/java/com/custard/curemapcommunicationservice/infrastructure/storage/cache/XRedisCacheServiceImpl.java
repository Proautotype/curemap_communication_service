package com.custard.curemapcommunicationservice.infrastructure.storage.cache;

import com.custard.curemapcommunicationservice.domain.ports.XRedisCacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class XRedisCacheServiceImpl implements XRedisCacheService {
    private final Logger logger = LoggerFactory.getLogger(XRedisCacheServiceImpl.class.getName());
    private final RedisTemplate<String, String> redisTemplate;
    private final ValueOperations<String, String> valueOperations;
    private final ObjectMapper objectMapper;


    public XRedisCacheServiceImpl(
            RedisTemplate<String, String> redisTemplate,
            ObjectMapper objectMapper
    ) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
        this.objectMapper = objectMapper;
    }


    @Override
    public void saveCache(String key, String value) {
        logger.info("caching {} | {} ", key, value);
        valueOperations.set(key, value);
    }

    @Override
    public void saveCache(String key, Object value) {
        logger.info("caching object {} | {} ", key, value);
        try {
            String valueString = objectMapper.writeValueAsString(value);
            this.saveCache(key, valueString);
        } catch (JsonProcessingException e) {
            logger.error(e.getOriginalMessage(), e);
        }
    }

    @Override
    public void saveCacheWithTTL(String key, String value, Long ttl) {
        valueOperations.set(key, value, ttl, TimeUnit.SECONDS);
    }

    @Override
    public void saveCacheWithTTL(String key, Object value, Long ttl) throws RuntimeException {
        try {
            String valueString = objectMapper.writeValueAsString(value);
            this.saveCacheWithTTL(key, valueString, ttl);
        } catch (JsonProcessingException e) {
            logger.error(e.getOriginalMessage(), e);
        }
    }

    @Override
    public String getCache(String key) {
        logger.info("get cache by {} ", key);
        return valueOperations.get(key);
    }

    @Override
    public Boolean delCache(String key) {
        return redisTemplate.delete(key);
    }

}
