package com.bpcbt.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private String code;
    private String message;
    private String result;
    private String description;
    private String transactionId;
    private com.bpcbt.model.Data data;
}
