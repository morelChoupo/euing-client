package com.ufi.euing.client.integration.thunes.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class Source implements Serializable {

    private double amount;
    private String currency;
    private String country_iso_code;

    @Override
    public String toString() {
        return "Source{" + "amount=" + amount + ", currency=" + currency + ", country_iso_code=" + country_iso_code + '}';
    }

}
