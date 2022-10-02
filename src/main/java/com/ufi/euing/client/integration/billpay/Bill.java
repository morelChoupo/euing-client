/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.billpay;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author UFI
 */

@Getter @Setter
public class Bill implements Serializable {

    private String billId;
    private String customerId;
    private String billType;
    private String ref4;
    private String billNumber;
    private String billGenerationDate;
    private String billDueDate;
    private Integer billAmount;
    private String customerName;
    private String meterNumber;
    private String billStatus;
    private String billTypeDesc;
    private String agence;
    private Long balance;
    private Long penalty;
    private String matricule;
    private String filiere;
    private String label;


    @Override
    public String toString() {
        return "Bill{" + "billId=" + billId + ", customerId=" + customerId + ", billType=" + billType + ", ref4=" + ref4 + ", billNumber=" + billNumber + ", billGenerationDate=" + billGenerationDate + ", billDueDate=" + billDueDate + ", billAmount=" + billAmount + ", customerName=" + customerName + ", meterNumber=" + meterNumber + ", billStatus=" + billStatus + ", billTypeDesc=" + billTypeDesc + ", agence=" + agence + ", balance=" + balance + ", penalty=" + penalty + ", matricule=" + matricule + ", filiere=" + filiere + ", label=" + label + '}';
    }

}
