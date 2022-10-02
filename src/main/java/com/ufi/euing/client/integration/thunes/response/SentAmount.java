package com.ufi.euing.client.integration.thunes.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter @Getter
public class SentAmount implements Serializable {

    private double amount;
    private String currency;

    @Override
    public String toString() {
        return "SentAmount{" + "amount=" + amount + ", currency=" + currency + '}';
    }
}
