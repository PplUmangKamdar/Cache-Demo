/*
 * Copyright 2020, Savvas Learning Company LLC
 *
 * ServiceHandler.java
 */
package com.savvas.ltg.rbs.CacheDemo.handlers;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.savvas.ltg.rbs.CacheDemo.constants.ServiceConstants;
import com.savvas.ltg.rbs.CacheDemo.services.CachedemoService;
import com.savvas.ltg.rbs.template.handlers.TemplateHandler;
import com.savvas.ltg.rbs.template.utilities.TelemetryUtil;
import com.pearson.logging.domain.TelemetryLog;

import reactor.core.publisher.Mono;

import java.security.InvalidParameterException;

@Component
public class ServiceHandler extends TemplateHandler implements ServiceConstants {
    
    private static final Logger LOGGER_TELEMETRY = LoggerFactory.getLogger("LOGGER_TELEMETRY");

    @Autowired
    protected CachedemoService templateService;
    
     /**
     * Your Service-Specific Handlers Go Here.
     * 
     * NOTE: You wil need getHandlerResponse - make sure to change the name + pathVariable to whatever you need it to be.
     * 
     * Add any additional necessary handlers.
     * 
     * **/

    public Mono<ServerResponse> insertCache(final ServerRequest request) {
        TelemetryLog telemetryLog = TelemetryUtil.buildTelemetry(request);
        LOGGER_TELEMETRY.info("Post Handler Response API : {}", telemetryLog);
        return templateService.insertCache(request.pathVariable("param")).transform(this::serverResponse);
    }

    public Mono<ServerResponse> getCache(final ServerRequest request) {
        TelemetryLog telemetryLog = TelemetryUtil.buildTelemetry(request);
        LOGGER_TELEMETRY.info("Get Handler Response API : {}", telemetryLog);
        return templateService.getCache(request.pathVariable("param")).transform(this::serverResponse);
    }

    public Mono<ServerResponse> insertCacheForInMemoryRedis(final ServerRequest request) {
        TelemetryLog telemetryLog = TelemetryUtil.buildTelemetry(request);
        LOGGER_TELEMETRY.info("Post Handler Response API : {}", telemetryLog);
        return templateService.insertCacheForInMemoryRedis(request.pathVariable("param")).transform(this::serverResponse);
    }

    public Mono<ServerResponse> getCacheForInMemoryRedis(final ServerRequest request) {
        TelemetryLog telemetryLog = TelemetryUtil.buildTelemetry(request);
        LOGGER_TELEMETRY.info("Get Handler Response API : {}", telemetryLog);
        return templateService.getCacheForInMemoryRedis(request.pathVariable("param")).transform(this::serverResponse);
    }

    public Mono<ServerResponse> getSummary(final ServerRequest request) {
        TelemetryLog telemetryLog = TelemetryUtil.buildTelemetry(request);
        LOGGER_TELEMETRY.info("Get Handler Response API : {}", telemetryLog);
        String assessmentSessionId = request.pathVariable("assessmentSessionId");

        return request.bodyToMono(String.class)
                .switchIfEmpty(Mono.error(new InvalidParameterException("Request body is missing")))
                .flatMap(jsonData -> templateService.getSummary(assessmentSessionId, jsonData)).transform(this::serverResponse);
    }

    public Mono<ServerResponse> getSummaryFromInMemoryCache(final ServerRequest request) {
        TelemetryLog telemetryLog = TelemetryUtil.buildTelemetry(request);
        LOGGER_TELEMETRY.info("Get Handler Response API : {}", telemetryLog);
        String assessmentSessionId = request.pathVariable("assessmentSessionId");

        return request.bodyToMono(String.class)
                .switchIfEmpty(Mono.error(new InvalidParameterException("Request body is missing")))
                .flatMap(jsonData -> templateService.getSummaryFromInMemoryRedis(assessmentSessionId, jsonData)).transform(this::serverResponse);
    }

    public Mono<ServerResponse> getCircuitBreakerHealthStats(final ServerRequest request) {
        TelemetryLog telemetryLog = TelemetryUtil.buildTelemetry(request);
        LOGGER_TELEMETRY.info("Get Circuit Breaker Health Stats API : {}", telemetryLog);
        return templateService.getCircuitBreakerHealthStats();
    }

}
