package com.ufi.euing.client.integration.thunes.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter @Setter
public class Fee implements Serializable {

    private double amount;
    private String currency;

    @Override
    public String toString() {
        return "Fee{" + "amount=" + amount + ", currency=" + currency + '}';
    }
}
