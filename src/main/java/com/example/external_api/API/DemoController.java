package com.example.external_api.API;

import com.example.external_api.DTO.CombinedResponse;
import com.example.external_api.RemoteApiTester;
import com.example.external_api.Service.ApiService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DemoController {
    ApiService apiService;
    RemoteApiTester remoteApiTester;

    public DemoController(ApiService apiService, RemoteApiTester remoteApiTester) {
        this.apiService = apiService;
        this.remoteApiTester = remoteApiTester;
    }

    private final int SLEEP_TIME = 1000*3;

    @GetMapping(value = "/random-string-slow")
    public String slowEndpoint() throws InterruptedException {
        Thread.sleep(SLEEP_TIME);
        return RandomStringUtils.randomAlphanumeric(10);
    }

    @GetMapping(value = "/api")
    public Mono<CombinedResponse> api(){
        return apiService.fetchNameDetails("Mathias");
    }

    @GetMapping(value = "/api-blocking")
    public void apiBlocking(){
        remoteApiTester.callEndpointBlocking();
    }

    @GetMapping(value = "/api-non-blocking")
    public void apiNonBlocking(){
        remoteApiTester.callSlowEndpointNonBlocking();
    }
}
