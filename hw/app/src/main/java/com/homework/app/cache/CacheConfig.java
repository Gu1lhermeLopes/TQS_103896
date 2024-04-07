package com.homework.app.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;
import com.github.benmanes.caffeine.cache.Caffeine; // Import the missing Caffeine class


@Configuration
@EnableCaching
public class CacheConfig {
    private static final int DEFAULT_CACHE_TTL_SECONDS = 3600;


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CaffeineCacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(List.of("exchangeRatesCache"));
        cacheManager.setCaffeine(exchangeRatesCacheBuilder());
        return cacheManager;
    }

    @Bean
    public Caffeine<Object, Object> exchangeRatesCacheBuilder() {
        return Caffeine.newBuilder()
                .expireAfterWrite(DEFAULT_CACHE_TTL_SECONDS, TimeUnit.SECONDS)
                .maximumSize(DEFAULT_CACHE_TTL_SECONDS)
                .recordStats();
    }
}