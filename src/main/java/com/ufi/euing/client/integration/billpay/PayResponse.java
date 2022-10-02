package com.ufi.euing.client.integration.billpay;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class PayResponse implements Serializable {

    private int status;
    private String message;
    private TransactionResponse transaction;

    public PayResponse() {
    }

    public PayResponse(int status, String message, TransactionResponse transaction) {
        this.status = status;
        this.message = message;
        this.transaction = transaction;
    }

}
