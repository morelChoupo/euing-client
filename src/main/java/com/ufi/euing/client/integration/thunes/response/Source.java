package com.ufi.euing.client.integration.thunes.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter @Getter
public class Source implements Serializable {

    private double amount;
    private String country_iso_code;
    private String currency;

    @Override
    public String toString() {
        return "Source{" + "amount=" + amount + ", country_iso_code=" + country_iso_code + ", currency=" + currency + '}';
    }
}
