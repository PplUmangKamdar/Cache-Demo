/*
 * Copyright 2020, Savvas Learning Company LLC
 *
 * ServiceRoutingConfiguration.java
 */
package com.savvas.ltg.rbs.CacheDemo.config.routing;

import com.savvas.ltg.rbs.template.config.routing.TemplateRoutingConfiguration;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.beans.factory.annotation.Autowired;
import com.savvas.ltg.rbs.CacheDemo.handlers.ServiceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class ServiceRoutingConfiguration extends TemplateRoutingConfiguration{
    
    @Autowired
    ServiceHandler serviceHandler;

    /*Your Service-Specific Routes Go Here. */
    @Bean
     public RouterFunction<ServerResponse> CacheDemoRoutes() {
        return route (POST("/CacheDemo-service/v1/insert/{param}"), serviceHandler :: insertCache)
                .andRoute(GET("/CacheDemo-service/v1/get/{param}"), serviceHandler :: getCache)
                .andRoute(POST("/CacheDemo-service/v2/insert/{param}"), serviceHandler :: insertCacheForInMemoryRedis)
                .andRoute(GET("/CacheDemo-service/v2/get/{param}"), serviceHandler :: getCacheForInMemoryRedis)
                .andRoute(POST("/CacheDemo-service/v1/sessions/{assessmentSessionId}/summary"), serviceHandler :: getSummary)
                .andRoute(POST("/CacheDemo-service/v2/sessions/{assessmentSessionId}/summary"), serviceHandler :: getSummaryFromInMemoryCache);
    }

}    
