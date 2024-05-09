package com.example.client.exception.advice;

import com.example.client.exception.DataException;
import com.example.client.exception.dto.ExchangeErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataException.class)
    private Mono<ExchangeErrorDto> dataException(DataException e){
        log.info("API ERROR :: DATA EXCEPTION");

        return Mono.just(ExchangeErrorDto.builder()
                        .resultCode(e.getResultCode())
                        .resultMsg(e.getResultMsg())
                        .build());
    }
}
