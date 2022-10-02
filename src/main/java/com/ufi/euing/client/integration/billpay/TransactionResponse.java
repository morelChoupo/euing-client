package com.ufi.euing.client.integration.billpay;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter  @Setter
public class TransactionResponse implements Serializable {

    public String code;
    public String transactionId;
    public String transactionStatus;
    public String billNumber;
    public String paidAmount;

}
