package com.example.client.converter;


import com.example.client.domain.ExchangeRate;
import com.example.client.dto.ExchangeApiResponseDto;
import org.springframework.core.convert.converter.Converter;

public class ExchangeApiDtoToExchangeDomainConverter implements Converter<ExchangeApiResponseDto, ExchangeRate> {
    @Override
    public ExchangeRate convert(ExchangeApiResponseDto source) {
        return ExchangeRate.builder()
                .usd(source.getRates().getUsd())
                .krw(source.getRates().getKrw())
                .timeLastUpdateUtc(source.getTimeLastUpdateUtc())
                .build();
    }

}
