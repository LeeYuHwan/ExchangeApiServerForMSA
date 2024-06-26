package com.example.client.repository;

import com.example.client.domain.ExchangeRate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ExchangeRepository extends ReactiveCrudRepository<ExchangeRate, Long> {
    Mono<ExchangeRate> findByExchangeId(long exchangeId);
    Mono<ExchangeRate> findFirstByOrderByCreateTimeDesc();
}