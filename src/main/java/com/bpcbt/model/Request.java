package com.bpcbt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Request {

    private String account;
    private String merchantId;
    private String templateVersion;
    private String qrcType;
    private String amount;
    private String currency;
    private String paymentPurpose;
    private String callbackMerchantURL; //
    private com.bpcbt.model.PaymentDetails paymentDetails;
    private String[] qrcIds;
    private String qrcId;
    private String transactionId;
    private String comment;
    private String recipientBankId;
    @JsonIgnore
    private String nameOfMethod;
}
