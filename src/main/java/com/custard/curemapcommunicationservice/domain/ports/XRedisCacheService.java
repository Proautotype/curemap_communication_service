package com.custard.curemapcommunicationservice.domain.ports;

public interface XRedisCacheService {
    void saveCache(String key, String value);
    void saveCache(String key, Object value);
    void saveCacheWithTTL(String key, String value, Long ttl);
    void saveCacheWithTTL(String key, Object value, Long ttl);
    String getCache(String key);
    Boolean delCache(String key);
}
