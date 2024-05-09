package com.example.client.config;

import com.example.client.converter.ExchangeApiDtoToExchangeDomainConverter;
import com.example.client.converter.ExchangeDomainToExchangeDtoConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class WebConfig {
    @Bean
    public DefaultConversionService conversionService(){

        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new ExchangeApiDtoToExchangeDomainConverter());
        conversionService.addConverter(new ExchangeDomainToExchangeDtoConverter());

        return conversionService;
    }
}
