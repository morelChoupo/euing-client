/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author User
 */

@Getter @Setter
public class CotationDetails {

    @JsonProperty("QuotationID")
    private String QuotationID;
    @JsonProperty("Nominated")
    private String Nominated;
    @JsonProperty("Sender")
    private String Sender;
    @JsonProperty("SendingCurrency")
    private int SendingCurrency;
    @JsonProperty("PayingCurrency")
    private int PayingCurrency;
    @JsonProperty("CommissionType")
    private int CommissionType;
    @JsonProperty("AmountInPayIn")
    private double AmountInPayIn;
    @JsonProperty("AmountInPayOut")
    private double AmountInPayOut;
    @JsonProperty("CommissionAmount")
    private double CommissionAmount;
    @JsonProperty("CommissionPercent")
    private double CommissionPercent;
    @JsonProperty("TotalAmount")
    private double TotalAmount;
    @JsonProperty("CustomerRate")
    private double CustomerRate;
    @JsonProperty("TotalAmountWithTax")
    private double TotalAmountWithTax;
    @JsonProperty("AmountInPayInUSD")
    private double AmountInPayInUSD;
    @JsonProperty("BankCharges")
    private double BankCharges;
    @JsonProperty("BankChargesType")
    private double BankChargesType;
    @JsonProperty("taxationViewModel")
    private List<TaxationViewModel> taxationViewModel;

}

@Getter @Setter
class TaxationViewModel {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("Template_Id")
    private Long Template_Id;
    @JsonProperty("TaxName")
    private String TaxName;
    @JsonProperty("TaxAmount")
    private double TaxAmount;
    @JsonProperty("CalculatedTax")
    private double CalculatedTax;
    @JsonProperty("TaxType")
    private Long TaxType;
    @JsonProperty("TaxAppliedOn")
    private Long TaxAppliedOn;
    @JsonProperty("CurrencyCode")
    private String CurrencyCode;
    @JsonProperty("CurrencyId")
    private Long CurrencyId;


    @Override
    public String toString() {
        return "TaxationViewModel{" + "id=" + id + ", Template_Id=" + Template_Id + ", TaxName=" + TaxName + ", TaxAmount=" + TaxAmount + ", CalculatedTax=" + CalculatedTax + ", TaxType=" + TaxType + ", TaxAppliedOn=" + TaxAppliedOn + ", CurrencyCode=" + CurrencyCode + ", CurrencyId=" + CurrencyId + '}';
    }

}
