package com.ufi.euing.client.integration.thunes.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;



@Getter @Setter
public class Quotation implements Serializable {

    private String external_id;
    private String payer_id;
    private String mode;
    private String transaction_type;
    private Source source;
    private Destination destination;

    @Override
    public String toString() {
        return "Quotation{" + "external_id=" + external_id + ", payer_id=" + payer_id + ", mode=" + mode + ", transaction_type=" + transaction_type + ", source=" + source + ", destination=" + destination + '}';
    }

}
