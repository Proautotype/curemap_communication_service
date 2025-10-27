package com.custard.curemapcommunicationservice.adapter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private String code = "00";
    private String message = "";

    private T data;
}
