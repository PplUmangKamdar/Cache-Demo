/*
 * Copyright 2020, Savvas Learning Company LLC
 *
 * ServiceHandlerTest.java
 */
package com.savvas.ltg.rbs.CacheDemo.handlers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.support.ServerRequestWrapper;
import com.savvas.ltg.rbs.template.exception.ErrorHandler;
import com.pearson.logging.domain.LogMessage;
import com.pearson.logging.test.util.LogbackTestAppender;
import com.pearson.logging.test.util.LoggerConstants;
import com.pearson.logging.test.util.LoggerUtil;
import com.savvas.ltg.rbs.template.utilities.TemplateUtilities;
import com.savvas.ltg.rbs.CacheDemo.services.CachedemoService;
import reactor.core.publisher.Mono;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ServiceHandler.class, TemplateUtilities.class })
public class ServiceHandlerTest {

    @Mock
    ErrorHandler errorHandler;

    @Mock
    CachedemoService templateService;
    
    LogbackTestAppender testAppender;

    @Mock
    ServerRequest request;

    private ServerRequestWrapper wrapper;

    Mono<String> response = Mono.just("sample response");
    String param = "3X6CVYD5Z28";
    ServiceHandler serviceHandler;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(TemplateUtilities.class);
        serviceHandler = new ServiceHandler();
        serviceHandler.templateService = templateService;
        serviceHandler.errorHandler = errorHandler;
        wrapper = new ServerRequestWrapper(request);
        testAppender = LoggerUtil.mockLogbackAppender(ServiceHandler.class);
    }

    @Test
    public void testGetResponse() {
//        when(templateService.getServiceResponse(param)).thenReturn(response);
//        when(wrapper.pathVariable("param")).thenReturn(param);
//        Mono<ServerResponse> response = serviceHandler.getHandlerResponse(wrapper);
//        assertEquals(response.block().statusCode(), HttpStatus.OK);
//
//        LogMessage logMessage = LoggerUtil.getLogbackFirstLogMessage(testAppender, "test-service", "nightly");
//        Map<String, String> expectedFieldsValues = new HashMap<String, String>();
//        expectedFieldsValues.put(LoggerConstants.CONTEXT_PLATFORM, "test-service");
//        expectedFieldsValues.put(LoggerConstants.CONTEXT_ENVIRONMENT, "nightly");
//        expectedFieldsValues.put(LoggerConstants.MESSAGE, "Get Handler Response API : {}");
//        LoggerUtil.verifyAssertions(expectedFieldsValues, logMessage);
    }

}
