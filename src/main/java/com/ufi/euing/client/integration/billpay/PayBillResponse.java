package com.ufi.euing.client.integration.billpay;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PayBillResponse {

    private int code;
    private String message;
    private PaymentEneo data;

    public PayBillResponse(int code, String message, PaymentEneo data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public PayBillResponse() {
    }

    @Override
    public String toString() {
        return "PayBillResponse{" + "code=" + code + ", message=" + message + ", data=" + data + '}';
    }
}
