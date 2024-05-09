package com.example.client.webclient;


import com.example.client.dto.ExchangeApiResponseDto;
import com.example.client.properties.ExchangeRateApiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExchangeWebClient {

    private final ExchangeRateApiProperties exchangeRateApiProperties;

    public Mono<ExchangeApiResponseDto> streamingExchangeRates() {

        WebClient webClient = WebClient.create(exchangeRateApiProperties.getUrl());

        Mono<ExchangeApiResponseDto> response =
                webClient
                        .get()
                        .uri(exchangeRateApiProperties.getApi())
                        .retrieve()
                        .bodyToMono(ExchangeApiResponseDto.class);

        log.info("---------TODAY EXCHANGE RATE---------");

        response.subscribe(exchange -> {
                    log.info("usd: {}", exchange.getRates().getUsd());
                    log.info("krw: {}", exchange.getRates().getKrw());
                    log.info("lastUpdateTime: {}", exchange.getTimeLastUpdateUtc());
                    log.info("=======================================");
                },
                error -> log.error("# error happened: ", error));

        log.info("--------------------------------------");

        return response;
    }
}
