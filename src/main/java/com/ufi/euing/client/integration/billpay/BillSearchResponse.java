package com.ufi.euing.client.integration.billpay;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter @Setter
public class BillSearchResponse implements Serializable {

    private int code;
    private String message;
    private List<Bill> data;

    public BillSearchResponse() {
    }

    public BillSearchResponse(int code, String message, List<Bill> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "BillSearchResponse{" + "code=" + code + ", message=" + message + ", data=" + data + '}';
    }

}
