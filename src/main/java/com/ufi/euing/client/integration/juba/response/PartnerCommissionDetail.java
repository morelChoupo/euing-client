/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufi.euing.client.integration.juba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author User
 */

@Getter @Setter
public class PartnerCommissionDetail {

    @JsonProperty("TotalCommissionInPayInCurrency")
    private double TotalCommissionInPayInCurrency;

    @JsonProperty("PayinCurrencyCode")
    private String PayinCurrencyCode;

    @JsonProperty("TotalCommissionInSettlmentCurrency")
    private double TotalCommissionInSettlmentCurrency;

    @JsonProperty("SettlementCurrencyCode")
    private String SettlementCurrencyCode;

    @JsonProperty("ParnterCommissionInPayInCurrency")
    private double ParnterCommissionInPayInCurrency;

    @JsonProperty("PartnerCommissionInSettlement")
    private double PartnerCommissionInSettlement;


    @Override
    public String toString() {
        return "PartnerCommissionDetail{" + "TotalCommissionInPayInCurrency=" + TotalCommissionInPayInCurrency + ", PayinCurrencyCode=" + PayinCurrencyCode + ", TotalCommissionInSettlmentCurrency=" + TotalCommissionInSettlmentCurrency + ", SettlementCurrencyCode=" + SettlementCurrencyCode + ", ParnterCommissionInPayInCurrency=" + ParnterCommissionInPayInCurrency + ", PartnerCommissionInSettlement=" + PartnerCommissionInSettlement + '}';
    }

}
