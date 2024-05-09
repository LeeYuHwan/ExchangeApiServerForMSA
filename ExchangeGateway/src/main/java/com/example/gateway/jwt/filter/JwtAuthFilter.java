package com.example.gateway.jwt.filter;

import com.example.gateway.jwt.TokenProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@Slf4j
public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config> {

    private final TokenProvider tokenProvider;
    public JwtAuthFilter(TokenProvider tokenProvider){
        super(Config.class);
        this.tokenProvider = tokenProvider;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            String token = tokenProvider.getToken(exchange.getRequest());

            if(!Objects.isNull(token) && tokenProvider.validateJwtToken(token)){
                return chain.filter(exchange); // Token is valid, continue to the next filter
            }

            return unauthorizedResponse(exchange); // Token is not valid, respond with unauthorized
        };
    }

    // 인증 실패 Response
    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    // Config inner class -> 설정파일에 있는 args
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Config{
        private String headerName; // Authorization
        private String granted; // Bearer
    }

}
