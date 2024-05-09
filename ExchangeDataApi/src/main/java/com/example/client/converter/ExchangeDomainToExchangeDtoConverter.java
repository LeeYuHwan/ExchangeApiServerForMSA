package com.example.client.converter;

import com.example.client.domain.ExchangeRate;
import com.example.client.dto.ExchangeDto;
import org.springframework.core.convert.converter.Converter;

public class ExchangeDomainToExchangeDtoConverter implements Converter<ExchangeRate, ExchangeDto> {
    @Override
    public ExchangeDto convert(ExchangeRate source) {
        return ExchangeDto.builder()
                .resultCode(0000)
                .resultMsg("success")
                .usd(source.getUsd())
                .krw(source.getKrw())
                .timeLastUpdateUtc(source.getTimeLastUpdateUtc())
                .build();
    }
}
