/*
 * Copyright 2020, Savvas Learning Company LLC
 *
 * CachedemoServiceTest.java
 */
package com.savvas.ltg.rbs.CacheDemo.services;

import org.junit.Before;

import reactor.core.publisher.Mono;

public class CachedemoServiceTest {

    CachedemoService templateService;

    Mono<String> response = Mono.just("sample response");
    String CacheDemoId = "3X6CVYD5Z28";

    @Before
    public void init() {
        templateService = new CachedemoService();
    }


}
