package com.ufi.euing.client.integration.billpay;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
public class ResponseMilddelwareConsult implements Serializable {

    private int status;
    private String message;
    private List<BillsMilddelWare> bills;

}
