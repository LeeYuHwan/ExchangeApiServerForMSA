package com.example.client.service;

import com.example.client.domain.ExchangeRate;
import com.example.client.dto.ExchangeApiResponseDto;
import com.example.client.repository.ExchangeRepository;
import com.example.client.webclient.ExchangeWebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExchangeWebClientService {
    private final ExchangeWebClient exchangeWebClient;
    private final DefaultConversionService conversionService;
    private final ExchangeRepository exchangeRepository;


    public Mono<?> perform() {
        Mono<ExchangeApiResponseDto> exchangeApiResponseDto = exchangeWebClient.streamingExchangeRates();
        return exchangeApiResponseDto.flatMap(data -> createExchangeRate(conversionService.convert(data, ExchangeRate.class)));
    }

    private Mono<ExchangeRate> createExchangeRate(ExchangeRate exchange) {
        return exchangeRepository.save(exchange);
    }
}
