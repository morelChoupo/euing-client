/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 * @author User
 */

@Data
public class CreateTransactionsCotation {

    @JsonProperty("NominatedCode")
    private String nominatedCode;
    @JsonProperty("SendingAmount")
    private String sendingAmount;
    @JsonProperty("SendingCurrencyCode")
    private String sendingCurrencyCode;
    @JsonProperty("PayingAmount")
    private String payingAmount;
    @JsonProperty("PayingCurrencyCode")
    private String payingCurrencyCode;
    @JsonProperty("TotalAmount")
    private String totalAmount;
    @JsonProperty("AmountType")
    private String amountType;

    @Override
    public String toString() {
        return "CreateTransactionsCotation{" + "nominatedCode=" + nominatedCode + ", sendingAmount=" + sendingAmount + ", sendingCurrencyCode=" + sendingCurrencyCode + ", payingAmount=" + payingAmount + ", payingCurrencyCode=" + payingCurrencyCode + ", totalAmount=" + totalAmount + ", amountType=" + amountType + '}';
    }

}
