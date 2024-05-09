package com.example.client.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExchangeDto {
    private int resultCode;
    private String resultMsg;
    private String usd;
    private String krw;
    private String timeLastUpdateUtc;
}
