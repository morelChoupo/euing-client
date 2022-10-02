package com.ufi.euing.client.integration.billpay;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class BillsMilddelWare implements Serializable {

    private String billId;
    private String billType;
    private String ref4;
    private String billNumber;
    private String billGenerationDate;
    private String billDueDate;
    private String billAmount;
    private String customerName;
    private String meterNumber;
    private String custbillStatusomerName;
    private String customerId;
    private String billStatus;

}
