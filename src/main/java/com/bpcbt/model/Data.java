package com.bpcbt.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    private String qrcId;
    private String payload;
    private String status;
    private String code;
    private String message;
    private String trxId;
}
