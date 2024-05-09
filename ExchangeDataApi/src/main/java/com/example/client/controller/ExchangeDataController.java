package com.example.client.controller;

import com.example.client.service.ExchangeDataService;
import com.example.client.service.ExchangeWebClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ms1/api/v1/exchange")
public class ExchangeDataController {

    private final ExchangeDataService exchangeDataService;
    private final ExchangeWebClientService exchangeWebClientService;

    @GetMapping("/web-client")
    public Mono<?> createExchangeRate(){
        return exchangeWebClientService.perform();
    }

    @GetMapping("/data")
    public Mono<?> getDataExchangeRate(){
        return exchangeDataService.perform();
    }
}
