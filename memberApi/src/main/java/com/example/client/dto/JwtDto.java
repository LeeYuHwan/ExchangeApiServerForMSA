package com.example.client.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtDto {
    private int resultCode;
    private String resultMsg;
    private String token;
}
