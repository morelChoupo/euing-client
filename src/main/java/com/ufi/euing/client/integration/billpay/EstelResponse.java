/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.billpay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author UFI
 */

@Getter @Setter
public class EstelResponse implements Serializable {

    private String statut;
    private String message;
    private String billno;
    private String biller;
    private String amount;
    private String fees;
    private String transaction;
    private String commission;
    private String bill_status;

    public EstelResponse() {
    }

    @Override
    public String toString() {
        return "EstelResponse{" + "statut=" + statut + ", message=" + message + ", billno=" + billno + ", biller=" + biller + ", amount=" + amount + ", fees=" + fees + ", transaction=" + transaction + ", commission=" + commission + ", bill_status=" + bill_status + '}';
    }
}
