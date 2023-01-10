package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {
    private boolean success;
    private int httpResponseCode;
    private String serviceCode;

    public Response(boolean success, int httpResponseCode, String serviceCode){
        this.success = success;
        this.httpResponseCode = httpResponseCode;
        this.serviceCode = serviceCode;
    }
}
