package com.example.gateway.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataException extends RuntimeException{
    private int resultCode;
    private String resultMsg;
}
