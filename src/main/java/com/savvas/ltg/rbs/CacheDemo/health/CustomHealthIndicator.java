/*
 * Copyright 2020, Savvas Learning Company LLC
 *
 * CustomHealthIndicator.java
 */
package com.savvas.ltg.rbs.CacheDemo.health;

import java.util.Optional;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

public class CustomHealthIndicator implements HealthIndicator {

    private static final String FAILURE_RATE = "failureRate";
    private static final String FAILURE_RATE_THRESHOLD = "failureRateThreshold";
    private static final String BUFFERED_CALLS = "bufferedCalls";
    private static final String FAILED_CALLS = "failedCalls";
    private static final String NOT_PERMITTED = "notPermittedCalls";
    private static final String MAX_BUFFERED_CALLS = "maxBufferedCalls";
    private static final String CIRCUIT_BREAKER = "circuitBreaker";

    private CircuitBreaker circuitBreaker;
    
    public CustomHealthIndicator(CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    @Override
    public Health health() {
        return Optional.of(circuitBreaker)
                .map(this::mapBackendMonitorState)
                .orElse(Health.up().build());
    }

    private Health mapBackendMonitorState(CircuitBreaker circuitBreaker) {
        switch (circuitBreaker.getState()) {
            case CLOSED:
                return addDetails(Health.up(), circuitBreaker).build();
            case OPEN:
                return addDetails(Health.down(), circuitBreaker).build();
            default:
                return addDetails(Health.unknown(), circuitBreaker).build();
        }
    }

    private Health.Builder addDetails(Health.Builder builder, CircuitBreaker circuitBreaker) {
        CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();
        CircuitBreakerConfig config = circuitBreaker.getCircuitBreakerConfig();
        builder.withDetail(FAILURE_RATE, metrics.getFailureRate() + "%")
            .withDetail(FAILURE_RATE_THRESHOLD, config.getFailureRateThreshold() + "%")
            .withDetail(MAX_BUFFERED_CALLS, metrics.getMaxNumberOfBufferedCalls())
            .withDetail(BUFFERED_CALLS, metrics.getNumberOfBufferedCalls())
            .withDetail(FAILED_CALLS, metrics.getNumberOfFailedCalls())
            .withDetail(NOT_PERMITTED, metrics.getNumberOfNotPermittedCalls())
            .withDetail(CIRCUIT_BREAKER, circuitBreaker.getName());
        return builder;
    }

}
