package com.example.client.exception.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExchangeErrorDto {
    private int resultCode;
    private String resultMsg;
}
