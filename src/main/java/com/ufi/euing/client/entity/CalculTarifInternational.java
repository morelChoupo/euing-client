/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class CalculTarifInternational implements Serializable {
//public class CalculTarifInternational {

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

    public CalculTarifInternational() {
    }

    public CalculTarifInternational(int res, String res_message, double amountSent, double amountToPaid, double totalToPaid, double fees, double othersFees, double exchangeRate) {
        this.res = res;
        this.res_message = res_message;
        this.amountSent = amountSent;
        this.amountToPaid = amountToPaid;
        this.totalToPaid = totalToPaid;
        this.fees = fees;
        this.othersFees = othersFees;
        this.exchangeRate = exchangeRate;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public double getAmountSent() {
        return amountSent;
    }

    public void setAmountSent(double amountSent) {
        this.amountSent = amountSent;
    }

    public double getAmountToPaid() {
        return amountToPaid;
    }

    public void setAmountToPaid(double amountToPaid) {
        this.amountToPaid = amountToPaid;
    }

    public double getTotalToPaid() {
        return totalToPaid;
    }

    public void setTotalToPaid(double totalToPaid) {
        this.totalToPaid = totalToPaid;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public double getOthersFees() {
        return othersFees;
    }

    public void setOthersFees(double othersFees) {
        this.othersFees = othersFees;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "CalculTarifInternational{" + "id=" + id + ", amountSent=" + amountSent + ", amountToPaid=" + amountToPaid + ", totalToPaid=" + totalToPaid + ", fees=" + fees + ", othersFees=" + othersFees + ", exchangeRate=" + exchangeRate + ", res=" + res + ", res_message=" + res_message + '}';
    }

}
