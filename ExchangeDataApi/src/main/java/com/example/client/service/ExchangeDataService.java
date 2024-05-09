package com.example.client.service;

import com.example.client.domain.ExchangeRate;
import com.example.client.dto.ExchangeDto;
import com.example.client.exception.DataException;
import com.example.client.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExchangeDataService {
    private final ExchangeRepository exchangeRepository;
    private final DefaultConversionService conversionService;

    public Mono<?> perform() {
        return getLatestExchangeRate()
                .map(data -> conversionService.convert(data, ExchangeDto.class));
    }

    private Mono<ExchangeRate> getLatestExchangeRate() {
        return exchangeRepository.findFirstByOrderByCreateTimeDesc()
                .switchIfEmpty(Mono.error((new DataException(9999, "DATA NOT EXIST"))));
    }
}
