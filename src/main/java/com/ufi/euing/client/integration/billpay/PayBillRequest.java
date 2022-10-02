package com.ufi.euing.client.integration.billpay;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter @Setter
public class PayBillRequest {

    private BigDecimal amount;
    private Long counterCode; // paymentcheckoutcode
    private String paymentMethod;
    private String status;
    private String billNumber;

    public PayBillRequest() {
    }

    public PayBillRequest(BigDecimal amount, Long counterCode, String paymentMethod, String status, String billNumber) {
        this.amount = amount;
        this.counterCode = counterCode;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.billNumber = billNumber;
    }

    @Override
    public String toString() {
        return "PayBillRequest{" + "amount=" + amount + ", counterCode=" + counterCode + ", paymentMethod=" + paymentMethod + ", status=" + status + ", billNumber=" + billNumber + '}';
    }
}
