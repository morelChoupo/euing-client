package com.ufi.euing.client.integration.billpay;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaymentEneo extends Bill {

    private String transactionId;
    private String message;
    private String status;
}
