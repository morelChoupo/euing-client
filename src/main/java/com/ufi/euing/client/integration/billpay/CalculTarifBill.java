/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.billpay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author UFI
 */

@Getter @Setter
public class CalculTarifBill implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    long id; // still set automatically
    @Column(name = "amountSent")
    private double amountSent;
    @Column(name = "amountToPaid")
    private double amountToPaid;
    @Column(name = "totalToPaid")
    private double totalToPaid;
    @Column(name = "fees")
    private double fees;
    @Column(name = "othersFees")
    private double othersFees;
    @Column(name = "exchangeRate")
    private double exchangeRate;
    @Column(name = "res")
    private int res;
    @Column(name = "res_message")
    private String res_message;

    public CalculTarifBill(int res, String res_message, double amountSent, double amountToPaid, double totalToPaid, double fees, double othersFees, double exchangeRate) {
        this.res = res;
        this.res_message = res_message;
        this.amountSent = amountSent;
        this.amountToPaid = amountToPaid;
        this.totalToPaid = totalToPaid;
        this.fees = fees;
        this.othersFees = othersFees;
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "CalculTarifBill{" + "id=" + id + ", amountSent=" + amountSent + ", amountToPaid=" + amountToPaid + ", totalToPaid=" + totalToPaid + ", fees=" + fees + ", othersFees=" + othersFees + ", exchangeRate=" + exchangeRate + ", res=" + res + ", res_message=" + res_message + '}';
    }

}
