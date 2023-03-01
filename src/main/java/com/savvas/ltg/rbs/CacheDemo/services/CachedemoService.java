/*
 * Copyright 2020, Savvas Learning Company LLC
 *
 * CachedemoService.java
 */
package com.savvas.ltg.rbs.CacheDemo.services;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import com.savvas.ltg.rbs.CacheDemo.health.CustomHealthIndicator;

import org.springframework.http.MediaType;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;

import java.util.UUID;

@Service
public class CachedemoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CachedemoService.class);

    @Value("${redis.expiry.assessment.info}")
    String assessmentInfoCacheExpiry;

    @Autowired
    CircuitBreaker circuitBreaker;

    @Autowired
    RedisCacheService redisCacheService;

    @Autowired
    RedisCacheForInMemoryService redisCacheForInMemoryService;

    /**
     * 
     * @param param
     * @return
     */
    public Mono<String> insertCache(String param) {
        String uuid = UUID.randomUUID().toString();
        redisCacheService.setValue("DEMO_CACHE", uuid,
                param, NumberUtils.toLong(assessmentInfoCacheExpiry));

        return Mono.just("Inserted");
    }

    public Mono<String> getCache(String param) {
        LOGGER.info("in TemplateService service for getting the response for param {}", param);

        Object val = redisCacheService.getValue("DEMO_CACHE",
                param);
        if(val == null) {
            return Mono.just("No value found");
        }
        return Mono.just(val.toString());

    }

    public Mono<String> insertCacheForInMemoryRedis(String param) {
        String uuid = UUID.randomUUID().toString();
        redisCacheForInMemoryService.setValue("DEMO_CACHE", uuid,
                param, NumberUtils.toLong(assessmentInfoCacheExpiry));

        return Mono.just("Inserted");
    }

    public Mono<String> getCacheForInMemoryRedis(String param) {
        LOGGER.info("in TemplateService service for getting the response for param {} from in memory cache", param);

        Object val = redisCacheForInMemoryService.getValue("DEMO_CACHE",
                param);
        if(val == null) {
            return Mono.just("No value found");
        }
        return Mono.just(val.toString());

    }

    public Mono<String> getSummary(String param, String jsonData) {
        LOGGER.info("in TemplateService service for getting the response for param {} data {}", param, jsonData);

        Object val = redisCacheService.getValue("ASSESSMENT_SESSION_ID",
                param);
        if (val == null) {
            redisCacheService.setValue("ASSESSMENT_SESSION_ID", param,
                    jsonData, NumberUtils.toLong(assessmentInfoCacheExpiry));
            return Mono.just("Inserted");
        }
        return Mono.just(val.toString());
    }


    public Mono<String> getSummaryFromInMemoryRedis(String param, String jsonData) {
        LOGGER.info("in TemplateService service for getting the response for param {}, data {}", param, jsonData);

        Object val = redisCacheForInMemoryService.getValue("ASSESSMENT_SESSION_ID",
                param);
        if (val == null) {
            redisCacheForInMemoryService.setValue("ASSESSMENT_SESSION_ID", param,
                    jsonData, NumberUtils.toLong(assessmentInfoCacheExpiry));
            return Mono.just("Inserted");
        }
        return Mono.just(val.toString());
    }

    public Mono<ServerResponse> getCircuitBreakerHealthStats() {
        HealthIndicator healthIndicator = new CustomHealthIndicator(circuitBreaker);
        Health health = healthIndicator.health();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8).body(Mono.just(health), Health.class);
    }

}
