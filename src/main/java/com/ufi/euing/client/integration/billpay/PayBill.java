/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.billpay;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 *
 * @author UFI
 */
public class PayBill implements Serializable {
 
    Long factureId;
    String serviceCode;

    @JsonIgnore
    Long userId;

    String depositorName;
    String depositorPhone;
    Long montant;

    public PayBill() {
    }

    public Long getFactureId() {
        return factureId;
    }

    public void setFactureId(Long factureId) {
        this.factureId = factureId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDepositorName() {
        return depositorName;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName;
    }

    public String getDepositorPhone() {
        return depositorPhone;
    }

    public void setDepositorPhone(String depositorPhone) {
        this.depositorPhone = depositorPhone;
    }

    public Long getMontant() {
        return montant;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "PayBill{" + "factureId=" + factureId + ", serviceCode=" + serviceCode + ", userId=" + userId + ", depositorName=" + depositorName + ", depositorPhone=" + depositorPhone + ", montant=" + montant + '}';
    }
}
