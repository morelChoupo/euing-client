package com.ufi.euing.client.integration.billpay;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class PayMilddawareResponse implements Serializable {

    private int status;
    private String message;
    private TransactionPayMiddleware transaction;

}
