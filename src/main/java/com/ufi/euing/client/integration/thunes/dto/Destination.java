package com.ufi.euing.client.integration.thunes.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;



@Getter @Setter
public class Destination implements Serializable {

    private double amount;
    private String currency;

    @Override
    public String toString() {
        return "Destination{" + "amount=" + amount + ", currency=" + currency + '}';
    }

}
