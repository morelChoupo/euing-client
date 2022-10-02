/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.billpay;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author UFI
 */

@Getter @Setter
public class ResponseCamwater {

    private int status;
    private int code;
    private String message;
    private Long balance;
    private Long unpaid;
    private Long penalty;
    private Long monthFlow;
    private String customerName;
    private String dateLimit;
    private Long minToPay;
    private String idTransaction;
    private String data;

    @Override
    public String toString() {
        return "ResponseCamwater{"
                + "status=" + status
                + "code=" + code
                + ", message='" + message + '\''
                + ", balance=" + balance
                + ", unpaid=" + unpaid
                + ", penalty=" + penalty
                + ", monthFlow=" + monthFlow
                + ", customerName='" + customerName + '\''
                + ", dateLimit='" + dateLimit + '\''
                + ", minToPay=" + minToPay
                + ", idTransaction='" + idTransaction + '\''
                + '}';
    }
}
