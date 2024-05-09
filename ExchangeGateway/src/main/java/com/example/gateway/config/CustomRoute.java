package com.example.gateway.config;

import com.example.gateway.jwt.filter.JwtAuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRoute {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, JwtAuthFilter jwtAuthFilter){
        return builder.routes()
                .route("exchange1", r -> r.path("/ms1/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthFilter.Config("Authorization", "Bearer"))))
                        .uri("lb://EXCHANGE1")) //로드 밸런싱
                .route("exchange2", r -> r.path("/ms2/**")
                        .uri("lb://EXCHANGE2"))
                .build();
    }

}
