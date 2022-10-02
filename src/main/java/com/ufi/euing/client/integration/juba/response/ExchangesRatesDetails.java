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
public class ExchangesRatesDetails {

    @JsonProperty("CurrencyCode")
    private String CurrencyCode;

    @JsonProperty("Rate")
    private String Rate;

    @JsonProperty("Description")
    private String Description;

    @Override
    public String toString() {
        return "ExchangesRatesDetails{" + "CurrencyCode=" + CurrencyCode + ", Rate=" + Rate + ", Description=" + Description + '}';
    }

}
