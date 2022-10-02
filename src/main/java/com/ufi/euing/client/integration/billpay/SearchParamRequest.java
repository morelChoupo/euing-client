package com.ufi.euing.client.integration.billpay;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchParamRequest {

    // The type of bill parameter to search. Should be one of BillNumber, MeterNumber, CustomerId. Required
    private String type;
    private String searchParam;

    public SearchParamRequest(String type, String searchParam) {
        this.type = type;
        this.searchParam = searchParam;
    }

    @Override
    public String toString() {
        return "SearchParamRequest{" + "type=" + type + ", searchParam=" + searchParam + '}';
    }
}
