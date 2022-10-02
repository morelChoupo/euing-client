package com.ufi.euing.client.integration.thunes.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class Payer implements Serializable {

    private String country_iso_code;
    private String currency;
    private int id;
    private String name;
    private Service service;

    @Override
    public String toString() {
        return "Payer{" + "country_iso_code=" + country_iso_code + ", currency=" + currency + ", id=" + id + ", name=" + name + ", service=" + service + '}';
    }
}
